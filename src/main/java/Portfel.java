public class Portfel {

    public Pozycja zloto_;
    public Pozycja funt_;
    public Pozycja rand_;



    public Portfel() {
        zloto_ = new Pozycja("zloto", 2000000.0, 0.00553211774, 0.908, new double[]{0.687, 0.075, -0.723});
        funt_ = new Pozycja("funt", 3000000.0, 0.00601238063, 1.123, new double[]{0.622, 0.453, 0.638});
        rand_ = new Pozycja("rand", 1000000.0, 0.01121083082, 0.969, new double[]{0.375, -0.888, 0.265});
    }

    public Pozycja getPozycja(int index) {
        if (index == 0) return zloto_;
        else if (index == 1) return funt_;
        else return rand_;
    }
}
