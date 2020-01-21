#include <iostream>
#include <cstdlib>
#include <vector>
#include <ctime>
#include <chrono>
#include <thread>

const int tamA = 10000;
const int numHilos = 2;

short MASKS[5][3][3] = {{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}},
                        {{0, 0, 0}, {-1, 1, 0}, {0, 0, 0}},
                        {{0, 1, 0}, {1, 4, 1}, {0, 1, 0}},
                        {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}},
                        {{1, -2, 1}, {-2, 5, 2}, {1, -2, 1}}};

std::vector<std::vector<int>> matrix(tamA, std::vector<int>(tamA));
std::vector<std::vector<int>> res(tamA, std::vector<int>(tamA));

void realizarConvolucion(int matConvIndex, int startRow, int endRow)
{
    std::vector<std::vector<int>> res(tamA, std::vector<int>(tamA));

    for (int i = startRow; i < endRow; i++)
    {
        for (int j = 0; j < tamA; j++)
        {
            int sum = 0;
            // System.out.println("row: " + row + " col: " + col + " => " + A[row][col]);
            for (int k = -1; k <= 1; k++)
            {
                for (int m = -1; m <= 1; m++)
                {
                    // System.out.print((i + k) + " : " + (j + m) + "=" + C[k + 1][m + 1] + " || ");
                    if (i + k >= 0 && i + k < tamA && j + m >= 0 && j + m < tamA)
                    {
                        sum += matrix[i + k][j + m] * MASKS[matConvIndex][k + 1][m + 1];
                    }
                }
            }
            res[i][j] = sum;
        }
    }
}

int main()
{
    srand(time(NULL));

    for (int i = 0; i < tamA; i++)
    {
        for (int j = 0; j < tamA; j++)
        {
            matrix[i][j] = rand() % 255;
        }
    }

    std::chrono::time_point<std::chrono::system_clock> start, end;

    int opcionMascara;
    std::cout << "Elige la mascara de convolución\n0:Enfocar\n1:Realzar bordes\n2:Detectar bordes\n3:Filtro Sobel\n4:Filtro Sharpen" << std::endl;
    std::cin >> opcionMascara;
    if (opcionMascara < 0 || opcionMascara > 4)
    {
        std::cout << "Opcion no valida. Saliendo de programa." << std::endl;
        exit(-1);
    }

    std::thread hilos[numHilos];
    start = std::chrono::system_clock::now();
    for (int i = 0; i < numHilos; i++)
    {
        int startRow = tamA / numHilos * i;
        int endRow = tamA / numHilos * (i + 1);
        hilos[i] = std::thread(realizarConvolucion, opcionMascara, startRow, endRow);
    }
    for (int i = 0; i < numHilos; i++)
    {
        hilos[i].join();
    }

    end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end - start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
              << "Tiempo: " << elapsed_seconds.count() << "s\n";
    std::cout << "Saliendo de programa." << std::endl;
}
