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

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;
import paketti.Threads.HaeTiedotAuto;
import paketti.Threads.InfrapunaThread;
/**
 * Auton alustamiseen tehty luokka
 * @author petri
 *
 */
public class Auto {


		/**
		 *  Moottorit
		 */
		static final RegulatedMotor rearLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		static final RegulatedMotor frontLeft = new EV3LargeRegulatedMotor(MotorPort.C);
		static final RegulatedMotor rearRight = new EV3LargeRegulatedMotor(MotorPort.B);
		static final RegulatedMotor frontRight = new EV3LargeRegulatedMotor(MotorPort.D);
		
		 /**
		  * Auton chassis
		  */
		static final Wheel wheel1 = WheeledChassis.modelWheel(rearLeft, 4.23).offset(23.5).invert(true);
		static final Wheel wheel2 = WheeledChassis.modelWheel(rearRight, 4.23).offset(-23.5).invert(true);
		static final Wheel wheel3 = WheeledChassis.modelWheel(frontLeft, 4.23).offset(23.5);
		static final Wheel wheel4 = WheeledChassis.modelWheel(frontRight, 4.23).offset(-23.5);
		static final Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2, wheel3, wheel4 }, WheeledChassis.TYPE_DIFFERENTIAL);
		public static MovePilot pilot = new MovePilot(chassis);
		
		private static PoseProvider poseprovider = chassis.getPoseProvider();
		private static Navigator navigator = new Navigator(getPilot());
		static Pose startPose = new Pose(10, 10, 0);
		private static float startHeading = 0;
		static Kartta kartta = new Kartta();
		static ShortestPathFinder pathfinder = new ShortestPathFinder(kartta.getKartta());
		static private InfrapunaThread ir = new InfrapunaThread();
		public static ServerSocket server = null, autoServer = null;
		public static Socket soc = null, autoS = null, rekkaS = null;
		public static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
		public static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
		public static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
		public static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
		public static ObjectOutputStream oOut = null, autoOout = null,rekkaOout = null;
		public static ObjectInputStream oIn = null, autoOin = null, rekkaOin = null;
		
		static boolean moving = false;
		private static HaeTiedotAuto hae = new HaeTiedotAuto();
		
		
		public Auto() {
			
		}
		
		/**
		 * Alusta nopeus- ja kiihtyvyysarvot.
		 */
		public static void startupInit() {	
			pathfinder.lengthenLines(5);
			getPilot().setLinearSpeed(60);
			getPilot().setAngularSpeed(60);
			getPilot().setLinearAcceleration(60);
			getPilot().setAngularAcceleration(60);
			try {
				int kys = 1111;
				autoServer = new ServerSocket(kys);
				autoS = autoServer.accept();
				autoIn = new BufferedInputStream(autoS.getInputStream());
				autoOut = new BufferedOutputStream(autoS.getOutputStream());
				autoOin = new ObjectInputStream(autoIn);
				
				
				//autoOout = new ObjectOutputStream(autoOut);
				//autoOout.flush();
				System.out.println("Connected");
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ir.start();
			getHae().start();
			}
		public static InfrapunaThread getIr() {
			return ir;
		}
		/**
		 * Alusta startpose kun auto poistuu rekan kyydistä
		 */
		public static void setStartPose() {
			getNavigator().getPoseProvider().setPose(startPose);
			getPoseprovider().setPose(startPose);
			}
		public static void setStartHeading() {
			startHeading = getNavigator().getPoseProvider().getPose().getHeading();
			}
		
		
		
		/**
		 * Ajaa takaisin lähtöpisteeseen tehtävän jälkeen.
		 */
		public static void driveToStartPose() {
		
			Pose currentPose = getPoseprovider().getPose();
			
			
			Waypoint startPoint = new Waypoint(startPose.getX(), startPose.getY(), startPose.getHeading());
			System.out.println("X: " + getPoseprovider().getPose().getX() + " Y: " + getPoseprovider().getPose().getY() + " H: " + getPoseprovider().getPose().getHeading());
			try {
				getNavigator().goTo(10, 10, 0);
				
				/*
				Path path = pathfinder.findRoute(currentPose, startPoint);
				navigator.followPath(path);				
				navigator.waitForStop();
				System.out.println("X: " + poseprovider.getPose().getX() + " Y: " + poseprovider.getPose().getY() + " H: " + poseprovider.getPose().getHeading());
				*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getPilot().rotate(startPose.getHeading());
		}
		/**
		 * auto lähtee pois rampilta
		 */
		public static void exitRamp() {
			
			getPilot().setLinearSpeed(40);
			getPilot().travel(-50);
			getPilot().setLinearSpeed(100);
			getPilot().stop();
			
		}
		/**
		 * auto ajaa suoraan
		 */
		public static void enterRamp() {
			
			getPilot().forward();
	
		}
		public static void setMoving(boolean b) {
			moving = b;
		}

		public static MovePilot getPilot() {
			return pilot;
		}

		public static void setPilot(MovePilot pilot) {
			Auto.pilot = pilot;
		}

		public static PoseProvider getPoseprovider() {
			return poseprovider;
		}

		public static void setPoseprovider(PoseProvider poseprovider) {
			Auto.poseprovider = poseprovider;
		}

		public static HaeTiedotAuto getHae() {
			return hae;
		}

		public static void setHae(HaeTiedotAuto hae) {
			Auto.hae = hae;
		}

		public static float getStartHeading() {
			return startHeading;
		}

		public static void setStartHeading(float startHeading) {
			Auto.startHeading = startHeading;
		}

		public static Navigator getNavigator() {
			return navigator;
		}

		public static void setNavigator(Navigator navigator) {
			Auto.navigator = navigator;
		}
		

}
		
	
