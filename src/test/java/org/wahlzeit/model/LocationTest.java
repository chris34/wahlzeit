/*
 * Copyright (c) 2018 by Christoph Volkert
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

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

	@Test
	public void testConstructor__cartesianCoordinate() {
		Coordinate cartesianCoordinate = CartesianCoordinate.getInstance(1, 1, 1);
		Location location = new Location(cartesianCoordinate);

		assertEquals(location.coordinate, cartesianCoordinate);
	}

	@Test
	public void testConstructor__sphericCoordinate() {
		Coordinate sqhericCoordinate = SphericCoordinate.getInstance(1, 1, 1);
		Location location = new Location(sqhericCoordinate);

		assertEquals(location.coordinate, sqhericCoordinate);
	}

	@Test
	public void testConstructor__explicitValues() {
		Location location = new Location(1, 1, 1);

		assertEquals(location.coordinate, CartesianCoordinate.getInstance(1, 1, 1));
	}
}
