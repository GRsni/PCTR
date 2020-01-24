//Programa conVolParalela. Realiza la convolucion de una matriz de enteros empleando tareas concurrentes.
//Santiago Jesús Mas Peña
//22/01/20

#include <iostream>
#include <cstdlib>
#include <vector>
#include <ctime>
#include <chrono>
#include <thread>

//Tamaño de la matriz a convolucionar.
const int tamA = 1000;
//Numero de tareas concurrentesa declarar.
const int numHilos = 14;

//Mascaras de convolución ya declaradas.
short MASKS[5][3][3] = {{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}},
                        {{0, 0, 0}, {-1, 1, 0}, {0, 0, 0}},
                        {{0, 1, 0}, {1, 4, 1}, {0, 1, 0}},
                        {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}},
                        {{1, -2, 1}, {-2, 5, 2}, {1, -2, 1}}};

std::vector<std::vector<int>> matrix(tamA, std::vector<int>(tamA));
std::vector<std::vector<int>> res(tamA, std::vector<int>(tamA));

/**
 * Metodo que realiza la convolucion de la matriz matrix, desde 
 * la fila startRow hasta la fila endRow, empleando la mascara
 * de convolucion MASKS[matConvIndex]
 */
void realizarConvolucion(int matConvIndex, int startRow, int endRow)
{
    std::vector<std::vector<int>> res(tamA, std::vector<int>(tamA));

    for (int i = startRow; i < endRow; i++)
    {
        for (int j = 0; j < tamA; j++)
        {
            int sum = 0;
            for (int k = -1; k <= 1; k++)
            {
                for (int m = -1; m <= 1; m++)
                {
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

/**
 * Metodo principal. Inicializa la matriz aleatoria original,
 * pide al usuario que elija la matriz de convolucion, y 
 * realiza la convolucion creando numHilos tareas concurrentes.
 */
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
    std::cout << "Elige la mascara de convolución\n1:Enfocar\n2:Realzar bordes\n3:Detectar bordes\n4:Filtro Sobel\n5:Filtro Sharpen" << std::endl;
    std::cin >> opcionMascara;
    if (opcionMascara < 1 || opcionMascara > 5)
    {
        std::cout << "Opcion no valida. Saliendo de programa." << std::endl;
        exit(-1);
    }
    //Creacion de hilos concurrentes.
    std::thread hilos[numHilos];
    start = std::chrono::system_clock::now();
    for (int i = 0; i < numHilos; i++)
    {
        int startRow = tamA / numHilos * i;
        int endRow = tamA / numHilos * (i + 1);
        hilos[i] = std::thread(realizarConvolucion, opcionMascara - 1, startRow, endRow);
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
