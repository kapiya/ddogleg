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

package org.ddogleg.optimization.trustregion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Specific configurations on this class are tested inside of the TrustRegionUpdate implementations
 *
 * @author Peter Abeles
 */
public class TestUnconMinTrustRegionBFGS_F64  {

	@Test
	public void checkConvergenceFTest() {
		fail("Implement");
	}

	/**
	 * Makes sure BFGS is called correctly
	 */
	@Test
	public void invokedBFGS() {
		fail("Implement");
	}
}