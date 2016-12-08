import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class AkkaVarMaster extends UntypedActor {

    private List<ActorRef> children = new ArrayList<>();
    private Wallet wallet = null;
    private int childrenCompletedCount = 0;
    private ArrayList<Double> allChangesSums = new ArrayList<>();

    public AkkaVarMaster() {

        for (int i = 0; i < AkkaMain.numberOfChildActors; i++) {
            ActorRef child = context().actorOf(Props.create(AkkaVarChild.class));
            children.add(child);
            context().watch(child);
        }
    }

    public void onReceive(Object message) {

        if (message instanceof Wallet) {

            wallet = (Wallet)message;

            double howManyForChild = (double) AkkaMain.numberOfSimulations / (double)children.size();
            int howManyForFirstChildren = (int)Math.ceil(howManyForChild);
            int howManyForLastChild = AkkaMain.numberOfSimulations - (children.size()-1) * howManyForFirstChildren;

            Random r = new Random();
            for (int ch = 0; ch < children.size()-1; ch++) {

                ArrayList<ArrayList<Double>> listOfNormalSeries = new ArrayList<>();
                for (int p = 0; p < wallet.getSize(); p++) {
                    ArrayList<Double> normalSeries = new ArrayList<>();
                    for (int i = 0; i < howManyForFirstChildren; i++) {
                        normalSeries.add(r.nextGaussian());
                    }
                    listOfNormalSeries.add(normalSeries);
                }


                Cloner cloner = new Cloner();
                Wallet walletClone =  cloner.deepClone(wallet);




                children.get(ch).tell(new Integer(ch), getContext().self());
                children.get(ch).tell(walletClone, getContext().self());
                children.get(ch).tell(listOfNormalSeries, getContext().self());

            }

            ArrayList<ArrayList<Double>> listOfNormalSeries = new ArrayList<>();
            for (int p = 0; p < wallet.getSize(); p++) {
                ArrayList<Double> normalSeries = new ArrayList<>();
                for (int i = 0; i < howManyForLastChild; i++) {
                    normalSeries.add(r.nextGaussian());
                }
                listOfNormalSeries.add(normalSeries);
            }

            Cloner cloner = new Cloner();
            Wallet walletClone =  cloner.deepClone(wallet);

            children.get(children.size()-1).tell(new Integer(children.size()-1), getContext().self());
            children.get(children.size()-1).tell(walletClone, getContext().self());
            children.get(children.size()-1).tell(listOfNormalSeries, getContext().self());




        }


        else if (message instanceof Pair) {
            childrenCompletedCount++;
            Pair pair = (Pair)message;

            allChangesSums.addAll(pair.series);


            if (childrenCompletedCount == AkkaMain.numberOfChildActors) {
                System.out.println("MASTER: I've got all changes' sums");
                Collections.sort(allChangesSums);
                double percentyl = allChangesSums.get(AkkaMain.numberOfSimulations/20);
                System.out.println("MASTER: VaR " + Math.abs(percentyl) + " USD.");


                long endTime = System.nanoTime();
                double programTime = (endTime - AkkaMain.actorSystemStarted)/1e6;
                System.out.println(" PROGRAM TIME = " + programTime);

                System.exit(0);

            }



        }
        else {
            unhandled(message);
        }
    }


}
