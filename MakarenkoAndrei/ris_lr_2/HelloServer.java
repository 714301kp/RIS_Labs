import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.util.Base64;

class CryptSysImpl extends CryptSysPOA {
  private static Cipher ecipher;
  private static SecretKey key;

  public void setKey(SecretKey secretKey) {
    key = secretKey;
  }

  public String crypt(String str) {
    try {
      ecipher = Cipher.getInstance("DES");
      ecipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] utf8 = str.getBytes("UTF8");

      byte[] enc = ecipher.doFinal(utf8);
      return new String(Base64.getEncoder().encodeToString(enc));
    } catch (NoSuchAlgorithmException e) {
      return "No Such Algorithm:" + e.getMessage();
    } catch (NoSuchPaddingException e) {
      return "No Such Padding:" + e.getMessage();
    } catch (InvalidKeyException e) {
      return "Invalid Key:" + e.getMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}

class DecryptSysImpl extends DecryptSysPOA {
  private static Cipher dcipher;
  private static SecretKey key;

  public void setKey(SecretKey secretKey) {
    key = secretKey;
  }

  public String decrypt(String str) {
    try {
      // generate secret key using DES algorithm
      dcipher = Cipher.getInstance("DES");
      dcipher.init(Cipher.DECRYPT_MODE, key);

      // decode with base64 to get bytes
      byte[] dec = Base64.getDecoder().decode(str.getBytes());
      byte[] utf8 = dcipher.doFinal(dec);

      return new String(utf8, "UTF8");
    } catch (NoSuchAlgorithmException e) {
      return "No Such Algorithm:" + e.getMessage();
    } catch (NoSuchPaddingException e) {
      return "No Such Padding:" + e.getMessage();
    } catch (InvalidKeyException e) {
      return "Invalid Key:" + e.getMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}

class BetweenSysImpl extends BetweenSysPOA {
  private static SecretKey key;

  public void generateKey() {
    try {
      key = KeyGenerator.getInstance("DES").generateKey();
    } catch (NoSuchAlgorithmException e) {
      System.out.println("No Such Algorithm:" + e.getMessage());
    }
  }

  public String findMethod(String user_message) {
    CryptSysImpl cryptSysImpl = new CryptSysImpl();
    DecryptSysImpl decryptSysImpl = new DecryptSysImpl();
    cryptSysImpl.setKey(key);
    decryptSysImpl.setKey(key);
    String[] method_string = user_message.split("#");
    switch(method_string[0].trim()) {
      case "crypt":
        return cryptSysImpl.crypt(method_string[1].trim());
      case "decrypt":
        return decryptSysImpl.decrypt(method_string[1].trim());
      default:
        return "String invalid!";
    }
  }
}

class HelloImpl extends HelloPOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val;
  }

  public String sayHello() {
    return "\nHello world !!\n";
  }

  public void shutdown() {
    orb.shutdown(false);
  }
}

public class HelloServer {
  public static void main(String args[]) {
    try{
      // создание и инициализация ORB
      ORB orb = ORB.init(args, null);
      // получим ссылку на rootpoa и активируем POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
      // создадим сервант и зарегестрируем его в ORB
      HelloImpl helloImpl = new HelloImpl();
      BetweenSysImpl betweenSysImpl = new BetweenSysImpl();
      helloImpl.setORB(orb);
      betweenSysImpl.generateKey();

      //получим объектную ссылку от серванта
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(betweenSysImpl);

      BetweenSys between_href = BetweenSysHelper.narrow(ref);

      // получим корневой элемент контекста имен
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

      // используем NamingContextExt, что является частью спецификации //Interoperable Naming Service

      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // привязка объектной ссылки в сервисе имен

      NameComponent path[] = ncRef.to_name("BetweenSys");
      ncRef.rebind(path, between_href);

      System.out.println("HelloServer ready and waiting ...");

      // ожидаем вызовов от клиентов

      orb.run();

    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
    System.out.println("HelloServer Exiting ...");
  }
}
