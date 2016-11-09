import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class AkkaMain {

    public static Portfel portfel = new Portfel();

    public static void main(String[] args) {



        MultiMain.printTime();


        final ActorSystem system = ActorSystem.create("helloakka");
        final ActorRef akkaVarMaster = system.actorOf(Props.create(AkkaVarMaster.class), "akkaVarMaster");
        System.out.println("Actor System was created");

        akkaVarMaster.tell(portfel, ActorRef.noSender());
        


    }

}