package paketti;

import AutonBehavior.AutoAlignRampBehavior;
import AutonBehavior.AutoEnterRampBehavior;
import AutonBehavior.AutoExitRampBehavior;
import AutonBehavior.AutoIdleBehavior;
import AutonBehavior.AutoInfraStopBehavior;
import AutonBehavior.AutoInfraTriggerBehavior;
import AutonBehavior.AutoMissionBehavior;
import AutonBehavior.AutoReturnToRekkaBehavior;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class AutoMains {
	
public static void main(String[] args) {
		
	
	
		Behavior b1 = new AutoIdleBehavior();
		Behavior b2 = new AutoMissionBehavior();
		Behavior b3 = new AutoReturnToRekkaBehavior();
		Behavior b4 = new AutoAlignRampBehavior();
		Behavior b5 = new AutoEnterRampBehavior();
		Behavior b6 = new AutoInfraTriggerBehavior();
		Behavior b7 = new AutoExitRampBehavior();
		Behavior b8 = new AutoInfraStopBehavior();
		
		
		Behavior[] bLista = {b1, b2, b3, b4, b5, b6, b7, b8};
		
		Arbitrator arbitrator = new Arbitrator(bLista, false);
		
		Auto.startupInit();
		
		arbitrator.go();
	
	}

}

