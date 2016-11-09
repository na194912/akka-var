
public class Pozycja {

    public String name;
    public double value;
    public double volatility;
    public double wartoscWlasna;
    public double[] wektorWlasny;

    public Pozycja(String name, double value, double volatility, double wartoscWlasna, double[] wektorWlasny) {
        this.name = name;
        this.value = value;
        this.volatility = volatility;
        this.wartoscWlasna = wartoscWlasna;
        this.wektorWlasny = wektorWlasny;

    }

}
