import java.util.ArrayList;

public class Position {

    public String name;
    public double value;
    public double volatility;
    public double eigenvalue;
    public double[] eigenvector;
    public ArrayList<Double> correlatedPriceChanges = null;
    public ArrayList<Double> dailyChanges = null;

    public Position(String name, double value, double volatility, double eigenvalue, double[] eigenvector) {
        this.name = name;
        this.value = value;
        this.volatility = volatility;
        this.eigenvalue = eigenvalue;
        this.eigenvector = eigenvector;

        correlatedPriceChanges = new ArrayList<>();
        dailyChanges = new ArrayList<>();

    }

}
