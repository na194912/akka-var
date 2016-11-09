import akka.actor.UntypedActor;

import java.util.ArrayList;


public class AkkaVarChild extends UntypedActor {

    public int myPosition;
    public Portfel portfel = null;

    public void onReceive(Object message) {
        if (message instanceof Integer) {
            myPosition = ((Integer)message).intValue();
        }
        else if (message instanceof Portfel) {
            portfel = (Portfel) message;
        }
        else if (message instanceof ArrayList) {
            System.out.println("CHILD "+myPosition+":  Otrzymalem szeregi normalne");
            ArrayList<ArrayList<Double>> szeregiNormalne = (ArrayList<ArrayList<Double>>)message;
            ArrayList<Double> skorelowaneZmianyCen = new ArrayList<>();
            ArrayList<Double> zmianyZadane = new ArrayList<>();

            /*for(int i = 0; i < 1000000; i++) {
                System.out.println(szeregiNormalne.get(1).get(i));
            }*/

            double temp = 0;
            for (int i = 0; i < 100000; i++) {
                temp =  szeregiNormalne.get(0).get(i) * Math.sqrt(portfel.getPozycja(0).wartoscWlasna) * portfel.getPozycja(0).wektorWlasny[myPosition]  +
                        szeregiNormalne.get(1).get(i) * Math.sqrt(portfel.getPozycja(1).wartoscWlasna) * portfel.getPozycja(1).wektorWlasny[myPosition]  +
                        szeregiNormalne.get(2).get(i) * Math.sqrt(portfel.getPozycja(2).wartoscWlasna) * portfel.getPozycja(2).wektorWlasny[myPosition];
                temp = temp * portfel.getPozycja(myPosition).volatility;
                skorelowaneZmianyCen.add(temp);
                zmianyZadane.add(portfel.getPozycja(myPosition).value * temp);
            }


            Para pozycjaZmianyZadane = new Para(myPosition,zmianyZadane);

            getSender().tell(pozycjaZmianyZadane,getSelf());


        }
        else {
            unhandled(message);
        }


    }

}
