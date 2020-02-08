package utilitaire;


public class Position {
	private double[] xPositions;
	
	public Position(double xMin, double xMax) {
		xPositions = new double[2];
		xPositions[0] = xMin;
		xPositions[1] = xMax;
	}
	
	public double getXMin() {
		return xPositions[0];
	}
	
	public double getXMax() {
		return xPositions[1];
	}

}
