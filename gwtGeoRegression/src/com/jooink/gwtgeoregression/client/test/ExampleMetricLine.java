/*
 * Copyright (C) 2011-2014, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Geometric Regression Library (GeoRegression).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jooink.gwtgeoregression.client.test;

import georegression.metric.Distance2D_F64;
import georegression.metric.Intersection2D_F64;
import georegression.struct.line.LineParametric2D_F64;
import georegression.struct.line.LineSegment2D_F64;
import georegression.struct.point.Point2D_F64;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This example demonstrates how classes inside of the metric package metric can be used to compute
 * values which describe the relationship between to lines and points. The relationship between other
 * shapes can also be done using the same classes.
 *
 * @author Peter Abeles
 */
public class ExampleMetricLine {

	static Logger lg=Logger.getLogger(ExampleMetricLine.class.getName());
	
	public static void distancePointToLine() {
		lg.log(Level.INFO, "Distance of points to lines");
		// two points which are used to define a line
		Point2D_F64 a = new Point2D_F64(0,0);
		Point2D_F64 b = new Point2D_F64(10,10);
		// a point on the line
		Point2D_F64 c = new Point2D_F64(20,20);
		// a point off the line
		Point2D_F64 d = new Point2D_F64(5,8);

		// define a line segment using two points
		LineSegment2D_F64 ls = new LineSegment2D_F64(a,b);
		// convert the line segment into a parametric line
		LineParametric2D_F64 lp = new LineParametric2D_F64(a.x,a.y,b.x-a.x,b.y-a.y);

		// Point C should lie along the infinite line but be outside of the finite line segment
		lg.log(Level.INFO, "Distance of line segment to point c     : "+ Distance2D_F64.distance(ls,c));
		lg.log(Level.INFO, "Distance of parametric line to point c  : "+ Distance2D_F64.distance(lp,c));
		
		// Point D should be the same distance from both lines
		lg.log(Level.INFO, "Distance of line segment to point d     : "+ Distance2D_F64.distance(ls,d));
		lg.log(Level.INFO, "Distance of parametric line to point d  : "+ Distance2D_F64.distance(lp,d));
	}

	public static void intersectionLineSegment() {
		LineSegment2D_F64 lineA = new LineSegment2D_F64(-10,-10,10,10);
		LineSegment2D_F64 lineB = new LineSegment2D_F64(-10,10,10,-10);
		LineSegment2D_F64 lineC = new LineSegment2D_F64(-10,-9,10,11);
		LineSegment2D_F64 lineD = new LineSegment2D_F64(-20,-20,-18,-18);
		LineSegment2D_F64 lineE = new LineSegment2D_F64(-20,-20,-9,-9);

		lg.log(Level.INFO, "Intersection between line segments");
		lg.log(Level.INFO, "A and B (one)  : "+ Intersection2D_F64.intersection(lineA,lineB,null));
		lg.log(Level.INFO, "A and C (none) : "+ Intersection2D_F64.intersection(lineA,lineC,null));
		lg.log(Level.INFO, "A and D (none) : "+ Intersection2D_F64.intersection(lineA,lineD,null));
		lg.log(Level.INFO, "A and E (many) : "+ Intersection2D_F64.intersection(lineA,lineE,null));

	}

	public static void intersectionParametric() {
		LineParametric2D_F64 lineA = new LineParametric2D_F64(0,0,1,1);
		LineParametric2D_F64 lineB = new LineParametric2D_F64(0,0,1,-1);
		LineParametric2D_F64 lineC = new LineParametric2D_F64(1,0,1,1);
		LineParametric2D_F64 lineD = new LineParametric2D_F64(-1,-1,1,1);

		lg.log(Level.INFO, "Intersection between lines");
		lg.log(Level.INFO, "A and B (one)  : "+ Intersection2D_F64.intersection(lineA,lineB));
		lg.log(Level.INFO, "A and C (none) : "+ Intersection2D_F64.intersection(lineA,lineC));
		lg.log(Level.INFO, "A and D (many) : "+ Intersection2D_F64.intersection(lineA,lineD));
	}

	public static void init() {
		distancePointToLine();
		lg.log(Level.INFO, "---");
		intersectionLineSegment();
		lg.log(Level.INFO, "---");
		intersectionParametric();
	}
}
