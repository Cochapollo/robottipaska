package paketti.Threads;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.robotics.navigation.Waypoint;
import lejos.utility.Delay;
import paketti.Rekka;

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
	public synchronized Waypoint getWaypoint() {
		return p;
	}
	public synchronized Boolean[] getBooleans() {
		return mbol;
	}
	public synchronized void setBooleans(Boolean[] b) {
		this.mbol = b;
	}
	public void lopeta() {
		kappa = false;
	}
}
