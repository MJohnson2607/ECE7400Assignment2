import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

// Class representing a layer in an artificial neural network
public class NeuralNetLayer extends Thread
{
	// List of neurons in this layer
	ArrayList<Neuron> neurons;

	// Reference to previous layer
	NeuralNetLayer previous;

	// Any layer is either an input layer to the neural network, an internal layer, or an output layer
	// to the network
	LayerType layertype;

	// Cyclic Barrier
	CyclicBarrier barrier = new CyclicBarrier(neurons.size());

	// Constructor for neural network layer	
	public NeuralNetLayer(ArrayList<Neuron> neurons, LayerType layertype)
	{
		this.neurons = neurons;
		this.layertype = layertype;
	}
		
	@Override
	public void run() {
		for (int i = 0; i < neurons.size(); i++)
		{
			// Neurons should know whether they expect their inputs from
			// the inputs to entire network or from outputs of previous layer
			if (layertype == LayerType.INPUT)
			{
				neurons.get(i).setInputLayer(true);
			}
			else
			{
				neurons.get(i).setInputLayer(false);
			}

			// Start threads for neurons in this layer
			neurons.get(i).start();
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