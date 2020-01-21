#include <iostream>
#include <cstdlib>
#include <vector>
#include <ctime>
#include <chrono>

const int tamA = 10000;

short ENFOCAR_MASK[3][3] = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
short REALZAR_BORDES_MASK[3][3] = {{0, 0, 0}, {-1, 1, 0}, {0, 0, 0}};
short DETECTAR_BORDES_MASK[3][3] = {{0, 1, 0}, {1, 4, 1}, {0, 1, 0}};
short SOBEL_MASK[3][3] = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
short SHARPEN_MASK[3][3] = {{1, -2, 1}, {-2, 5, 2}, {1, -2, 1}};

void realizarConvolucion(std::vector<std::vector<int>> matrix, short conv[3][3])
{
    std::vector<std::vector<int>> res(tamA, std::vector<int>(tamA));

    for (int i = 0; i < tamA; i++)
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
                        sum += matrix[i + k][j + m] * conv[k + 1][m + 1];
                    }
                }
            }
            res[i][j] = sum;
        }
    }
    /*for (int i = 0; i < tamA; i++)
    {

        std::cout << res[i][0] << ", ";
    }*/
}

int main()
{
    srand(time(NULL));
    std::vector<std::vector<int>> matA(tamA, std::vector<int>(tamA));
    short matConv[3][3];

    for (int i = 0; i < tamA; i++)
    {
        for (int j = 0; j < tamA; j++)
        {
            matA[i][j] = rand() % 255;
        }
    }

    std::chrono::time_point<std::chrono::system_clock> start, end;

    int opcion;
    std::cout << "Elige la mascara de convolución\n1:Enfocar\n2:Realzar bordes\n3:Detectar bordes\n4:Filtro Sobel\n5:Filtro Sharpen" << std::endl;
    std::cin >> opcion;
    start = std::chrono::system_clock::now();
    switch (opcion)
    {
    case 1:
        realizarConvolucion(matA, ENFOCAR_MASK);
        break;
    case 2:
        realizarConvolucion(matA, REALZAR_BORDES_MASK);
        break;
    case 3:
        realizarConvolucion(matA, DETECTAR_BORDES_MASK);
        break;
    case 4:
        realizarConvolucion(matA, SOBEL_MASK);
        break;
    case 5:
        realizarConvolucion(matA, SHARPEN_MASK);
        break;
    default:
        std::cout << "Opcion no valida. Saliendo de programa." << std::endl;
        exit(-1);
        break;
    }
    end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end - start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
              << "Tiempo: " << elapsed_seconds.count() << "s\n";
    std::cout << "Saliendo de programa." << std::endl;
}
