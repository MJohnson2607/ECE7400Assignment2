public class Neuron 
{
    boolean isInputLayer;
    int id, layer;
    double output;
    double[] inputWeights;

    public Neuron(int ID)
    {
        this.id = ID;
        this.output = 0;
    }

    public void setInputLayer(boolean input)
    {
        this.isInputLayer = input;
    }

    public void start()
    {
        
    }
}