public class Neuron extends Thread
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

    @Override
    public void run()
    {   
        System.out.println("\nDragons");
        // TODO Auto-generated method stub
        
    }
}