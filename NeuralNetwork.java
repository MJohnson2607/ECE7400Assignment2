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

    public NeuralNetwork(String str, int L)
    {
        layers.add(new NeuralNetLayer(LayerType.INPUT, 4));

        layers.get(0).neurons.add(new Neuron(1));
        layers.get(0).neurons.add(new Neuron(2));
        layers.get(0).neurons.add(new Neuron(3));
        layers.get(0).neurons.add(new Neuron(4));

        for (int i = 0; i < L; i++)
        {
            layers.add(new NeuralNetLayer(LayerType.INTERNAL, 5));

            layers.get(i+1).neurons.add(new Neuron(1));
            layers.get(i+1).neurons.add(new Neuron(2));
            layers.get(i+1).neurons.add(new Neuron(3));
            layers.get(i+1).neurons.add(new Neuron(4));
            layers.get(i+1).neurons.add(new Neuron(5));
        }

        layers.add(new NeuralNetLayer(LayerType.OUTPUT, 3));

        layers.get(L+1).neurons.add(new Neuron(1));
        layers.get(L+1).neurons.add(new Neuron(2));
        layers.get(L+1).neurons.add(new Neuron(3));

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

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (NeuralNetLayer LYR : layers)
        {
            LYR.run();
        }

    }
}