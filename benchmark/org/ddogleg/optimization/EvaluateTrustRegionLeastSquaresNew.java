/*
 * Copyright (c) 2012-2018, Peter Abeles. All Rights Reserved.
 *
 * This file is part of DDogleg (http://ddogleg.org).
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

package org.ddogleg.optimization;

import org.ddogleg.optimization.trustregion.*;
import org.ejml.LinearSolverSafe;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.factory.LinearSolverFactory_DDRM;
import org.ejml.interfaces.linsol.LinearSolverDense;

/**
 * @author Peter Abeles
 */
public class EvaluateTrustRegionLeastSquaresNew extends UnconstrainedLeastSquaresEvaluator_DDRM {

	public EvaluateTrustRegionLeastSquaresNew(boolean verbose) {
		super(verbose, true);
	}

	@Override
	protected UnconstrainedLeastSquares createSearch(double minimumValue) {
		ConfigTrustRegion config = new ConfigTrustRegion();
//		config.regionInitial = 1;
//		config.scalingMinimum = .1;
//		config.scalingMaximum = 1e6;

//		LinearSolverDense<DMatrixRMaj> solver = LinearSolverFactory_DDRM.chol(4);
		LinearSolverDense<DMatrixRMaj> solver = LinearSolverFactory_DDRM.leastSquaresQrPivot(true, false);
		solver = new LinearSolverSafe<>(solver);
//		TrustRegionUpdateDogleg_F64<DMatrixRMaj> alg = new TrustRegionUpdateDogleg_F64<>(solver);
		TrustRegionUpdateDogleg_F64<DMatrixRMaj> alg = new TrustRegionUpdateDoglegLS_F64<>(solver);
//		TrustRegionUpdateCauchy_F64<DMatrixRMaj alg = new TrustRegionUpdateCauchy_F64<>();

		UnconLeastSqTrustRegion_F64<DMatrixRMaj> tr = new UnconLeastSqTrustRegion_F64<>(
				alg, new TrustRegionMath_DDRM());
		tr.configure(config);
		return tr;
	}

	public static void main(String args[]) {
		EvaluateTrustRegionLeastSquaresNew eval = new EvaluateTrustRegionLeastSquaresNew(false);

		System.out.println("Powell              ----------------");
		eval.powell();
		System.out.println("Helical Valley      ----------------");
		eval.helicalValley();
		System.out.println("Rosenbrock          ----------------");
		eval.rosenbrock();
		System.out.println("Rosenbrock Mod      ----------------");
		eval.rosenbrockMod(Math.sqrt(2 * 1e6));
		System.out.println("variably            ----------------");
		eval.variably();
		System.out.println("trigonometric       ----------------");
		eval.trigonometric();
		System.out.println("Bady Scaled Brown   ----------------");
		eval.badlyScaledBrown();
	}
}