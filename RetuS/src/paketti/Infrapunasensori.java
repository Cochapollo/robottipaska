package paketti;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/*
 * Infrapunasensori mittaa etäisyyttä edessä oleviin kohteisiin. Metodilla liianLahella() voidaan palauttaa true, 
 * kun robotti havaitsee esteen 10cm päässä sensorista.
 */
public class Infrapunasensori {
    static final Port port = LocalEV3.get().getPort("S4");
    static final SensorModes sensor = new EV3IRSensor(port);
    static final SampleProvider distance = ((EV3IRSensor)sensor).getDistanceMode();
    static float[] sample = new float[distance.sampleSize()];
    		
    public Infrapunasensori() {
    	
    }
    //Palauttaa infrapunasensorin havaitseman etäisyyden sentteinä
    public float mittaaEtaisyys() {
    		distance.fetchSample(sample, 0);
    		return sample[0];
    }
    //Palauttaa true jos este on <= 10cm päässä robotista
    public boolean liianLahella() {
    	distance.fetchSample(sample, 0);
    	if(sample[0] < 10) {
    		
    		return true;
    	}
    	else return false;
    }
    
    // Tarkista sensoriportin toiminta. True jos portista löytyy anturi.
    public static boolean checkPortConnection() {
    	if(port != null) {
    		return true;
    	} else return false;
    }
}
