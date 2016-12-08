import java.util.ArrayList;

class Wallet {

    ArrayList<Double> dailyChangesSums = null;
    private ArrayList<Position> positions = null;

    Wallet() {
        dailyChangesSums = new ArrayList<>();
        positions = new ArrayList<>();
    }


    void addPosition(Position position) {
        positions.add(position);
    }

    Position getPosition(int id) {
        return positions.get(id);
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    int getSize() {
        return positions.size();
    }
}
