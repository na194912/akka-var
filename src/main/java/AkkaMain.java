import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class AkkaMain {

    static long actorSystemStarted;
    static int numberOfSimulations;
    static int numberOfChildActors;

    public static void main(String[] args) {

        String walletFileName = args[0];

        String sNumberOfSimulations = args[1];
        numberOfSimulations = Integer.parseInt(sNumberOfSimulations);

        String sNumberOfChildActors = args[2];
        numberOfChildActors = Integer.parseInt(sNumberOfChildActors);


        WalletParser wp = new WalletParser(walletFileName);
        Wallet wallet;
        wallet = wp.parseWallet();


        final ActorSystem system = ActorSystem.create("Akka-Var-Calculation");
        final ActorRef akkaVarMaster = system.actorOf(Props.create(AkkaVarMaster.class), "akkaVarMaster");

        actorSystemStarted = System.nanoTime();
        System.out.println("Actor System was created");



        akkaVarMaster.tell(wallet, ActorRef.noSender());

    }


}