import java.util.concurrent.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class NeuralNetwork
{
    ArrayList<NeuralNetLayer> layers;

    int lines;

    Matrix weights;

    public NeuralNetwork(String str)
    {
        /*for (int i = 0; i < L; i++)
        {
            ArrayList<Neuron> neurons = new ArrayList<Neuron>();
            if (i == 0)
            {
                Neuron neuron1 = new Neuron(1);
                Neuron neuron2 = new Neuron(1);
                Neuron neuron3 = new Neuron(1);
                neurons.add(neuron1);
                neurons.add(neuron2);
                neurons.add(neuron3);

            }
        }*/

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
    }

    public Matrix getWeights()
    {
        return weights;
    }
}