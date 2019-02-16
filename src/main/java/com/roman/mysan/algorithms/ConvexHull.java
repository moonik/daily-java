package com.roman.mysan.algorithms;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConvexHull {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int orientation(Point q, Point p1, Point p2) {
        return ((p2.x - q.x) * (p1.y - q.y)) - ((p1.x - q.x) * (p2.y - q.y));
    }
    public List<Point> getHull(List<Point> points) {
        List<Point> result = new ArrayList<>();
        Point start = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.x < start.x) {
                start = p;
            }
        }
        result.add(start);
        Point point = start;
        Point farPoint = null;
        while (farPoint == null || !farPoint.equals(start)) {
            Point p1 = null;
            for (Point p : points) {
                if (point.equals(p)) {
                    continue;
                } else {
                    p1 = p;
                    break;
                }
            }
            farPoint = p1;
            for (Point p2 : points) {
              if (p2.equals(p1) || p2.equals(point)) {
                  continue;
              } else {
                  int direction = orientation(point, farPoint, p2);
                  if (direction > 0) { farPoint = p2; }
              }
            }
            result.add(farPoint);
            point = farPoint;
        }
        return result;
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            points.add(new Point(ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextInt(0, 10)));
        }
        ConvexHull scan = new ConvexHull();
        scan.getHull(points);
    }
}
