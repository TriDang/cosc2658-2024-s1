package w07;

import java.awt.geom.*;

public class ConvexHull {
  public static void main(String[] args) {
    /* Line2D represents a line segment in (x,y) coordinate space.
       This class, like all of the Java 2D API, uses a default coordinate system called user space
       in which the y-axis values increase downward and x-axis values increase to the right.
    */

    // The first 4 points are the convex hull
    double[] px = new double[]{0, 100, 0, 100, 25, 30, 44, 67, 89};
    double[] py = new double[]{0, 100, 100, 0, 77, 62, 90, 33, 28};

    // initialization
    int n = px.length;
    Point2D[] points = new Point2D[n];
    for (int i = 0; i < n; i++) {
      points[i] = new Point2D.Double(px[i], py[i]);
    }

    // brute force convex hull algorithm
    // assumption: there are no 3 points positioned on a same line
    Line2D[] convexHull = new Line2D[points.length];
    int sides = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        // create a line from point i -> j
        Line2D line = new Line2D.Double(points[i], points[j]);

        // check if all other points are on one side of the newly formed line
        boolean oneSide = true;
        boolean firstPoint = true;
        int sign = 0;
        for (int k = 0; k < n; k++) {
          if (k == i || k == j) {
            continue;
          }
          if (firstPoint) {
            sign = line.relativeCCW(points[k]);
            firstPoint = false;
          } else {
            int newSign = line.relativeCCW(points[k]);
            if (newSign * sign < 0) {
              oneSide = false;
              break;
            }
          }
        }
        if (oneSide) {
          convexHull[sides] = line;
          sides++;
        }
      }
    }

    // Output
    for (int i = 0; i < sides; i++) {
      Line2D line = convexHull[i];
      System.out.printf("\nLine (%.2f, %.2f) - (%.2f, %.2f) is on the convex hull",
        line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
  }
}