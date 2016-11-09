import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class MultiMain {

    public static void printTime() {
        System.out.println(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
        );

    }

    public static void main(String[] args) {
        printTime();





        ArrayList<Double> snA = new ArrayList<>();
        ArrayList<Double> zlotoSkorelowaneZmianyCen = new ArrayList<>();
        ArrayList<Double> zlotoZmianyZadane = new ArrayList<>();
        Pozycja zloto_ = new Pozycja("zloto", 2000000.0, 0.00553211774, 0.908, new double[]{0.687, 0.075, -0.723});






        ArrayList<Double> snB = new ArrayList<>();
        ArrayList<Double> funtSkorelowaneZmianyCen = new ArrayList<>();
        ArrayList<Double> funtZmianyZadane = new ArrayList<>();
        Pozycja funt_ = new Pozycja("funt", 3000000.0, 0.00601238063, 1.123, new double[]{0.622, 0.453, 0.638});





        ArrayList<Double> snC = new ArrayList<>();
        ArrayList<Double> randSkorelowaneZmianyCen = new ArrayList<>();
        ArrayList<Double> randZmianyZadane = new ArrayList<>();
        Pozycja rand_ = new Pozycja("rand", 1000000.0, 0.01121083082, 0.969, new double[]{0.375, -0.888, 0.265});






        ArrayList<Double> calkowiteZmianyZadane = new ArrayList<>();
        Random r = new Random();


        for (int i = 0; i < 100000; i++) {
            snA.add(r.nextGaussian());
            snB.add(r.nextGaussian());
            snC.add(r.nextGaussian());
        }

        System.out.println("tutaj");
        printTime();

        double temp;
        for (int i = 0; i < 100000; i++) {
            temp =  snA.get(i) * Math.sqrt(zloto_.wartoscWlasna) * zloto_.wektorWlasny[0] * zloto_.volatility +
                    snB.get(i) * Math.sqrt(funt_.wartoscWlasna) * funt_.wektorWlasny[0] * zloto_.volatility +
                    snC.get(i) * Math.sqrt(rand_.wartoscWlasna) * rand_.wektorWlasny[0] * zloto_.volatility;
            zlotoSkorelowaneZmianyCen.add(temp);
            zlotoZmianyZadane.add(zloto_.value * temp);

            temp =  snA.get(i) * Math.sqrt(zloto_.wartoscWlasna) * zloto_.wektorWlasny[1] * funt_.volatility +
                    snB.get(i) * Math.sqrt(funt_.wartoscWlasna) * funt_.wektorWlasny[1] * funt_.volatility +
                    snC.get(i) * Math.sqrt(rand_.wartoscWlasna) * rand_.wektorWlasny[1] * funt_.volatility;
            funtSkorelowaneZmianyCen.add(temp);
            funtZmianyZadane.add(funt_.value * temp);

            temp =  snA.get(i) * Math.sqrt(zloto_.wartoscWlasna) * zloto_.wektorWlasny[2] * rand_.volatility +
                    snB.get(i) * Math.sqrt(funt_.wartoscWlasna) * funt_.wektorWlasny[2] * rand_.volatility +
                    snC.get(i) * Math.sqrt(rand_.wartoscWlasna) * rand_.wektorWlasny[2] * rand_.volatility;
            randSkorelowaneZmianyCen.add(temp);
            randZmianyZadane.add(rand_.value * temp);

            calkowiteZmianyZadane.add(zlotoZmianyZadane.get(i) + funtZmianyZadane.get(i) + randZmianyZadane.get(i));
        }

        printTime();

        Collections.sort(calkowiteZmianyZadane);
        double percentyl = calkowiteZmianyZadane.get(5000);
        System.out.println("Wynik " + percentyl);

        printTime();
    }
}
