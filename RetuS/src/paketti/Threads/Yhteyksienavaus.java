package paketti.Threads;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.utility.Delay;
/**
 * 
 * @author Petri
 *Rekan portti autoon 1111 ja Rekan portti tietokoneeseen 9999
 */

public class Yhteyksienavaus{
	public static ServerSocket server = null, autoServer = null;
	public static Socket soc = null, autoS = null, rekkaS = null;
	public static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
	public static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
	public static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
	public static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
	public static ObjectOutputStream oOut = null, autoOout = null,rekkaOout = null;
	public static ObjectInputStream oIn = null, autoOin = null, rekkaOin = null;
	
/**
 * 
 */
public static void avaaTietokoneSocket() {
	try {
		
		server = new ServerSocket(9999);
		soc = server.accept();
		in = new BufferedInputStream(soc.getInputStream());
		
		Out = new BufferedOutputStream(soc.getOutputStream());
		oIn = new ObjectInputStream(in);
		
		String b = (String) oIn.readObject();
		System.out.println(b);
		
		oOut = new ObjectOutputStream(Out);
		oOut.flush();
		System.out.println("Connected Out");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void suljeTietokoneSocket() {
	try {
		Out.flush();
		Out.close();
		in.close();
		soc.close();
		server.close();
		
	;
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public static void avaaTietokoneDataStream() {
		
		try {
			
			
			dIn = new DataInputStream(in);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static void suljeTietokoneDataStream() {
	try {
		Out.close();
		dOut.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
/*public static void avaaTietokoneObjectStream() {
	
	try {
		
		
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
public static void suljeTietokoneObjectStream() {
	try {
		Out.flush();
		oOut.flush();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void avaaAutonSocket() {
	
	
		try {
			int kys = 1111;
			System.out.println(kys);
			autoServer = new ServerSocket(kys);
			autoS = autoServer.accept();
			autoIn = new BufferedInputStream(autoS.getInputStream());
			autoOut = new BufferedOutputStream(autoS.getOutputStream());
			autoOin = new ObjectInputStream(autoIn);
			String b = (String) autoOin.readObject();
			System.out.println(b);
			autoOout = new ObjectOutputStream(autoOut);
			autoOout.flush();
			System.out.println("Connected Out");
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
}
public static void suljeAutonSocket() {
	try {
		autoIn.close();
		autoOut.close();
		autoS.close();
		autoServer.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
//Avaa datainput sreamin autosta rekkaan
public static void avaaAutoDataStream() {
	try {
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void suljeAutoDataStream() {
	try {
		
		autoDin.close();
		autoDout.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void avaaAutoObjectOutStream() {
	try {
		autoOin = new ObjectInputStream(autoIn);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	
}
public static void suljeAutoObjectStream() {
	try {
		autoOout.close();
		autoOin.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


//Avaa socketin rekka lähettää tietoa autolle ja vastaanottaa
public static void avaaSocketAutoon() {
	
	try {
		rekkaS = new Socket("10.0.1.5",1111);
		rekkaOut = new BufferedOutputStream(rekkaS.getOutputStream());
		rekkaIn = new BufferedInputStream(rekkaS.getInputStream());
		rekkaOout = new ObjectOutputStream(rekkaOut);
		String b = "Connection ready";
		rekkaOout.writeObject(b);
		rekkaOout.flush();
		rekkaOin = new ObjectInputStream(in);
		
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
//Sulkee datainput socketit rekasta autoon
public static void suljeRekanSocketit() {
	try {
		rekkaOut.close();
		rekkaIn.close();
		rekkaS.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public static void avaaDataStreamRekka() {
	try {
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void suljeDataStreamRekka() {
	try {
		rekkaDin.close();
		rekkaDout.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public static void avaaObjectStreamInRekka() {
	try {
		rekkaOin = new ObjectInputStream(rekkaIn);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void suljeOhjectStreamRekka() {
	try {
		rekkaOin.close();
		rekkaOout.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	
}


