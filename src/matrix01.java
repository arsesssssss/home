import java.util.Scanner;

/*
    // Матрицы
    //
    // пределы размеров квадратной матрицы задаются в программе в виде
    // констант
    // (1 и 10, например, где 1 - минимальный размер, 10 - максимальный)

    [ввод на старте программы; если введено число, выходящее за рамки,
    то повторяем ввод]
    Введите размер квадратной матрицы (целое число от 1 до 10): _

    // в программе матрица заполняется случайными числами в пределах
    // заданных в программе констант

    // при выводе матрицы на каждое число отводится определённое
    // количество знакомест, указанное в константах внутри программы:
    // количество "целых" и "дробных" знакомест задаётся константами

    [вывод программы]
    Матрица:
      12      34     565    7878
      65    5654      33    3434
     475    3534    4344     757
     455    6768    8667    7676
*/

public class matrix01 {

    private static double GetRandom(double dMin, double dMax) {
        double dSpread = dMax - dMin;
        return dMin + (Math.random() * dSpread);
    }

    private static final int cMatrixSizeMin = 1;
    private static final int cMatrixSizeMax = 10;

    private static final int cMatrixItemMin = -100;
    private static final int cMatrixItemMax = 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Ввод размера матрицы
        int iMatrixSize = 0;
        boolean bIsOK = false;

        while (!bIsOK) {
            System.out.printf(
                    "Введите размер квадратной матрицы (целое число от %d до %d): ",
                    cMatrixSizeMin, cMatrixSizeMax);
            iMatrixSize = in.nextInt();
            if ((iMatrixSize >= cMatrixSizeMin) && (iMatrixSize <= cMatrixSizeMax)) {
                bIsOK = true;
                break;
            } else {
                System.out.printf("Число %d лежит вне диапазона!", iMatrixSize);
                System.out.println();
            }
        }

        // Создание матрицы
        double[][] matrix = new double[iMatrixSize][iMatrixSize];

        // Заполнение массива
        System.out.println("Матрица:");
        for (int row = 0; row < iMatrixSize; row++) {
            for (int col = 0; col < iMatrixSize; col++) {
                matrix[row][col] = GetRandom(cMatrixItemMin, cMatrixItemMax);
                System.out.printf("%8.1f", matrix[row][col]);
            }
            System.out.println();
        }

    }
}
