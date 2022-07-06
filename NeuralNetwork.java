import java.util.concurrent.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class NeuralNetwork implements Runnable
{
    ArrayList<NeuralNetLayer> layers = new ArrayList<NeuralNetLayer>();

    int lines;

    Matrix weights;
    static CountDownLatch[][] hiddenCountdownArray;

    public NeuralNetwork(String str, int L)
    {
        hiddenCountdownArray = new CountDownLatch[L][5];
        CountDownLatch[] outputCountdownArray = new CountDownLatch[3];
        int[][] hiddenCountdownArrayvalues = new int[L][5];

        try {
            File f = new File(str);
            Path path = Paths.get(str);
            long fileLength = Math.round(Files.lines(path).count());
            this.lines = (int)fileLength;
            this.weights = new Matrix(lines, 5, f);
        } catch (InputMismatchException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long[][] arrayWeights = getWeights();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (arrayWeights[i][j] != 0) {
                    hiddenCountdownArrayvalues[0][j]++;
                }
            }
        }

        for (int l = 1; l < L; l++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (arrayWeights[(l*5) + i - 1][j] != 0) {
                        hiddenCountdownArrayvalues[l][j]++;
                    }
                }
            }
        }

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < 5; j++) {
                hiddenCountdownArray[i][j] = new CountDownLatch(hiddenCountdownArrayvalues[i][j]);
            }
        }

        layers.add(new NeuralNetLayer(LayerType.INPUT, 4));

        for (int i = 0; i < L; i++)
        {
            layers.add(new NeuralNetLayer(LayerType.INTERNAL, 5));
        }

        layers.add(new NeuralNetLayer(LayerType.OUTPUT, 3));

        for (int i = 1; i < layers.size(); i++)
        {
            layers.get(i).addReferencetoPrevious(layers.get(i-1));
        }
    }

    public Matrix getWeights()
    {
        return weights;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (NeuralNetLayer LYR : layers)
        {
            LYR.run();
        }

    }
}
