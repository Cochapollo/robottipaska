package paketti;
import java.io.IOException;
import java.io.ObjectOutputStream;

import lejos.utility.Delay;
import paketti.Threads.Yhteyksienavaus;
public class autoasd {
	static Boolean[] mbol = {false,false,false,false};
	/**
	 * Testi luokka jossa testasimme yhteyden muodostamista auton ja rekan välille jota emme saaneet koskaan kunnolla toimimaan
	 * @param args
	 */
	public static void main(String[] args) {
			
		
		
		Yhteyksienavaus.avaaAutonSocket();
		try {
			mbol = (Boolean[]) Yhteyksienavaus.autoOin.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			Delay.msDelay(10000);
	
		
		/*HaeTiedotAuto haeTiedotAuto = new HaeTiedotAuto();
		haeTiedotAuto.start();
		do{
			int i = 0;
			
				for(Boolean b : haeTiedotAuto.getBooleans()) {
					System.out.println(haeTiedotAuto.getBooleans()[i]);
					Delay.msDelay(2000);
					i++;
				}
		
		}while(!haeTiedotAuto.getBooleans()[3]);
		

		haeTiedotAuto.lopeta();
		try {
			Yhteyksienavaus.autoOin.close();
			Yhteyksienavaus.autoOout = new ObjectOutputStream(Yhteyksienavaus.autoOut);
			mbol[0] = true;
			Yhteyksienavaus.autoOout.writeObject(mbol);
			Yhteyksienavaus.autoS.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}


