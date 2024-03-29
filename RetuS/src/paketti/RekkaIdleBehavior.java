package paketti;

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

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

/**
 * 
 * Behaviorlistan käynnissä pitävä idle-behavior. Aina käynnissä silloin kun auto on rekan lavetin päällä toimettomana.
 *
 */
public class RekkaIdleBehavior implements Behavior {

	private volatile boolean suppressed = false;
	public static ServerSocket server = null, autoServer = null;
	public static Socket soc = null, autoS = null, rekkaS = null;
	public static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
	public static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
	public static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
	public static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
	public static ObjectOutputStream oOut = null, autoOout = null,rekkaOout = null;
	public static ObjectInputStream oIn = null, autoOin = null, rekkaOin = null;
	//private Auto auto = new Auto();
	
	
	public boolean takeControl() {
		
		// teoriassa aina valmiina idlaamaan
		return true;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		Rekka.gyrodirectionfinder.setDegrees(0);
		Rekka.pilot.stop();
		System.out.println("Rekka idle");
		while(!suppressed) {
		
		while(Button.ENTER.isDown()) {
		RekkaStraightBehavior.setStart();
		}
		
		if(Rekka.hae.getBooleans()[0]) {
			Rekka.hae.lopeta();
			
			
			RekkaStraightBehavior.setStart();
		}
		while(Button.ESCAPE.isDown()) {
			System.exit(-1);
		}
		 
	
	}
	}
}
