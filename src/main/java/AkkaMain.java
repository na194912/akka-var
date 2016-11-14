import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class AkkaMain {

    public static Wallet wallet = new Wallet();
    public static long startTime;
    public static long actorSystemStarted;
    public static int numberOfSimulations = 1000000;
    public static int numberOfChildActors = 100;

    public static void main(String[] args) {
        startTime = System.nanoTime();




        final ActorSystem system = ActorSystem.create("Akka-Var-Calculation");

        final ActorRef akkaVarMaster = system.actorOf(Props.create(AkkaVarMaster.class), "akkaVarMaster");

        actorSystemStarted = System.nanoTime();
        System.out.println("Actor System was created");



        akkaVarMaster.tell(wallet, ActorRef.noSender());



    }

}