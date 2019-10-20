package AutonBehaviorit;

import lejos.robotics.subsumption.Behavior;
import paketti.Auto;

/**
 * 
 * Auton ajamisen rekan lavetin päälle toteuttava Behavior.
 *
 */
public class AutoEnterRampBehavior implements Behavior {

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
		start = false;
		System.out.println("Enter Ramp");
		Auto.getPilot().setLinearAcceleration(50);
		Auto.getPilot().setLinearSpeed(30);
		Auto.getPilot().setAngularAcceleration(50);
		Auto.getPilot().setAngularSpeed(30);
		AutoInfraStopBehavior.setMonitoringActive(true);
		Auto.getPilot().forward();
		while(!suppressed) Thread.yield();
		Auto.getPilot().stop();
		start = false;
		}
	
	public static void setStart() {
		start = true;
	}
}
