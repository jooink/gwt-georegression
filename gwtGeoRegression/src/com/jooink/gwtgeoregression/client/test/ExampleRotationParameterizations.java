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

import georegression.geometry.RotationMatrixGenerator;
import georegression.struct.so.Quaternion_F64;
import georegression.struct.so.Rodrigues_F64;

import org.ejml.data.DenseMatrix64F;

/**
 * Simple example of how to convert a rotation matrix into equivalent formats.
 *
 * @author Peter Abeles
 */
public class ExampleRotationParameterizations {

	public static void init() {

		// Euler to rotation matrix
		DenseMatrix64F R = RotationMatrixGenerator.eulerXYZ(0.5,-1,-0.45,null);

		// matrix to Rodrigues
		Rodrigues_F64 rod = RotationMatrixGenerator.matrixToRodrigues(R,(Rodrigues_F64)null);

		// Rodrigues to Quaternion
		Quaternion_F64 quat = RotationMatrixGenerator.rodriguesToQuaternion(rod,null);

		// Quaternion to Rotation Matrix
		DenseMatrix64F T = RotationMatrixGenerator.quaternionToMatrix(quat,null);

		// see if you get the same answer
		R.print();
		T.print();
	}
}
