package AutonBehaviorit;

import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

/**
 * 
 * Auton tehtävän tekemisen toteuttava Behavior.
 *
 */
public class AutoMissionBehavior implements Behavior {

	private volatile boolean suppressed = false;
	private static boolean start = false;
	private static boolean complete = false;

	public boolean takeControl() {
		
		return start;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		start = false;
		System.out.println("Mission start");
		
		//Auto.pilot.rotate(90);
		
		Delay.msDelay(2000);
		Sound.beep();
		//AutoInfraTriggerBehavior.setMonitoringActive(true);
		//Auto.pilot.travel(25);
		
		/*while (!suppressed) {
			Thread.yield();
			Auto.pilot.rotate(20);
		}
		*/
		AutoAlignRampBehavior.setStart();
	}
	public static void setStart() {
		start = true;
	}
	public static void setComplete(boolean c) {
		complete = c;
	}
}
