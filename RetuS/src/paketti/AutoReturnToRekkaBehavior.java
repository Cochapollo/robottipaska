package paketti;

import lejos.robotics.subsumption.Behavior;

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
		
		System.out.println("X: " + Auto.poseprovider.getPose().getX());
		System.out.println("Y: " + Auto.poseprovider.getPose().getY());
		Auto.navigator.goTo(10, 10, Auto.startHeading);
		Auto.navigator.waitForStop();
		System.out.println("X: " + Auto.poseprovider.getPose().getX());
		System.out.println("Y: " + Auto.poseprovider.getPose().getY());
	
		start = false;
		AutoAlignRampBehavior.setStart();
	
	}
	public static void setStart() {
		start = true;
	}
}
