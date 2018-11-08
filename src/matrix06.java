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

public class matrix06 {

    private static double GetRandom(double dMin, double dMax) {
        double dSpread = dMax - dMin;
        return dMin + (Math.random() * dSpread);
    }

    private static final int cMatrixCountMin = 1;
    private static final int cMatrixCountMax = 10;

    private static final int cMatrixSizeMin = 1;
    private static final int cMatrixSizeMax = 10;

    private static final int cMatrixItemMin = -100;
    private static final int cMatrixItemMax = 100;

    private static final String cInputCountFormat = "Введите количество матриц (целое число от %d до %d): ";
    private static final String cInputWidthFormat = "Введите ширину матрицы (целое число от %d до %d): ";
    private static final String cInputHeightFormat = "Введите высоту матрицы (целое число от %d до %d): ";
    private static final String cOutputOutOfRange = "Число %d лежит вне диапазона!";
    private static final String cOutputMatrixTitle = "Матрица %d: ";
    private static final String cOutputMatrixItemFormat = "%8.1f";

    private static final String cOutputMinFormat = " Минимум: %8.1f";
    private static final String cOutputMaxFormat = "Максимум: %8.1f";
    private static final String cOutputAvgFormat = " Среднее: %8.1f";
    private static final String cOutputDiag1Format = "Сумма диагонали 1: %8.1f";
    private static final String cOutputDiag2Format = "Сумма диагонали 2: %8.1f";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean bIsOK = false;


        // Ввод количества матриц
        int iMatrixCount = 0;

        while (!bIsOK) {
            System.out.printf(cInputCountFormat, cMatrixCountMin, cMatrixCountMax);
            iMatrixCount = in.nextInt();
            if ((iMatrixCount >= cMatrixCountMin) && (iMatrixCount <= cMatrixCountMax)) {
                bIsOK = true;
                break;
            } else {
                System.out.printf(cOutputOutOfRange, iMatrixCount);
                System.out.println();
            }
        }

        // Ввод ширины матрицы
        int iMatrixWidth = 0;
        bIsOK = false;

        while (!bIsOK) {
            System.out.printf(cInputWidthFormat, cMatrixSizeMin, cMatrixSizeMax);
            iMatrixWidth = in.nextInt();
            if ((iMatrixWidth >= cMatrixSizeMin) && (iMatrixWidth <= cMatrixSizeMax)) {
                bIsOK = true;
                break;
            } else {
                System.out.printf(cOutputOutOfRange, iMatrixWidth);
                System.out.println();
            }
        }

        // Ввод высоты матрицы
        int iMatrixHeight = 0;
        bIsOK = false;

        while (!bIsOK) {
            System.out.printf(cInputHeightFormat, cMatrixSizeMin, cMatrixSizeMax);
            iMatrixHeight = in.nextInt();
            if ((iMatrixHeight >= cMatrixSizeMin) && (iMatrixHeight <= cMatrixSizeMax)) {
                bIsOK = true;
                break;
            } else {
                System.out.printf(cOutputOutOfRange, iMatrixHeight);
                System.out.println();
            }
        }

        // Создание матриц
        double[][][] matrices = new double[iMatrixCount][iMatrixSize][iMatrixSize];

        for (int nMatrix = 0; nMatrix < iMatrixCount; nMatrix++) {

            double[][] matrix = matrices[nMatrix];

            // Заполнение массива
            double dMin = 0;
            double dMax = 0;
            double dSum = 0;
            double dSumDiag1 = 0;
            double dSumDiag2 = 0;
            boolean bMinMaxInit = true;

            System.out.printf(cOutputMatrixTitle, nMatrix + 1);
            System.out.println();
            for (int row = 0; row < iMatrixSize; row++) {
                for (int col = 0; col < iMatrixSize; col++) {
                    matrix[row][col] = GetRandom(cMatrixItemMin, cMatrixItemMax);
                    System.out.printf(cOutputMatrixItemFormat, matrix[row][col]);

                    // Инициализация мин и макс
                    if (bMinMaxInit) {
                        bMinMaxInit = false;
                        dMin = dMax = matrix[row][col];
                    }

                    // Выбор наименьшего значения
                    if (matrix[row][col] < dMin) {
                        dMin = matrix[row][col];
                    }

                    // Выбор наибольшего значения
                    if (matrix[row][col] > dMax) {
                        dMax = matrix[row][col];
                    }

                    // Сумма элементов матрицы
                    dSum += matrix[row][col];

                    // Расчёт сумм элементов диагоналей
                    if (row == col) {
                        dSumDiag1 += matrix[row][col];
                    }
                    if ((row + col) == (iMatrixSize - 1)) {
                        dSumDiag2 += matrix[row][col];
                    }

                }
                System.out.println();
            }
            System.out.printf(cOutputMinFormat, dMin);
            System.out.println();
            System.out.printf(cOutputMaxFormat, dMax);
            System.out.println();
            System.out.printf(cOutputAvgFormat, dSum / Math.pow(iMatrixSize, 2));
            System.out.println();
            System.out.printf(cOutputDiag1Format, dSumDiag1);
            System.out.println();
            System.out.printf(cOutputDiag2Format, dSumDiag2);
            System.out.println();
        }
    }

}

