import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.Collections;


class AkkaVarChild extends UntypedActor {

    private int myNumber;
    private Wallet wallet = null;

    public void onReceive(Object message) {

        if (message instanceof Integer) {
            myNumber = ((Integer)message).intValue();
        }

        else if (message instanceof Wallet) {
            wallet = (Wallet) message;
        }

        else if (message instanceof ArrayList) {

            ArrayList<ArrayList<Double>> listOfNormalSeries = (ArrayList<ArrayList<Double>>)message;


            double temp = 0;
            for (int i = 0; i < listOfNormalSeries.get(0).size(); i++) {
                temp = 0;
                for (int j = 0; j < listOfNormalSeries.size(); j++) {

                    for (int k = 0; k < listOfNormalSeries.size(); k++) {


                        temp += listOfNormalSeries.get(k).get(i) *
                                Math.sqrt(wallet.getPosition(k).getEigenvalue()) *
                                (wallet.getPosition(k).getEigenvector())[j];


                    }



                    temp = temp * wallet.getPosition(j).volatility;
                    wallet.getPosition(j).correlatedPriceChanges.add(temp);
                    wallet.getPosition(j).dailyChanges.add(wallet.getPosition(j).value * temp);

                }

                wallet.dailyChangesSums.add(  wallet.getPosition(0).dailyChanges.get(i) +
                                                    wallet.getPosition(1).dailyChanges.get(i) +
                                                    wallet.getPosition(2).dailyChanges.get(i));
            }



            Collections.sort(wallet.dailyChangesSums);

            Pair pDailyChangesSums = new Pair(myNumber, wallet.dailyChangesSums);

            getSender().tell(pDailyChangesSums, getSelf());
        }


        else {
            unhandled(message);
        }


    }

}
