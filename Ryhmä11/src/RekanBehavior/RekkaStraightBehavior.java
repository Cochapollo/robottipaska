package RekanBehavior;

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

import lejos.robotics.subsumption.Behavior;
import paketti.Gyro;
import paketti.Rekka;
import paketti.Threads.Yhteyksienavaus;

/**
 * 
 * Rekka ajaa hitaasti pitkää suoraa jonkin aikaa.
 *
 */

public class RekkaStraightBehavior implements Behavior {
	
	Gyro gyro = Rekka.getGyro();
	
	private volatile boolean suppressed = false;
	private static boolean start = false;
	public static ServerSocket server = null, autoServer = null;
	public static Socket soc = null, autoS = null, rekkaS = null;
	public static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
	public static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
	public static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
	public static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
	public static ObjectOutputStream oOut = null, autoOout = null,rekkaOout = null;
	public static ObjectInputStream oIn = null, autoOin = null, rekkaOin = null;
	
	public boolean takeControl() {
		//Rekan alin behavior, aina true
		return start;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		start = false;
		System.out.println("Rekka Straight");
		try {
			rekkaS = new Socket("10.0.1.5",1111);
			rekkaOut = new BufferedOutputStream(rekkaS.getOutputStream());
			//rekkaIn = new BufferedInputStream(rekkaS.getInputStream());
			rekkaOout = new ObjectOutputStream(rekkaOut);
			rekkaOout.flush();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rekkaOout.writeObject(Rekka.getHae().getBooleans());
			rekkaOout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean[] xd = {false,false,false,false};
		Rekka.getHae().setBooleans(xd);
		Rekka.getPilot().travel(100);
		
		//while(!suppressed) Thread.yield();
		//Rekka.pilot.stop();
		//RekkaGyroInterceptBehavior.setGyroActive(true);
		//RekkaTurnBehavior.setStart();
	}
	public static void setStart() {
		start = true;
	}
}
