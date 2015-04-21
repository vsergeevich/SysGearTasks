/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysgears.task4;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * To solve this task we normalize the points on value of angle between the
 * X-axis and the line that joins the point(0, 0) and current point. If two
 * points has the equal value of angle, we normalize them on the distance from
 * point (0, 0)(polar coordinate system)
 *
 * @author Victor Tyrin
 */
public class Graf {

    public static void main(String[] args) {
        Set<Point> set = new TreeSet<>(new MyComparator()); //Provides sorting and storage the points in a order, specified by MyComparator
        Random r = new Random();
        Point p;
        for (int i = 0; i < 10; i++) {
            p = new Point(r.nextInt(10), r.nextInt(10)); //generating a random point in range [10, 10]
            set.add(p); //add a point to the Set
        }
        for (Iterator<Point> iter = set.iterator(); iter.hasNext();) {
            System.out.println(iter.next());
        }
    }
}

/**
 * To add items to Set in the correct order, we will implement the interface
 * Comparator and override method "compare".
 *
 * @author Victor Tyrin
 */
class MyComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        //difference between the angle in polar system points p1 and p2
        double deltaAngle = getAngleInPolarSystem(p1) - getAngleInPolarSystem(p2);
        if (deltaAngle < 0) {
            return 1;
        } else if (deltaAngle > 0) {
            return -1;
        } else {
            //difference beetwen the distance to (0, 0) for points p1 and p2
            double deltaRadius = Math.hypot(p1.getX(), p1.getY()) - Math.hypot(p2.getX(), p2.getY());
            if (deltaRadius < 0) {
                return 1;
            } else if (deltaRadius > 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Calculation of angle between the X-axis and the line that joins the
     * point(0, 0) and current point
     *
     * @param p
     * @return
     */
    private double getAngleInPolarSystem(Point p) {
        double x = p.getX();
        double y = p.getY();
        double angle;
        if (x > 0 & y >= 0) {
            angle = Math.atan(y / x);
        } else if (x > 0 & y < 0) {
            angle = Math.atan(y / x) + Math.PI * 2;
        } else {
            angle = Math.atan(y / x) + Math.PI;
        }
        return angle;
    }

}
