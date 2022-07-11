import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Matrix
{
    double[][] weights;
    int rows;
    int cols;

    public Matrix(int R, int C, File F) throws FileNotFoundException
    {
        this.rows = R;
        this.cols = C;
        this.weights = new double[this.rows-1][this.cols];
        String str;
        String[] w;
        try (Scanner reader = new Scanner(F)) {
            int tracker = 0;
            while (reader.hasNextLine())
            {
                try {
                    str = reader.nextLine();
                    if (tracker >= 1 && tracker <= (this.rows-6))
                    {
                        w = str.split(",");
                        for (int j = 0; j < C; j++)
                        {
                            weights[tracker-1][j] = Double.parseDouble(w[j]);
                        }
                    }
                    else if (tracker >= (R-5))
                    {
                        w = str.split(",");
                        for (int j = 0; j < (C-2); j++)
                        {
                            weights[tracker-1][j] = Double.parseDouble(w[j]);
                        }
                    }

                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
                tracker++;
                if (tracker == this.rows)
                {
                    break;
                }
            }

        }
    }

    public double[][] getWeights()
    {
        return this.weights;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        try {
            File f = new File("weights.txt");
            Path path = Paths.get("weights.txt");
            long fileLength = Math.round(Files.lines(path).count());
            int lines = (int)fileLength;
            Matrix m = new Matrix(lines, 5, f);
            for (int i = 0; i < 24; i++)
            {
                System.out.println(Arrays.toString(m.weights[i]));
            }
        } catch (InputMismatchException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
