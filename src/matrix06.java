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

    public static void main(String[] args) {

        class MatrixWorker06{
            private double GetRandom(double dMin, double dMax) {
                double dSpread = dMax - dMin;
                return dMin + (Math.random() * dSpread);
            }

            class UserInputResultInt {
                public UserInputResultInt(){
                    iValue = 0;
                    bIsOK = false;
                }
                public int iValue = 0;
                public boolean bIsOK = false;
            }

            public UserInputResultInt GetUserInputInt(Scanner in, String sPromptFormat,
                                                      int iMin, int iMax, String sOutOfRangeFormat,
                                                      int iMaxTries) {
                UserInputResultInt uir = new UserInputResultInt();
                int iTries = iMaxTries;
                try {
                    while (!uir.bIsOK) {
                        if (iMaxTries > 0){
                            iTries--;
                            if (iTries < 0){
                                break;
                            }
                        }
                        System.out.printf(sPromptFormat, iMin, iMax);
                        uir.iValue = in.nextInt();
                        if ((uir.iValue >= iMin) && (uir.iValue <= iMax)) {
                            uir.bIsOK = true;
                            break;
                        } else {
                            System.out.printf(sOutOfRangeFormat, uir.iValue);
                            System.out.println();
                        }
                    }
                }
                catch (Exception ex){
                    uir.iValue = 0;
                    uir.bIsOK = false;
                }
                return uir;
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

            public void Run(){
                Scanner in = new Scanner(System.in);

                UserInputResultInt uir;

                // Ввод количества матриц
                uir = GetUserInputInt(in, cInputCountFormat, cMatrixCountMin, cMatrixCountMax, cOutputOutOfRange, -1);
                int iMatrixCount = uir.iValue;

                // Ввод ширины матрицы
                uir = GetUserInputInt(in, cInputWidthFormat, cMatrixSizeMin, cMatrixSizeMax, cOutputOutOfRange, -1);
                int iMatrixWidth = uir.iValue;

                // Ввод высоты матрицы
                uir = GetUserInputInt(in, cInputHeightFormat, cMatrixSizeMin, cMatrixSizeMax, cOutputOutOfRange, -1);
                int iMatrixHeight = uir.iValue;

                // Создание матриц
                double[][][] matrices = new double[iMatrixCount][iMatrixHeight][iMatrixWidth];

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
                    for (int row = 0; row < iMatrixHeight; row++) {
                        for (int col = 0; col < iMatrixWidth; col++) {
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
                            if (iMatrixHeight == iMatrixWidth) {
                                if (row == col) {
                                    dSumDiag1 += matrix[row][col];
                                }
                                if ((row + col) == (iMatrixWidth - 1)) {
                                    dSumDiag2 += matrix[row][col];
                                }
                            }

                        }
                        System.out.println();
                    }
                    System.out.printf(cOutputMinFormat, dMin);
                    System.out.println();
                    System.out.printf(cOutputMaxFormat, dMax);
                    System.out.println();
                    System.out.printf(cOutputAvgFormat, dSum / (iMatrixWidth * iMatrixHeight));
                    System.out.println();
                    if (iMatrixWidth == iMatrixHeight) {
                        System.out.printf(cOutputDiag1Format, dSumDiag1);
                        System.out.println();
                        System.out.printf(cOutputDiag2Format, dSumDiag2);
                        System.out.println();
                    }
                }
            }
        }

        MatrixWorker06 w = new MatrixWorker06();
        w.Run();
    }
}
