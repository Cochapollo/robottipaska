package RekanBehavior;

import lejos.robotics.subsumption.Behavior;
import paketti.Gyro;
import paketti.Rekka;

/**
 * 
 * Rekka ajaa hitaasti pitkää suoraa jonkin aikaa.
 *
 */
public class RekkaGyroInterceptBehavior implements Behavior {

	Gyro gyro = Rekka.getGyro();
	
	private volatile boolean suppressed = false;
	private static volatile boolean gyroActive = false;
	
	//Herää kun gyrosensori detectaa 180 asteen käännöksen.
	public boolean takeControl() {
		
		//True silloin kun rekka ajaa kaarteessa ja huomaa 180 asteen käännöksen
		if(gyroActive && Rekka.gyroHeadingReached()) {
			return true;
		} else return false;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		System.out.println("GyroIntercept");
		System.out.println("GyroHeadingDegrees: " + Rekka.getGyrodirectionfinder().getDegrees());
		//System.out.println("Total angle:" + gyro.getTotalAngle());
		//System.out.println("current heading:" + Rekka.getCurrentHeading());
		gyroActive = false;
		gyro.resetTotalAngle();
		//RekkaStraightBehavior.setStart();
	
	}
	public static void setGyroActive(boolean jew) {
		gyroActive = jew;
	}
}
