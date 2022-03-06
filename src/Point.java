import java.util.ArrayList;
import java.util.Random;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState(boolean isRain) {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors
		if (!isRain) {
			if (this.currentState == 0) {
				if (this.numOfAliveNeighbors() == 3) this.nextState = 1;
				else this.nextState = 0;
			} else {
				if (this.numOfAliveNeighbors() != 2 && this.numOfAliveNeighbors() != 3) {
					this.nextState = 0;
				} else this.nextState = this.currentState;
			}
		}
		else{
			if (this.currentState > 0) this.nextState = this.currentState - 1;
			else if (this.neighbors.get(0).getState() > 0) this.nextState = 6;
			else this.nextState = this.currentState;
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
	public int numOfAliveNeighbors(){
		int num = 0;
		for (Point neigh : this.neighbors) {if (neigh.currentState == 1) num++;}
		return num;
	}

	public void drop(){
		Random random = new Random();
		int s = random.nextInt(100);
		if (s < 5) this.setState(6);
	}

	public void clearNeighbors(){
		this.neighbors.clear();
	}
}
