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

import org.ddogleg.optimization.impl.LevenbergMarquardtDampened_DDRM;
import org.ddogleg.optimization.wrap.LevenbergDampened_to_UnconstrainedLeastSquares;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.factory.LinearSolverFactory_DDRM;
import org.ejml.interfaces.linsol.LinearSolverDense;

/**
 * @author Peter Abeles
 */
public class EvaluateLevenbergMarquardtDampened extends UnconstrainedLeastSquaresEvaluator_DDRM {

	boolean robust = true;
	double dampInit = 1e-3;

	public EvaluateLevenbergMarquardtDampened(boolean verbose) {
		super(verbose, true);
	}

	@Override
	protected UnconstrainedLeastSquares createSearch(double minimumValue) {
		LinearSolverDense<DMatrixRMaj> solver;

		if( robust ) {
			solver = LinearSolverFactory_DDRM.pseudoInverse(true);
		} else {
			solver = LinearSolverFactory_DDRM.symmPosDef(10);
		}

		LevenbergMarquardtDampened_DDRM alg = new LevenbergMarquardtDampened_DDRM(solver,dampInit);
		return new LevenbergDampened_to_UnconstrainedLeastSquares(alg);
	}

	public static void main( String args[] ) {
		EvaluateLevenbergMarquardtDampened eval = new EvaluateLevenbergMarquardtDampened(false);

		System.out.println("Powell              ----------------");
		eval.powell();
		System.out.println("Powell Singular     ----------------");
		eval.powellSingular();
		System.out.println("Helical Valley      ----------------");
		eval.helicalValley();
		System.out.println("Rosenbrock          ----------------");
		eval.rosenbrock();
		System.out.println("Rosenbrock Mod      ----------------");
		eval.rosenbrockMod(Math.sqrt(2*1e6));
		System.out.println("variably            ----------------");
		eval.variably();
		System.out.println("trigonometric       ----------------");
		eval.trigonometric();
		System.out.println("Badly Scaled Brown  ----------------");
		eval.badlyScaledBrown();
		System.out.println("Badly Scaled Powell ----------------");
		eval.badlyScalledPowell();
		System.out.println("Bundle 2D           ----------------");
		eval.bundle2D();
	}
}