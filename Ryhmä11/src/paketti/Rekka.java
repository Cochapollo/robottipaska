package paketti;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.Gyroscope;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.CompassPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Pose;
import lejos.utility.GyroDirectionFinder;
import paketti.Threads.HaeTiedot;
import paketti.Threads.Yhteyksienavaus;

public class Rekka {

	
	// Moottoriportit
	static RegulatedMotor rearLeft = new EV3LargeRegulatedMotor(MotorPort.A);
	static RegulatedMotor frontLeft = new EV3LargeRegulatedMotor(MotorPort.B);
	static RegulatedMotor rearRight = new EV3LargeRegulatedMotor(MotorPort.C);
	static RegulatedMotor frontRight = new EV3LargeRegulatedMotor(MotorPort.D);

	
	//  Chassis
	static Wheel wheel1 = WheeledChassis.modelWheel(rearLeft, 4.23).offset(8.5).invert(true);
	static Wheel wheel2 = WheeledChassis.modelWheel(rearRight, 4.23).offset(-8.5).invert(true);
	static Wheel wheel3 = WheeledChassis.modelWheel(frontLeft, 4.23).offset(8.5);
	static Wheel wheel4 = WheeledChassis.modelWheel(frontRight, 4.23).offset(-8.5);
	static Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2, wheel3, wheel4 }, WheeledChassis.TYPE_DIFFERENTIAL);
	public static MovePilot pilot = new MovePilot(chassis);
	static PoseProvider poseprovider = chassis.getPoseProvider();
	static Pose startPose = new Pose(0, 0, 0);
	public static ServerSocket server = null, autoServer = null;
	public static Socket soc = null, autoS = null, rekkaS = null;
	public static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
	public static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
	public static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
	public static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
	public static ObjectOutputStream oOut = null, autoOout = null,rekkaOout = null;
	public static ObjectInputStream oIn = null, autoOin = null, rekkaOin = null;
	
	static private Gyro gyro = new Gyro();
	private static GyroDirectionFinder gyrodirectionfinder;
	static float targetHeading = 180;
	private static HaeTiedot hae = new HaeTiedot();
	public Rekka() {
		
	}
	
	
	//Aseta rekan vauhdit hitaiksi
	public static void startupInit() {
		getPilot().setLinearAcceleration(10);
		getPilot().setAngularAcceleration(10);

		getPilot().setLinearSpeed(5);
		getPilot().setAngularSpeed(5);
		//gyro.start();
		//gyro.reset();
		setGyrodirectionfinder(new GyroDirectionFinder(gyro, true));
		try {
			server = new ServerSocket(9999);
			soc = server.accept();
			in = new BufferedInputStream(soc.getInputStream());
			
			Out = new BufferedOutputStream(soc.getOutputStream());
			oIn = new ObjectInputStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getHae().start();
	}
	

	
	// Aja eteenpäin suoraan. Tän vois korvata jollain fiksummalla. Gyroa inee.
	public static void driveStraight() {
		
		double suunta = poseprovider.getPose().getHeading();
		double target = poseprovider.getPose().getX();
		if(suunta < 30 && suunta > -30) {
			target = poseprovider.getPose().getX() + 200;
		} else if(suunta < 210 && suunta > 150) {
			target = poseprovider.getPose().getX() - 200;
		}
		
		
		while(poseprovider.getPose().getX() != target) {
			getPilot().forward();
		}
		getPilot().stop();
	}
	
	//Alusta startpose kun auto poistuu rekan kyydistä. Tätä tarvitaan auton navigointiin ...ehkä?
	public static void setStartPose() {
		
		poseprovider.setPose(startPose);
		
		}
	public static Pose getCurrentPose() {
		return poseprovider.getPose();
	}
	
	
	
	public static Gyro getGyro() {
		return gyro;
	}
	public static boolean gyroHeadingReached() {
		if(getGyrodirectionfinder().getDegrees() >= 320) {
			return true;
		}
		else return false;
	}


	public static GyroDirectionFinder getGyrodirectionfinder() {
		return gyrodirectionfinder;
	}


	public static void setGyrodirectionfinder(GyroDirectionFinder gyrodirectionfinder) {
		Rekka.gyrodirectionfinder = gyrodirectionfinder;
	}


	public static HaeTiedot getHae() {
		return hae;
	}


	public static void setHae(HaeTiedot hae) {
		Rekka.hae = hae;
	}


	public static MovePilot getPilot() {
		return pilot;
	}


	public static void setPilot(MovePilot pilot) {
		Rekka.pilot = pilot;
	}
}

