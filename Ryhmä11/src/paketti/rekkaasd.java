package paketti;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.hardware.Button;
import lejos.robotics.navigation.Waypoint;
import lejos.utility.Delay;
import paketti.Threads.*;

/**
 * Luokka jossa testasimme yhteyksien avausta tietokoneesta rekkaan ja rekasta autoon
 * @author petri
 *
 */
public class rekkaasd {

	public static ServerSocket server = null, autoServer = null;
	public static Socket soc = null, autoS = null, rekkaS = null;
	public static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
	public static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
	public static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
	public static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
	public static ObjectOutputStream oOut = null, autoOout = null,rekkaOout = null;
	public static ObjectInputStream oIn = null, autoOin = null, rekkaOin = null;
	static Boolean[] b = {false,false,false,false};
		public static void main(String[] args) {
			System.out.println("täällä");
			Yhteyksienavaus.avaaTietokoneSocket();
			
			
			HaeTiedot hae = new HaeTiedot();
			hae.start();
			 do{
				for(Boolean b : hae.getBooleans()) {
					System.out.println(b);
					Delay.msDelay(2000);
				}
			}while(!hae.getBooleans()[0]);
			
			
			hae.lopeta();
			
			try {
				Yhteyksienavaus.soc.close();
				Yhteyksienavaus.avaaSocketAutoon();
				Yhteyksienavaus.rekkaOout.writeObject(hae.getBooleans());
				Yhteyksienavaus.rekkaOout.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*try {
				
				server = new ServerSocket(9999);
				soc = server.accept();
				in = new BufferedInputStream(soc.getInputStream());
				
				Out = new BufferedOutputStream(soc.getOutputStream());
				oIn = new ObjectInputStream(in);
				
				b = (Boolean[]) oIn.readObject();
				
				for(Boolean b : b) {
					System.out.println(b);
					Delay.msDelay(2000);
				}
				
				
				
				
				oOut = new ObjectOutputStream(Out);
				b[1] = false;
				oOut.writeObject(b);
				oOut.flush();
				b = (Boolean[]) oIn.readObject();
				for(Boolean b : b) {
					System.out.println(b);
					Delay.msDelay(2000);
				}
				String boi = (String) oIn.readObject();
				System.out.println(boi);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				String boi = "asd";
				try {
					oOut.writeObject(boi);
					oOut.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			
			/*System.out.println("täällä");
			Yhteyksienavaus.avaaTietokoneSocket();
			//Yhteyksienavaus.avaaTietokoneObjectStream();
			//Yhteyksienavaus.avaaSocketAutoon();
			
			System.out.println("mutta ei täällä");
			HaeTiedot haeTiedot = new HaeTiedot();
			
			haeTiedot.start();
			

			do{
				int i = 0;
				
					for(Boolean b : haeTiedot.getBooleans()) {
						System.out.println(haeTiedot.getBooleans()[i]);
						Delay.msDelay(2000);
						i++;
					}
			
			}while(!haeTiedot.getBooleans()[3]);
			
			haeTiedot.lopeta();*/ 
			
			/* try {
				Boolean[] b = {false,false,false,true};
				
				Yhteyksienavaus.rekkaOout.writeObject(b);
				Yhteyksienavaus.rekkaOout.flush();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 try {
					Yhteyksienavaus.rekkaOut.close();
					
					
					Object obj = null;
					Yhteyksienavaus.avaaObjectStreamInRekka();
					obj = Yhteyksienavaus.rekkaOin.readObject();
					
					if (obj != null) {
						b = (Boolean[]) obj;
					} 
					
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 for(Boolean bi : b) {
					System.out.println(bi);
					Delay.msDelay(2000);
					
				}
			*/
	}
}
