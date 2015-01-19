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

import georegression.fitting.MotionTransformPoint;
import georegression.fitting.se.MotionSe3PointSVD_F64;
import georegression.geometry.RotationMatrixGenerator;
import georegression.struct.point.Point3D_F64;
import georegression.struct.se.Se3_F64;
import georegression.transform.se.SePointOps_F64;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstrate how to estimate rigid body motion of a point cloud
 *
 * @author Peter Abeles
 */
public class ExampleTransformFitting {

	static Logger lg=Logger.getLogger(ExampleTransformFitting.class.getName());

	public static void init() {
		Random rand = new Random(234);

		// Create a transform which will be applied to the point cloud
		Se3_F64 actual = new Se3_F64();
		RotationMatrixGenerator.eulerXYZ(0.5,-2,0.15,actual.R);
		actual.T.set(2,3,-2);

		// Create a random point cloud and transform it
		List<Point3D_F64> fromPts = new ArrayList<Point3D_F64>();
		List<Point3D_F64> toPts = new ArrayList<Point3D_F64>();


		for( int i = 0; i < 50; i++ ) {
			double x = rand.nextGaussian();
			double y = rand.nextGaussian();
			double z = rand.nextGaussian();

			Point3D_F64 p = new Point3D_F64(x,y,z);
			Point3D_F64 tranP = new Point3D_F64();

			SePointOps_F64.transform(actual,p,tranP);

			fromPts.add(p);
			toPts.add(tranP);
		}

		// Estimate the transform from the point pairs
		MotionTransformPoint<Se3_F64, Point3D_F64> estimator = new MotionSe3PointSVD_F64();

		if(!estimator.process(fromPts,toPts))
			throw new RuntimeException("Estimation of Se3 failed");

		Se3_F64 found = estimator.getTransformSrcToDst();

		// Print out the results and see how it compares to ground truth
		//actual.print();
		lg.log(Level.INFO, actual.toString());
		lg.log(Level.INFO, "  .  ");
		System.out.println();
		lg.log(Level.INFO, found.toString());
		//found.print();
	}
}
