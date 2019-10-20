package paketti;

import lejos.robotics.subsumption.Behavior;

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
		Auto.pilot.setLinearAcceleration(50);
		Auto.pilot.setLinearSpeed(30);
		Auto.pilot.setAngularAcceleration(50);
		Auto.pilot.setAngularSpeed(30);
		AutoInfraStopBehavior.setMonitoringActive(true);
		Auto.pilot.forward();
		while(!suppressed) Thread.yield();
		Auto.pilot.stop();
		start = false;
		}
	
	public static void setStart() {
		start = true;
	}
}
