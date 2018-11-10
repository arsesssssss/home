import java.awt.peer.SystemTrayPeer;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.regex.*;
import java.util.Arrays;

public class matrix07 {

    public static void main(String[] args) {

        class MatrixWorker07 {
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

            class UserInputResultString {
                public UserInputResultString(){
                    sValue = "";
                    bIsOK = false;
                }
                public String sValue = "";
                public boolean bIsOK = false;
            }

            public UserInputResultString GetUserInputString(Scanner in, String sPrompt) {
                UserInputResultString uir = new UserInputResultString();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while (!uir.bIsOK) {
                        System.out.print(sPrompt);
                        uir.sValue = br.readLine();
                        uir.bIsOK = true;
                    }
                }
                catch (Exception ex){
                    uir.sValue = "";
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
            private static final String cInputValuesLineFormat = "Матрица %d, строка %d: введите %d значений и нажмите ВВОД: ";
            private static final String cOutputMinFormat = " Минимум: %8.1f";
            private static final String cOutputMaxFormat = "Максимум: %8.1f";
            private static final String cOutputAvgFormat = " Среднее: %8.1f";
            private static final String cOutputDiag1Format = "Сумма диагонали 1: %8.1f";
            private static final String cOutputDiag2Format = "Сумма диагонали 2: %8.1f";

            private static final String cMatrixItemsDelimiter = ",|;";

            public void Run(){
                Scanner in = new Scanner(System.in);

                UserInputResultInt uiri;
                UserInputResultString uirs;

                // Ввод количества матриц
                uiri = GetUserInputInt(in, cInputCountFormat, cMatrixCountMin, cMatrixCountMax, cOutputOutOfRange, -1);
                int iMatrixCount = uiri.iValue;

                // Ввод ширины матрицы
                uiri = GetUserInputInt(in, cInputWidthFormat, cMatrixSizeMin, cMatrixSizeMax, cOutputOutOfRange, -1);
                int iMatrixWidth = uiri.iValue;

                // Ввод высоты матрицы
                uiri = GetUserInputInt(in, cInputHeightFormat, cMatrixSizeMin, cMatrixSizeMax, cOutputOutOfRange, -1);
                int iMatrixHeight = uiri.iValue;

                // Создание матриц
                double[][][] matrices = new double[iMatrixCount][iMatrixHeight][iMatrixWidth];

                // В цикле выполняем построчный ввод значения матриц
                for (int nMatrix = 0; nMatrix < iMatrixCount; nMatrix++) {
                    double[][] matrix = matrices[nMatrix];

                    for (int row = 0; row < iMatrixHeight; row++) {
                        // запрос ввода строки с разделителями
                        String sPrompt = String.format(cInputValuesLineFormat, nMatrix, row, iMatrixWidth);
                        uirs = GetUserInputString(in, sPrompt);
                        // разбиение строки на подстроки
                        Pattern pattern = Pattern.compile(cMatrixItemsDelimiter);
                        String[] sStringValues = pattern.split(uirs.sValue);
                        int iStringValuesLen = sStringValues.length;
                        // преобразование подстрок в числа-значения
                        for (int col = 0; col < iMatrixWidth; col++){
                            if (col <= iStringValuesLen) {
                                try{
                                    matrix[row][col] = Double.parseDouble(sStringValues[col]);
                                }
                                catch(Exception ex){
                                    matrix[row][col] = 0;
                                }
                            }
                            else{
                                matrix[row][col] = 0;
                            }
                            System.out.printf(cOutputMatrixItemFormat, matrix[row][col]);
                        }
                        System.out.println();
                    }
                }

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
                            //matrix[row][col] = GetRandom(cMatrixItemMin, cMatrixItemMax);
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

        MatrixWorker07 w = new MatrixWorker07();
        w.Run();
    }
}
