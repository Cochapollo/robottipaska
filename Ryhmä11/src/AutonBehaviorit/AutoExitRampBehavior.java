package AutonBehaviorit;

import lejos.robotics.subsumption.Behavior;
import paketti.Auto;

/**
 * 
 * Auton poistumisen rekan lavetilta toteuttava Behavior.
 *
 */
public class AutoExitRampBehavior implements Behavior {

	private volatile boolean suppressed = false;
	private static boolean start = false;
	
	public boolean takeControl() {
		return start;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		
		// Auto peruuttaa alas rampilta
		
		Auto.exitRamp();
		System.out.println("ExitRamp");
		Auto.setStartPose();
		Auto.setStartHeading();
		System.out.println("X: " + Auto.getPoseprovider().getPose().getX());
		System.out.println("Y: " + Auto.getPoseprovider().getPose().getY());
		System.out.println("H: " + Auto.getPoseprovider().getPose().getHeading());
		
		
		//AutoInfraTriggerBehavior.setMonitoringActive(true);
		AutoMissionBehavior.setStart();
		start = false;
	}
	
	public static void setStart() {
		start = true;
	}
}
