package HelloApp;


/**
* HelloApp/CryptSysHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Hello.idl
* Sunday, March 15, 2020 2:13:39 PM MSK
*/

abstract public class CryptSysHelper
{
  private static String  _id = "IDL:HelloApp/CryptSys:1.0";

  public static void insert (org.omg.CORBA.Any a, HelloApp.CryptSys that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HelloApp.CryptSys extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (HelloApp.CryptSysHelper.id (), "CryptSys");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HelloApp.CryptSys read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CryptSysStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HelloApp.CryptSys value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static HelloApp.CryptSys narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HelloApp.CryptSys)
      return (HelloApp.CryptSys)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HelloApp._CryptSysStub stub = new HelloApp._CryptSysStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static HelloApp.CryptSys unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HelloApp.CryptSys)
      return (HelloApp.CryptSys)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HelloApp._CryptSysStub stub = new HelloApp._CryptSysStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
