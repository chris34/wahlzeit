/*
 * Copyright (c) 2019 by Christoph Volkert
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model.parliament;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * All test cases of the class {@link Parliament}.
 */
public class ParliamentTest {
	public ParliamentType pt;

	@Before
	public void setUp() {
		pt = new ParliamentType("style");
	}

	@Test
	public void testConstructor() {
		new Parliament(pt, "TestName", 1900);
	}

	@Test
	public void testYearBuild_setterGetter() {
		Parliament parliament = new Parliament(pt, "TestName", 1900);
		parliament.setYearBuild(1878);
		assertEquals(1878, parliament.getYearBuild());
	}
}
