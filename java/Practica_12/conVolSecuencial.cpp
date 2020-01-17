#include <iostream>
#include <cstdlib>

// int matA[5][5] = {
//     {35, 40, 41, 45, 50},
//     {40, 40, 42, 46, 52},
//     {42, 46, 50, 55, 55},
//     {48, 52, 56, 58, 60},
//     {56, 60, 65, 70, 75}};

int matConv[3][3] = {
    {-2, -1, 0},
    {-1, 1, 1},
    {0, 1, 2}};

int main()
{
    const int tamA = 10;

    short matA[tamA][tamA];
    short res[tamA][tamA];

    std::cout << "hola mundo" << std::endl;

    for (int i = 0; i < tamA; i++)
    {
        for (int j = 0; j < tamA; j++)
        {
            matA[i][j] = rand() % 255;
            std::cout << matA[i][j] << " ";
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;

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
                        sum += matA[i + k][j + m] * matConv[k + 1][m + 1];
                    }
                }
            }
            res[i][j] = sum;
        }
    }
    for (int i = 0; i < tamA; i++)
    {
        for (int j = 0; j < tamA; j++)
        {
            std::cout << res[i][j] << ", ";
        }
        std::cout << std::endl;
    }
}
