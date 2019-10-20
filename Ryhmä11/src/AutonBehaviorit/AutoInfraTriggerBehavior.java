package AutonBehaviorit;

import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;
import paketti.Auto;
import paketti.Threads.InfrapunaThread;

/**
 * 
 * Infrapunasensorin reaktioita käsittelevä behavior.
 *
 */
public class AutoInfraTriggerBehavior implements Behavior {

	InfrapunaThread ir = Auto.getIr();
	private static boolean enemyKilled = false;
	private volatile boolean suppressed = false;
	private static volatile boolean irMonitorActive = false;

	//private static boolean start = false;
	public boolean takeControl() {
		
		if(irMonitorActive && Auto.getIr().liianLahella()) {
			return true;
		} else
		return false;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		System.out.println("Target found!");
		Auto.pilot.travel(30);
		Sound.beep();
		AutoReturnToRekkaBehavior.setStart();
		irMonitorActive = false;
	}
	public static void setMonitoringActive(boolean bo) {
		irMonitorActive = bo;
	}
	public static void setEnemyKilled(boolean b) {
		enemyKilled = b;
	}
	public static boolean getEnemyKilled() {
		return enemyKilled;
	}
}
