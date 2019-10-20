package paketti;

import RekanBehavior.RekkaGyroInterceptBehavior;
import RekanBehavior.RekkaIdleBehavior;
import RekanBehavior.RekkaStraightBehavior;
import RekanBehavior.RekkaTurnBehavior;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class RekkaMain {
	
public static void main(String[] args) {
		
		Behavior b1 = new RekkaIdleBehavior();
		Behavior b2 = new RekkaStraightBehavior();
		Behavior b3 = new RekkaTurnBehavior();
		Behavior b4 = new RekkaGyroInterceptBehavior();
		
		Behavior[] bLista = {b1, b2, b3, b4};
		
		Arbitrator arbitrator = new Arbitrator(bLista, false);
		
		Rekka.startupInit();
		
		arbitrator.go();
		
	}

}
