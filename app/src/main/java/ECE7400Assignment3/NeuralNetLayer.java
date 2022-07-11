import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

// Class representing a layer in an artificial neural network
public class NeuralNetLayer extends Thread
{
	// List of neurons in this layer
	Neuron[] neurons;

	// Any layer is either an input layer to the neural network, an internal layer, or an output layer
	// to the network
	LayerType layertype;

	// Cyclic Barrier
	CyclicBarrier barrier;

	// Input Array
	double[] inputs;

	// Constructor for neural network layer	
	public NeuralNetLayer(LayerType layertype, int L)
	{
		this.layertype = layertype;
		this.barrier = new CyclicBarrier(L);

        switch (layertype) {
        case INPUT:
            neurons = new Neuron[]{new Neuron(1), new Neuron(2), new Neuron(3), new Neuron(4)};
            break;
        case INTERNAL:
            neurons = new Neuron[]{new Neuron(1), new Neuron(2), new Neuron(3), new Neuron(4), new Neuron(5)};
            break;
        case OUTPUT:
            neurons = new Neuron[]{new Neuron(1), new Neuron(2), new Neuron(3)};
            break;
        }
	}

	@Override
	public void run()
	{
		System.out.println("\n imagine");
		for (int i = 0; i < neurons.length; i++)
		{
			// Neurons should know whether they expect their inputs from
			// the inputs to entire network or from outputs of previous layer
			if (layertype == LayerType.INPUT)
			{
				neurons[i].setInputLayer(true);
			}
			else
			{
				neurons[i].setInputLayer(false);
			}

			// Start threads for neurons in this layer
			neurons[i].start();
			try {
				// This may not be necesary - good to slow things down a bit
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
