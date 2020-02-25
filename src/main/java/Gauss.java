import java.util.ArrayList;
import java.util.Arrays;
import java.text.*;
/**
 * This implementation works without row interchange.
 */
public class Gauss {

    private static class Element {
        int row;
        int column;

        Element set(int row, int column) {
            this.row = row;
            this.column = column;
            return this;
        }
    }

    // Gauss-Jordan elimination with partial pivoting
    public static double[] solve(double[][] matrix, double[] rightSide) {
        int[] correspondence = new int[matrix.length];
        for (int i = 0 ; i < correspondence.length; i++) {
            correspondence[i] = i;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        System.out.println("Matrix before setting diagonal");
        printMatrix(matrix,rightSide);

        for (int column = 0; column < columns; column++) {
            Element diagonalElement = findBestUnusedElement(matrix, column);

            if (diagonalElement.row != column) {
                swapRows(matrix, diagonalElement.row, column);
                double temp;
                temp = rightSide[diagonalElement.row];
                rightSide[diagonalElement.row] = rightSide[column];
                rightSide[column] = temp;
            }

            if (diagonalElement.column != column) {
                //System.out.println("Columns swapped");
                swapColumns(matrix, diagonalElement.column, column);
                // We have swapped the answers (solutions) so we need to remember that
                int temp;
                temp = correspondence[diagonalElement.column];
                correspondence[diagonalElement.column] = correspondence[column] ;
                correspondence[column] = temp;
            }
            System.out.println("Matrix after setting diagonal");
            printMatrix(matrix,rightSide);


            ArrayList<Thread> threads = new ArrayList<>();
            threads.ensureCapacity(rows);



            double d = 1 / matrix[column][column];
            for (int col = 0; col < columns; col++) {
                matrix[column][col] *= d;
            }
            rightSide[column] *= d;


            double[] diagonal = Arrays.copyOf(matrix[column], matrix[column].length);

            //        printMatrix(matrix, rightSide);

            for (int row = column; row < rows; row++) {
                int finalRow = row;
                int finalColumn = column;

                double rightSideValue = rightSide[column];

                //System.out.println(rightSide[finalRow]);

                if (finalRow != column) {
                    Thread thread = new Thread(
                            () -> {
                                double diff = matrix[finalRow][finalColumn] / diagonal[finalColumn];
                                for (int col = 0; col < columns; col++) {
                                    matrix[finalRow][col] -= diagonal[col] * diff;
                                }
                                rightSide[finalRow] -= rightSideValue * diff;

                            });
                    thread.start();
                    threads.add(thread);
                }
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
            System.out.println("Matrix after iteration");
            printMatrix(matrix,rightSide);
            //printMatrix(matrix, rightSide);


            //System.out.println(Arrays.toString(correspondence));


        }
        double[] solutions = new double[rightSide.length];

        // Going backwards
        for (int iteration = matrix.length - 1; iteration > 0; iteration--) {
            for (int row = 0; row < iteration; row ++) {
                double diff = matrix[iteration][iteration] * matrix[row][iteration];
                matrix[row][iteration] -= matrix[iteration][iteration] * diff;
                rightSide[row] -= rightSide[iteration] * diff;
            }
        }
        System.out.println("Diagonal matrix");
        printMatrix(matrix,rightSide);

        // System.out.println(Arrays.toString(correspondence));

        for (int resultIndex = 0; resultIndex < solutions.length; resultIndex ++) {
            solutions[correspondence[resultIndex]] = rightSide[resultIndex];
        }

        return solutions;
    }
    
    public static Element findBestUnusedElement(double[][] matrix, int currentRow) {
        Element element = new Element().set(currentRow, 0);
        for (int row = currentRow; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (Math.abs(matrix[element.row][element.column]) < Math.abs(matrix[row][col])) {
                    element.set(row, col);
                }
            }
        }
        return element;
    }

    public static void swapRows(double[][] matrix, int i1, int i2) {
        double[] temp = matrix[i1];
        matrix[i1] = matrix[i2];
        matrix[i2] = temp;
    }

    public static void swapColumns(double[][] matrix, int i1, int i2) {
        for (int row = 0; row < matrix.length; row ++) {
            double temp = matrix[row][i1];
            matrix[row][i1] =  matrix[row][i2];
            matrix[row][i2] =  temp;
        }
    }

    public static void printMatrix(double[][] matrix, double[] complement) {
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for(int j = 0; j< matrix.length; j++){
                if(j+1 != matrix.length){
                    System.out.print(df.format(matrix[i][j]) + ", ");
                }
                else{
                    System.out.print(df.format(matrix[i][j]));
                }

            }
            System.out.println("] | " + df.format(complement[i]));
        }
    }

}
