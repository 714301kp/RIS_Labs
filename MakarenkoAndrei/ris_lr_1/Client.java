import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
  private Client() {}

  public static void main(String[] args) {
    String host = (args.length < 1) ? null : args[0];
    try {
      Registry registry = LocateRegistry.getRegistry(host);
      Hello stub = (Hello) registry.lookup("Hello");

      // String response = stub.sayHello();

      Scanner in = new Scanner(System.in);
      System.out.print("Input a string: ");
      String string = in.nextLine();
      in.close();

      String response = stub.removeSpaces(string);

      System.out.println("response: " + response);

      } catch (Exception e) {

      System.err.println("Client exception: " + e.toString());

      e.printStackTrace();

    }
  }
}
