package AutonBehaviorit;

import lejos.robotics.subsumption.Behavior;
import paketti.Auto;
import paketti.Threads.InfrapunaThread;

/**
 * 
 * Auton rekan luo palaamisen toteuttava Behavior.
 *
 */
public class AutoReturnToRekkaBehavior implements Behavior {

	private volatile boolean suppressed = false;
	private static boolean start = false;
	InfrapunaThread ir = Auto.getIr();
	public boolean takeControl() {
		
		if(start)
		return true;
		else return false;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		
		System.out.println("X: " + Auto.getPoseprovider().getPose().getX());
		System.out.println("Y: " + Auto.getPoseprovider().getPose().getY());
		Auto.getNavigator().goTo(10, 10, Auto.getStartHeading());
		Auto.getNavigator().waitForStop();
		System.out.println("X: " + Auto.getPoseprovider().getPose().getX());
		System.out.println("Y: " + Auto.getPoseprovider().getPose().getY());
	
		start = false;
		AutoAlignRampBehavior.setStart();
	
	}
	public static void setStart() {
		start = true;
	}
}
