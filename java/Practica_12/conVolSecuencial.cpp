//Programa conVolSecuencial. Realiza la convolucion de una matriz de enteros de forma secuencial.
//Santiago Jesús Mas Peña
//22/01/20

#include <iostream>
#include <cstdlib>
#include <vector>
#include <ctime>
#include <chrono>

//Tamaño de la matriz a crear
const int tamA = 1000;

//Mascaras de convolucion
short MASKS[5][3][3] = {{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}},
                        {{0, 0, 0}, {-1, 1, 0}, {0, 0, 0}},
                        {{0, 1, 0}, {1, 4, 1}, {0, 1, 0}},
                        {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}},
                        {{1, -2, 1}, {-2, 5, 2}, {1, -2, 1}}};

/**
 * Metodo que realiza la convolución de la matriz de entrada matrix,
 * mediante la mascara de convolucion conv[3][3].
 */
void realizarConvolucion(std::vector<std::vector<int>> matrix, short conv[3][3])
{
    std::vector<std::vector<int>> res(tamA, std::vector<int>(tamA));

    for (int i = 0; i < tamA; i++)
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
                        sum += matrix[i + k][j + m] * conv[k + 1][m + 1];
                    }
                }
            }
            res[i][j] = sum;
        }
    }
}

/**
 * Metodo principal. Crea e inicializa una matriz de enteros aleatoria 
 * de tamaño tamA, pide al usuario que elija una mascara de convolucion,
 * y realiza la convolucion de la matriz aleatoria.
 */
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
    if (opcion < 1 || opcion > 5)
    {
        std::cout << "Opcion no valida. Saliendo de programa." << std::endl;
        exit(-1);
    }
    realizarConvolucion(matA, MASKS[opcion - 1]);
    end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end - start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "Calculo terminado a las: " << std::ctime(&end_time)
              << "Tiempo: " << elapsed_seconds.count() << "s\n";
    std::cout << "Saliendo de programa." << std::endl;
}
