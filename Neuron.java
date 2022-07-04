public class Neuron 
{
    boolean isInputLayer;
    int id, layer;
    double output;
    double[] inputWeights;

    public Neuron(int ID, int LYR, double[] W)
    {
        this.id = ID;
        this.layer = LYR;
        this.output = 0;
        this.inputWeights = W;
    }

    public void setInputLayer(boolean input)
    {
        this.isInputLayer = input;
    }

    public void start()
    {
        
    }
}