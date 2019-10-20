package paketti.Threads;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

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
    
    // IR beacon channel 1 -- ei sittenkään käytetä mihinkään
    static int rekkaBeaconDirection;
    static int rekkaBeaconDistance;
    
    // IR beacon channel 2
    static int rampBeaconDirection;
    static int rampBeaconDistance;
    
    public InfrapunaThread() {
    	
    }
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
    public boolean liianLahella() {
    	if(x <= 10.0) {
    		return true;
    	}
    	else return false;
    }
    public boolean leftRampNear() {
    	if(leftD < 16) {
    		return true;
    	}
    	else return false;
    }
    public boolean rightRampNear() {
    	if(rightD < 16) {
    		return true;
    	}
    	else return false;
    }
    public float getDistance() {
    	return x;
    }
    public void getBeaconInfo() {
    	beacon.fetchSample(beaconSample, 0);
    	
		rekkaBeaconDirection = (int) beaconSample[0];
		rekkaBeaconDistance = (int) beaconSample[1];
		rampBeaconDirection = (int) beaconSample[2];
		rampBeaconDistance = (int) beaconSample[3];
    }
    public double getLeftDistance() {
    	return leftD;
    }
    public double getRightDistance() {
    	return rightD;
    }
    /**
     * 
     * @return
     */
    public int getRekkaBeaconDirection() {
    	return rekkaBeaconDirection;
    }
    public int getRekkaBeaconDistance() {
    	return rekkaBeaconDistance;
    }
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
    
    public void lopeta() {
    	kappa=false;
    }
}
