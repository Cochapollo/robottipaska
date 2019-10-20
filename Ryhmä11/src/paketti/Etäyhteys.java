package paketti;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;


public class Etäyhteys {
	private static ServerSocket server = null, rekkaServer = null;
	private static Socket soc = null, autoS = null, rekkaS = null;
	private static BufferedInputStream in = null, autoIn = null, rekkaIn = null;
	private static DataInputStream dIn = null, autoDin = null, rekkaDin = null;
	private static BufferedOutputStream Out = null, autoOut = null, rekkaOut = null;
	private static DataOutputStream dOut = null, autoDout = null, rekkaDout = null;
	private LineMap kartta = new LineMap();
	private ArrayList<Waypoint> waypointit = new ArrayList<Waypoint>();
	public void avaaSocket() {
		try {
			server = new ServerSocket(1111);
			soc = server.accept();
			////
			Out = new BufferedOutputStream(soc.getOutputStream());
			dOut = new DataOutputStream(Out);
			// dOut = new DataOutputStream(soc.getOutputStream());
			///
			in = new BufferedInputStream(soc.getInputStream());
			dIn = new DataInputStream(in);
			// dIn = new DataInputStream(soc.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void avaaRekanSocket() {
		
		try {
			autoS = new Socket("10.0.1.5",1155);
			autoOut = new BufferedOutputStream(autoS.getOutputStream());
			autoDout = new DataOutputStream(autoOut);
			// dOut = new DataOutputStream(soc.getOutputStream());
			///
			autoIn = new BufferedInputStream(autoS.getInputStream());
			autoDin = new DataInputStream(in);
			// dIn = new DataInputStream(soc.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		////
		
	}
public void avaaAutonSocket() {
		
		try {
			rekkaServer = new ServerSocket(1155);
			rekkaS = rekkaServer.accept();
			rekkaOut = new BufferedOutputStream(soc.getOutputStream());
			rekkaDout = new DataOutputStream(Out);
			// dOut = new DataOutputStream(soc.getOutputStream());
			///
			rekkaIn = new BufferedInputStream(soc.getInputStream());
			rekkaDin = new DataInputStream(in);
			// dIn = new DataInputStream(soc.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		////
		
	}
	public void suljeSocket() {
		try {
			dIn.close();
			in.close();
			dOut.close();
			Out.close();
			soc.close();
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public LineMap getKartta() {
		try {
			this.kartta.loadObject(dIn);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return kartta;
	}
	public ArrayList<Waypoint> getWaypointit() {
		try {
			boolean kappa = true;
			while (kappa){
				Waypoint point = new Waypoint(0, 0);
				point.loadObject(dIn);
				waypointit.add(point);
				if(waypointit.get(waypointit.size()-1).x == 0 && waypointit.get(waypointit.size()-1).y == 0 ) {
					waypointit.remove(waypointit.size()-1);
					kappa = false;
				}
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return waypointit;
	}
	public void lähetäPose(Pose pose) {
		try {	
			pose.dumpObject(dOut);
			dOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
