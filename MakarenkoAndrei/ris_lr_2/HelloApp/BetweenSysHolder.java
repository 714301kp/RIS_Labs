package HelloApp;

/**
* HelloApp/BetweenSysHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Hello.idl
* Sunday, March 15, 2020 2:13:39 PM MSK
*/

public final class BetweenSysHolder implements org.omg.CORBA.portable.Streamable
{
  public HelloApp.BetweenSys value = null;

  public BetweenSysHolder ()
  {
  }

  public BetweenSysHolder (HelloApp.BetweenSys initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HelloApp.BetweenSysHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HelloApp.BetweenSysHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HelloApp.BetweenSysHelper.type ();
  }

}
