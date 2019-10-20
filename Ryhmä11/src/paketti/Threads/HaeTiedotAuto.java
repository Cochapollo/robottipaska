package paketti.Threads;



import java.io.IOException;

import lejos.hardware.Button;
import lejos.robotics.navigation.Waypoint;
import lejos.utility.Delay;
import paketti.Auto;
/**
 * 
 * @author petri
 * Threadi joka hakee tiedot autolle rekalta
 */
public class HaeTiedotAuto extends Thread{
	boolean kappa;
	Boolean[] mbol = {false,false,false,false};
	
	public void run() {
		kappa = true;
		while(kappa) {
			try {
				Object obj = null;
				
				obj = Auto.autoOin.readObject();
				
				if (obj != null) {
					mbol = (Boolean[]) obj;
				} 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("EI LÖYDY CLASS");
				
			}
			
		}

	}
	
/**
 * Palauttaa boolean taulukon
 * @return
 */
	public synchronized Boolean[] getBooleans() {
		return mbol;
	}
	/**
	 * 
	 * @param b
	 */
	public synchronized void setBooleans(Boolean[] b) {
		this.mbol = b;
	}
	/**
	 * Lopettaa Threadin
	 */
	public void lopeta() {
		kappa = false;
	}
}
