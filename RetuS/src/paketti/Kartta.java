package paketti;
import java.util.ArrayList;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;

public class Kartta {
	private Line[] janat = new Line[4];
	private LineMap kartta;
	public Kartta(){
		Rectangle suorakulmio = new Rectangle(0,0,200,200);
		// Ulkoreunat
		janat[0] = new Line(0,0,200,0);
		janat[1] = new Line(200,0,200,200);
		janat[2] = new Line(0,200,200,200);
		janat[3] = new Line(0,0,0,200);
		
		kartta = new LineMap(janat,suorakulmio);
	}
	public LineMap getKartta() {
		return kartta;
	}
}
