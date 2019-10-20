package paketti;

import lejos.robotics.subsumption.Behavior;

/**
 * 
 * Rekan pitkän radan kaarrokset hoitava Behavior.
 *
 */
public class RekkaTurnBehavior implements Behavior {

	Gyro gyro = Rekka.getGyro();
	
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
		System.out.println("Rekka Turn");
		Rekka.gyrodirectionfinder.setDegrees(0);
		System.out.println("GyroHeadingDegrees: " + Rekka.gyrodirectionfinder.getDegrees());
		RekkaGyroInterceptBehavior.setGyroActive(true);
		//gyro.resetTotalAngle();
		//Rekka.setTargetHeading();
		//System.out.println("Total angle: " + gyro.getTotalAngle());
		//System.out.println("Current heading: " + Rekka.getCurrentHeading());
		Rekka.pilot.arcForward(45);
		while(!suppressed) Thread.yield();
		Rekka.pilot.stop();
		
	}
	public static void setStart() {
		start = true;
	}
}
