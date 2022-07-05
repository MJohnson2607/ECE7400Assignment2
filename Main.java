import java.util.Arrays;
import java.util.concurrent.*;


public class Main 
{
    public static void main(String[] args)
    {
        NeuralNetwork network = new NeuralNetwork("weights.txt");
        Matrix temp = network.getWeights();
        for (int i = 0; i < (network.lines-1); i++)
        {
            System.out.println(Arrays.toString(temp.weights[i]));
        }
    }
}
