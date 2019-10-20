package paketti.Threads;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
/**
 * 
 * @author petri
 *	Thread joka hakee infrapunasensorin tietoja
 */
public class InfrapunaThread extends Thread{
    static Port port = LocalEV3.get().getPort("S4");
    static Port port2 = LocalEV3.get().getPort("S2");
    static Port port3 = LocalEV3.get().getPort("S3");
    static SensorModes sensor = new EV3IRSensor(port);
    static SensorModes left = new EV3IRSensor(port3);
    static SensorModes right = new EV3IRSensor(port2);
    static SampleProvider distance = ((EV3IRSensor)sensor).getDistanceMode();
    static SampleProvider leftDistance = ((EV3IRSensor)left).getDistanceMode();
    static SampleProvider rightDistance = ((EV3IRSensor)right).getDistanceMode();
    static SampleProvider beacon = ((EV3IRSensor)sensor).getSeekMode();

    static float[] sample = new float[distance.sampleSize()];
    static float[] leftSample = new float[leftDistance.sampleSize()];
    static float[] rightSample = new float[rightDistance.sampleSize()];
    static float[] beaconSample = new float[beacon.sampleSize()];
    static volatile float x;
    static volatile float leftD, rightD;
    private boolean kappa = true;
    
    
    static int rekkaBeaconDirection;
    static int rekkaBeaconDistance;
    
  
    static int rampBeaconDirection;
    static int rampBeaconDistance;
    
    public InfrapunaThread() {
    	
    }
    /**
     * haetaan kolmen sensorin tiedot jotka tallennetaan taulukoihin
     */
    public void run() {
    	
    		while(kappa) {
    			
	    		distance.fetchSample(sample, 0);
	    		leftDistance.fetchSample(leftSample, 0);
	    		rightDistance.fetchSample(rightSample, 0);
	    		x = sample[0];
	    		leftD = leftSample[0];
	    		rightD = rightSample[0];
	    		getBeaconInfo();
	    		Thread.yield();
    	}
    }
    /**
     * Palauttaa true jos etu ir sensorin arvo on liian pieni
     * @return
     */
    public boolean liianLahella() {
    	if(x <= 10.0) {
    		return true;
    	}
    	else return false;
    }
    /**
     * tarkistaa vasemman ir sensorin etäisyyden ja palauttaa true jos ramppi on sen edessä
     * @return
     */
    public boolean leftRampNear() {
    	if(leftD < 16) {
    		return true;
    	}
    	else return false;
    }
    /**
     * tarkistaa oikean ir sensorin etäisyyden ja palauttaa true jos ramppi on sen edessä
     * @return
     */
    public boolean rightRampNear() {
    	if(rightD < 16) {
    		return true;
    	}
    	else return false;
    }
    /**
     * palauttaa etu ir sensorin arvon
     * @return
     */
    public float getDistance() {
    	return x;
    }
    /**
     * hakee tiedot ir beaconin olinpaikasta
     */
    public void getBeaconInfo() {
    	beacon.fetchSample(beaconSample, 0);
    	
		rekkaBeaconDirection = (int) beaconSample[0];
		rekkaBeaconDistance = (int) beaconSample[1];
		rampBeaconDirection = (int) beaconSample[2];
		rampBeaconDistance = (int) beaconSample[3];
    }
    /**
     * palauttaa vasemman sensorin tiedot
     * @return
     */
    public double getLeftDistance() {
    	return leftD;
    }
    /**
     * palauttaa oikean sensorin tiedot
     * @return
     */
    public double getRightDistance() {
    	return rightD;
    }
    /**
     * palauttaa suunnan jossa beacon on
     * @return
     */
    public int getRekkaBeaconDirection() {
    	return rekkaBeaconDirection;
    }
    /**
     * palauttaa etaisyyden beaconiin
     * @return
     */
    public int getRekkaBeaconDistance() {
    	return rekkaBeaconDistance;
    }
    /**
     * hakee 50 arvoa beacon sijainnista ja palauttaa keskiarvon niistä
     * @return
     */
    public double getRampBeaconDirection() {
    	
    	int avgDir = 0;
    	int counter = 0;
    	for(int i = 0; i < 50; i++) {
    		beacon.fetchSample(beaconSample, 0);
    		if(beaconSample[2] < 25 && beaconSample[2] > -25)
    		avgDir += beaconSample[2];
    		counter++;
    	}
    	avgDir = avgDir / counter;
    	return avgDir;
    }
/**
 * hakee 50 arvoa beacon etäisyydestä ja palauttaa keskiarvon niistä
 * @return
 */
    public double getRampBeaconDistance() {
    	int avgDis = 0;
    	int counter = 0;
    	for(int i = 0; i < 50; i++) {
    		beacon.fetchSample(beaconSample, 0);
    		if(beaconSample[3] < 100 && beaconSample[3] != 0)
    		avgDis += beaconSample[3];
    		counter++;
    	}
    	avgDis = avgDis / counter;
    	return avgDis;
    }
    public SensorModes getSensor() {
    	return sensor;
    }
    /**
     * lopettaa threadin
     */
    public void lopeta() {
    	kappa=false;
    }
}
