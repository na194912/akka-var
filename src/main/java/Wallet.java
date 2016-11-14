import java.util.ArrayList;

public class Wallet {

    public Position gold_;
    public Position pound_;
    public Position rand_;
    public int size = 3;
    public ArrayList<Double> dailyChangesSums = null;
    public ArrayList<Position> positions = null;


    public Wallet() {
        gold_ = new Position("gold", 2000000.0, 0.00553211774, 0.908, new double[]{0.687, 0.075, -0.723});
        pound_ = new Position("pound", 3000000.0, 0.00601238063, 1.123, new double[]{0.622, 0.453, 0.638});
        rand_ = new Position("rand", 1000000.0, 0.01121083082, 0.969, new double[]{0.375, -0.888, 0.265});

        dailyChangesSums = new ArrayList<>();

        positions = new ArrayList<>();
        positions.add(gold_);
        positions.add(pound_);
        positions.add(rand_);
    }

    public Position getPosition(int index) {
        return positions.get(index);
    }
}
