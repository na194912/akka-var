import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AkkaVarMaster extends UntypedActor {

    public List<ActorRef> children = new ArrayList<>();
    public Portfel portfel = null;
    public ArrayList<ArrayList<Double>> szeregiNormalne = new ArrayList<>();
    public int zmianyZadaneCounter = 0;
    ArrayList<Double> calkowiteZmianyZadane = new ArrayList<>();

    public AkkaVarMaster() {

        for (int i = 0; i < 3; i++) {
            ActorRef child = context().actorOf(Props.create(AkkaVarChild.class));
            children.add(child);
            //
            context().watch(child);
        }
    }

    public void onReceive(Object message) {

        if (message instanceof Portfel) {

            portfel = (Portfel)message;

            for(int i = 0; i < 100000; i++) {
                calkowiteZmianyZadane.add(0.0);
            }

            Random r = new Random();
            for (int i = 0; i < children.size(); i++) {
                ArrayList<Double> szeregNormalny = new ArrayList<>();
                for(int j = 0; j < 100000; j++) {
                    szeregNormalny.add(r.nextGaussian());
                }
                szeregiNormalne.add(szeregNormalny);
            }



            for (int i = 0; i < children.size(); i++) {
                children.get(i).tell(new Integer(i), getContext().self());
                children.get(i).tell((Portfel) message, getContext().self());
                children.get(i).tell(szeregiNormalne, getContext().self());
            }
        }
        else if (message instanceof Para) {
            zmianyZadaneCounter++;
            Para para = (Para)message;
            System.out.println("MASTER: Otrzymalem zmiany zadane z pozycji " + para.pozycja);
            for (int i = 0; i < 100000; i++) {
                calkowiteZmianyZadane.set(i, calkowiteZmianyZadane.get(i) + para.zmianyZadane.get(i));
            }

            if (zmianyZadaneCounter == 3) {
                System.out.println("MASTER: Mam calkowite zmiany zadane");
                Collections.sort(calkowiteZmianyZadane);
                double percentyl = calkowiteZmianyZadane.get(5000);
                System.out.println("MASTER: VaR " + Math.abs(percentyl) + " USD.");
                MultiMain.printTime();
            }

        }
        else {
            unhandled(message);
        }
    }


}
