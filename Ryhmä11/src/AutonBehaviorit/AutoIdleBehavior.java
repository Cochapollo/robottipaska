package AutonBehaviorit;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.subsumption.Behavior;
import paketti.Auto;
import paketti.Threads.InfrapunaThread;

/**
 * 
 * Behaviorlistan käynnissä pitävä idle-behavior. Aina käynnissä silloin kun auto on rekan lavetin päällä toimettomana.
 *
 */
public class AutoIdleBehavior implements Behavior {

	private volatile boolean suppressed = false;
	
	InfrapunaThread ir = Auto.getIr();
	public boolean takeControl() {
		
		// teoriassa aina valmiina idlaamaan
		return true;
	}
	
	public void suppress() {
		suppressed = true;
	}
	
	public void action() {
		suppressed = false;
		
		Auto.pilot.stop();
		System.out.println("Auto idle");
		while(!suppressed) {
		// TODO tänne joku listeneri tms. mikä odottaa rekalta starttikäskyä, jolloin asetetaan AutoExitRampBehaviorin starttiehto todeksi.
		
		
		while(Button.ENTER.isDown()) {
			AutoExitRampBehavior.setStart();
		}
		if(Auto.hae.getBooleans()[0]) {
			Auto.hae.lopeta();
			Boolean[] xd = {false,false,false,false};
			Auto.hae.setBooleans(xd);
			AutoExitRampBehavior.setStart();
		}
		while(Button.ESCAPE.isDown()) {
			System.exit(-1);
		}
	
	}
	}
}
