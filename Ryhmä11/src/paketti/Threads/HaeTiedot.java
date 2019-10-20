package paketti.Threads;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.robotics.navigation.Waypoint;
import lejos.utility.Delay;
import paketti.Rekka;
/**
 * 
 * @author petri
 *Threadi joka hakee objectstreamistä boolean taulokon rekalle
 */
public class HaeTiedot extends Thread{
	boolean kappa;
	Boolean[] mbol = {false,false,false,false};
	Waypoint p = new Waypoint(0, 0);
	public void run() {
		kappa = true;
		while(kappa) {
			try {
				Object obj = null;
				
				obj = Rekka.oIn.readObject();
				
				if (obj != null) {
					mbol = (Boolean[]) obj;
				} 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("EI LÖYDY CLASS");
				
			}catch(Exception e) {
				throw e;
			}
			
		}

	}
	/**
	 * Metodi jonka oli tarkoitus palauttaa waypointit jotta voimme lähettää sijainnin rekalta autolle
	 * @return
	 */
	public synchronized Waypoint getWaypoint() {
		return p;
	}
	/**
	 * Palauttaa boolean taulukon
	 * @return
	 */
	public synchronized Boolean[] getBooleans() {
		return mbol;
	}
	/**
	 * Settaa boolean taulukon
	 * @param b
	 */
	public synchronized void setBooleans(Boolean[] b) {
		this.mbol = b;
	}
	/**
	 * Metodi threadin lopettamiseen
	 */
	public void lopeta() {
		kappa = false;
	}
}
