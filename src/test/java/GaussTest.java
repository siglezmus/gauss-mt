import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;
import java.util.Random;

public class GaussTest {

    @Test
    public void testGauss1(){
        //example1
        double[][] matrix = {{3,-2},
                              {5,1}};

        double[] rightSide = {-6, 3};

        double[] solution = {0,3};

        double[] result = Gauss.solve(matrix,rightSide);

        assertTrue(Arrays.equals(solution,result));

    }

    @Test
    public void testGauss2(){
        //example2

        double[][] matrix =
                {{1, -1, 3, 1},
                        {4, -1, 5, 4},
                        {2, -2, 4, 1},
                        {1, -4, 5, -1}};

        double[] rightSide = {5, 4, 6, 3};

        double[] solution = {9, 18, 10, -16};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.001);
    }

    @Test
    public void testGauss3(){
        //example2

        double[][] matrix =
                {{3, 2, -5},
                        {2, -1, 3},
                        {1, 2, -1}};

        double[] rightSide = {-1, 13, 9};

        double[] solution = {3, 5, 4};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.001);
    }

    @Test
    public void testGauss4(){
        //example2

        double[][] matrix =
                {{3, 1, -5},
                        {2, -1, 3},
                        {1, 2, -1}};

        double[] rightSide = {-1, 13, 9};

        double[] solution = {3.7, 4.3, 3.3};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.1);
    }


    @Test
    public void testGauss5(){
        //example2

        double[][] matrix =
                {{3, 2, -5},
                        {2, -1, 1},
                        {1, 2, -1}};

        double[] rightSide = {-1, 3, 9};

        double[] solution = {2.3, 5.2, 3.6};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.1);
    }


    @Test
    public void testGauss6(){
        //example2

        double[][] matrix =
                {{1, -1, 3, 1},
                        {4, -1, 5, 4},
                        {2, -2, 4, 1},
                        {1, -4, -5, -1}};

        double[] rightSide = {5, 1, 4, 3};

        double[] solution = {-6.3, -4.4, 0.8, 4.4};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.1);
    }


    @Test
    public void testGauss7(){
        //example2

        double[][] matrix =
                {{1, -1, 3, 1},
                        {1, -1, 69, 42},
                        {2, -2, 228, 504},
                        {1, -4, -666, -1}};

        double[] rightSide = {5, 1, 420, 3};

        double[] solution = {180.7, 174.5, -0.8, 1.2};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.1);
    }

    @Test
    public void testGauss8(){
        //example2

        double[][] matrix =
                {{24, 12, 1},
                        {29, 8, 99},
                        {17, 4, 1}};

        double[] rightSide = {-1, 13, 9};

        double[] solution = {1, -2.2, 0.001};

        double[] result = Gauss.solve(matrix,rightSide);

        assertArrayEquals(solution, result, 0.1);
    }

}
