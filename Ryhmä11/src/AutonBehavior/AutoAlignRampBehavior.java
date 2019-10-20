package AutonBehavior;

import lejos.robotics.subsumption.Behavior;
import paketti.Auto;
import paketti.Threads.InfrapunaThread;

/**
 * 
 * Auton kohdistaminen rekan rampin suuntaisesti.
 *
 */
public class AutoAlignRampBehavior implements Behavior {

	private volatile boolean suppressed = false;
	private static boolean start = false;
	private static boolean rampNearby = false;
	private static boolean rampIRcheck = false;
	InfrapunaThread ir = Auto.getIr();
	private double rampBeaconDirection, rampBeaconDistance;
	public boolean takeControl() {
		
		return start;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		start = false;
		System.out.println("Ramp Alignment");
		rampIRcheck = false;
		rampNearby = false;
		
		/*while(!rampNearby) {
			rampBeaconDirection = ir.getRampBeaconDirection();
			rampBeaconDistance = ir.getRampBeaconDistance();
			System.out.println("Dir: " + rampBeaconDirection + " Dis: " + rampBeaconDistance);
			if(rampBeaconDirection == 0 && rampBeaconDistance == 0) {
				System.out.println("Dir: " + rampBeaconDirection + " Dis: " + rampBeaconDistance);
			}
			
			if (rampBeaconDirection >= 5) {
				Auto.pilot.travelArc(20, 15);
			}
			else if (rampBeaconDirection <= -5) {
				Auto.pilot.travelArc(-20, 15);
			}
			else if(rampBeaconDirection == 0) {
				while(rampBeaconDistance > 40) {
					Auto.pilot.travel(20);
				}
				rampNearby=true;
				break;
				
				
			}
		}*/
		
		while(!rampIRcheck) {
			System.out.println("L: " + ir.getLeftDistance() + " R: " + ir.getRightDistance());
			if(ir.leftRampNear() && ir.rightRampNear()) {
				rampIRcheck = true;
				break;
			}
			else if (!ir.leftRampNear() && !ir.rightRampNear()) {
				Auto.pilot.travel(15);
			}
			else if (ir.leftRampNear() && !ir.rightRampNear()) {
				//Auto.pilot.rotate(10);
				Auto.pilot.travelArc(20, 20);
				rampIRcheck=true;
				break;
				
			}
			else if (!ir.leftRampNear() && ir.rightRampNear()) {
				//Auto.pilot.rotate(-10);
				Auto.pilot.travelArc(-20, 20);
				rampIRcheck=true;
				break;
			}
		}

		AutoInfraStopBehavior.setMonitoringActive(true);
		AutoEnterRampBehavior.setStart();
		
	}
	public static void setStart() {
		start = true;
	}
	public static void setRampNearby (boolean jew) {
		rampNearby = jew;
	}
}
