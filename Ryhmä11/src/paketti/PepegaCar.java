package paketti;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.SteeringPilot;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;

/**
 * 
 * Tässä luokassa testasimme moottorikomentoja, jotka eivät koskaan kuitenkaan toimineet lejos API:n puutteiden takia.
 *
 */

public class PepegaCar {

	public static void main(String[] args) {

		
		// 4 moottorin setup
				RegulatedMotor rearLeft = new EV3LargeRegulatedMotor(MotorPort.A);
				RegulatedMotor frontLeft = new EV3LargeRegulatedMotor(MotorPort.B);
				RegulatedMotor rearRight = new EV3LargeRegulatedMotor(MotorPort.C);
				RegulatedMotor frontRight = new EV3LargeRegulatedMotor(MotorPort.D);
		
		/*
		//  AUTO -- takamoottorit invertattu
		Wheel wheel1 = WheeledChassis.modelWheel(rearLeft, 4.23).offset(23.5).invert(true);
		Wheel wheel2 = WheeledChassis.modelWheel(rearRight, 4.23).offset(-23.5).invert(true);
		Wheel wheel3 = WheeledChassis.modelWheel(frontLeft, 4.23).offset(23.5);
		Wheel wheel4 = WheeledChassis.modelWheel(frontRight, 4.23).offset(-23.5);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2, wheel3, wheel4 }, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);
		*/
				
		//  REKKA -- takamoottorit invertattu
				Wheel wheel1 = WheeledChassis.modelWheel(rearLeft, 4.23).offset(8.5).invert(true);
				Wheel wheel2 = WheeledChassis.modelWheel(rearRight, 4.23).offset(-8.5).invert(true);
				Wheel wheel3 = WheeledChassis.modelWheel(frontLeft, 4.23).offset(8.5);
				Wheel wheel4 = WheeledChassis.modelWheel(frontRight, 4.23).offset(-8.5);
				Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2, wheel3, wheel4 }, WheeledChassis.TYPE_DIFFERENTIAL);
				MovePilot pilot = new MovePilot(chassis);
				
			
		
		/*// Kartan alustus
		Kartta kartta = new Kartta();
		
		// Navigatorin alustus

		PoseProvider poseprovider = chassis.getPoseProvider();
		Navigator navigator = new Navigator(pilot);
		ShortestPathFinder pathfinder = new ShortestPathFinder(kartta.getKartta());
		
		pathfinder.lengthenLines(5);
		Pose startPose = new Pose(0, 10, 0);
		
		// Waypointit
		ArrayList<Waypoint> waypoints = new ArrayList<>();
		waypoints.add(new Waypoint(50, 10));
		waypoints.add(new Waypoint(80, 30));
		waypoints.add(new Waypoint(80, 50));
		waypoints.add(new Waypoint(50, 70));
		waypoints.add(new Waypoint(50, 90));
		waypoints.add(new Waypoint(10, 90));
		
		for (Waypoint waypoint : waypoints) {
			navigator.addWaypoint(waypoint);
		}
		navigator.getPoseProvider().setPose(startPose);*/
		
		
		//Moottoritestejä
		
		//pilot.travel(-100);
		//pilot.rotate(180);
/*
		pilot.travel(50);
		pilot.rotate(45);
		pilot.travel(20);
		pilot.rotate(45);
		pilot.travel(20);
		pilot.rotate(45);
		pilot.travel(20);
		pilot.rotate(45);
		pilot.travel(50);
	*/
		//pilot.travel(100);

				pilot.setLinearSpeed(10);
				pilot.setAngularSpeed(10);
				pilot.setLinearAcceleration(10);
				pilot.setAngularAcceleration(10);
				
				//while(!Button.ESCAPE.isDown()) pilot.arc(50, 90);
				
				
				//pilot.travel(50);

				pilot.arc(50, 230);
				
				
				//pilot.travel(50);
		
		
		
		/*
		// Ajelut
		try {
			Path path = pathfinder.findRoute(startPose, waypoints.get(0));
			System.out.println(poseprovider.getPose());
			
			navigator.setPath(path);
			navigator.followPath();
			navigator.waitForStop();
		} catch (DestinationUnreachableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 1; i < waypoints.size(); i++) {
			try {
				Path path = pathfinder.findRoute(poseprovider.getPose(), waypoints.get(i));
				System.out.println(poseprovider.getPose());
				navigator.setPath(path);
				navigator.followPath();
				navigator.waitForStop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		*/
		
	}
}
