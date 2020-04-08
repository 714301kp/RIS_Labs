import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class HelloClient {
  static Hello helloImpl;
  static BetweenSys betweenSysImpl;

  public static void main(String args[]) {
    try{
      // создание и инициализация ORB
      ORB orb = ORB.init(args, null);
      // получение корня контекста имен
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      // опять используем INS
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
      // найдем объектную ссылку по имени

      betweenSysImpl = BetweenSysHelper.narrow(ncRef.resolve_str("BetweenSys"));

      System.out.println("Obtained a handle on server object: " + helloImpl);
      int process_code = 0;
      Scanner in = new Scanner(System.in);

      System.out.println(
          "Please write method name and text for crypt/decrypt:\n" +
          "Example: crypt # Hellow word!\n" +
          "For exit write: exit'"
        );
      while (process_code == 0 && in.hasNext()) {

          String string = in.nextLine();

          if (string == "exit") {
            process_code = 1;
          } else {
            System.out.println(betweenSysImpl.findMethod(string));
          }
      }
      in.close();

      helloImpl.shutdown();
    } catch (Exception e) {
      System.out.println("ERROR : " + e) ;
      e.printStackTrace(System.out);
    }

  }
}
