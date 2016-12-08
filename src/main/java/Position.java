import java.util.ArrayList;

class Position {

    private String name;
    private int id;
    double value;
    double volatility;
    double eigenvalue;
    double[] eigenvector;
    public ArrayList<Double> correlatedPriceChanges = null;
    public ArrayList<Double> dailyChanges = null;

    Position(int id, String name, double value, double volatility, double eigenvalue, double[] eigenvector) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.volatility = volatility;
        this.eigenvalue = eigenvalue;
        this.eigenvector = eigenvector;

        correlatedPriceChanges = new ArrayList<>();
        dailyChanges = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public double getEigenvalue() {
        return eigenvalue;
    }

    public double[] getEigenvector() {
        return eigenvector;
    }

}
