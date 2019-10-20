package AutonBehaviorit;

import java.sql.Savepoint;

import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;
import paketti.Auto;
import paketti.Threads.InfrapunaThread;

/**
 * 
 * Infrapunasensorin reaktioita käsittelevä behavior.
 *
 */
public class AutoInfraStopBehavior implements Behavior {

	InfrapunaThread ir = Auto.getIr();
	private volatile boolean suppressed = false;
	private static volatile boolean irMonitorActive = false;

	//private static boolean start = false;
	public boolean takeControl() {
		
		if(irMonitorActive && ir.liianLahella()) {
			return true;
		} else
		return false;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		Auto.pilot.stop();
		irMonitorActive = false;
		Auto.pilot.stop();
	
	}
	public static void setMonitoringActive(boolean kys) {
		irMonitorActive = kys;
	}
}
