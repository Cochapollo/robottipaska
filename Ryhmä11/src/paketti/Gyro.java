package paketti;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.Gyroscope;
import lejos.robotics.SampleProvider;

public class Gyro extends Thread implements Gyroscope{
	
	static final Port port = LocalEV3.get().getPort("S1");
	static final HiTechnicGyro sensor = new HiTechnicGyro(port);
	static final SampleProvider sp = sensor.getRateMode();
	static float[] sample = new float[sp.sampleSize()];
	static private double offset;
	static private boolean active = true;
	static private double totalAngle = 0;
	

	/**
	 * Creates GyroSensor object. This is a wrapper class for EV3GyroSensor.
	 * 
	 * @param port SensorPort of EV3GyroSensor device.
	 */
	public Gyro() {
		
	}
	public void run() {
    	
		while(active) {		
    		getTotalAngle();
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean detectTurnComplete() {
		if(totalAngle >= 30) {
			//System.out.println("detectTurnComplete true");
			return true;
		} else return false;
	}

	/**
	 * Returns the underlying EV3GryoSensor object.
	 * 
	 * @return Sensor object reference.
	 */
	public HiTechnicGyro getSensor() {
		return sensor;
	}

	/**
	 * Return the current angular velocity from the gyro.
	 * 
	 * @return The angular velocity in degrees/second. Negative if turning right.
	 */
	
	public float getAngularVelocity() {
		sp.fetchSample(sample, 0);
		return sample[0];
	}

	/**
	 * Return the current accumulated angle from starting point from the gyro.
	 * 
	 * @return The accumulated angle in degrees. Negative if turning right past
	 *         zero.
	 */
	
	public int getAngle() {
		sp.fetchSample(sample, 0);
		return (int) ((sample[0] - offset) * 0.01);
	}
	public double getTotalAngle() {
		totalAngle += getAngle();
		//System.out.println("Total: " + totalAngle);
		return totalAngle;
	}

	/**
	 * Reset angle to zero.
	 */
	
	public void reset() {
		
		int jew = 0;
		for(int i = 0; i < 100; i++) {
			sp.fetchSample(sample, 0);
			jew += sample[0];
		}
		
		offset = (jew / 100);
		
		
	}
	/**
	 * Release resources.
	 */
	public void close() {
		sensor.close();
	}
	
	public static void stopGyro() {
		active = false;
	}
	
	public void recalibrateOffset() {
		sp.fetchSample(sample, 0);
		offset = sample[0];
	}
	public void resetTotalAngle() {
		totalAngle = 0;
	}
}