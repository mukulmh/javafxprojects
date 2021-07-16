package lab1;

class Point {
	double x;
	double y;

	Point(double nx, double ny) {
		x = nx;
		y = ny;
	}

	double getDistance(Point b) {
		Point a = this;
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	double getAngle(Point b) {
		// TODO write your own code
		return 0;
	}

	Point translate(double h, double k) {
		// TODO write your own code
                Point a = this;
                double nx = a.x + h, ny = a.y + k;
		Point p = new Point(nx, ny); 
                
		return p;
	}	
}

public class Lab1 {
	public static void main(String args[]) {
		System.out.printf("Enter the x and y coordinates of two points\n");

		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		double x1 = scanner.nextDouble();
		double y1 = scanner.nextDouble();
		double x2 = scanner.nextDouble();
		double y2 = scanner.nextDouble();

		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		double distance = p1.getDistance(p2);

		System.out.printf("Distance = %f\n", distance);
		
		double angle = p1.getAngle(p2);
		System.out.printf("Angle with the x-axis = %f\n", angle);

		System.out.printf("Enter the h and k value by which we want to translate p1 and p2\n");
		// TODO add your code to do the rest
                double h = scanner.nextDouble();
                double k = scanner.nextDouble();
                
                Point p = p1.translate(h,k);
                
	}
}