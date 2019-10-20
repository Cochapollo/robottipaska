package RekanBehavior;

import lejos.robotics.subsumption.Behavior;
import paketti.Gyro;
import paketti.Rekka;

/**
 * 
 * Gyrosensorin arvoja seuraava behavior. Aktivoituu ja lopettaa rekan kaartamisen kun rekka on kääntynyt 180 astetta.
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
		gyroActive = false;
		gyro.resetTotalAngle();
		RekkaStraightBehavior.setStart();
	
	}
	public static void setGyroActive(boolean b) {
		gyroActive = b;
	}
}
