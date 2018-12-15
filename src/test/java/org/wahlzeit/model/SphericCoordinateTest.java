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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link SphericCoordinate}.
 */
public class SphericCoordinateTest {
	private static final double EQUAL_DELTA = AbstractCoordinate.EQUAL_DELTA;

	private SphericCoordinate sphericCoordinate;

	@Before
	public void startUp() {
		sphericCoordinate = SphericCoordinate.getInstance(1, 1, 1);
		assertNotNull(sphericCoordinate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__phiSmall() {
		SphericCoordinate.getInstance(-1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__phiBig() {
		SphericCoordinate.getInstance(3*Math.PI, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__phiNotANumber() {
		SphericCoordinate.getInstance(Double.NaN, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__phiInfinity() {
		SphericCoordinate.getInstance(Double.POSITIVE_INFINITY, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__thetaSmall() {
		SphericCoordinate.getInstance(1, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__thetaBig() {
		SphericCoordinate.getInstance(1, 3*Math.PI, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__thetaNotANumber() {
		SphericCoordinate.getInstance(1, Double.NaN, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__thetaInfinity() {
		SphericCoordinate.getInstance(1, Double.POSITIVE_INFINITY, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__radiusZero() {
		SphericCoordinate.getInstance(1, Math.PI, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__radiusNegative() {
		SphericCoordinate.getInstance(1, Math.PI, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__radiusNotANumber() {
		SphericCoordinate.getInstance(1, 1, Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__radiusInfinity() {
		SphericCoordinate.getInstance(1, 1, Double.POSITIVE_INFINITY);
	}

	@Test
	public void testAsCartesianCoordinate() {
		assertTrue(SphericCoordinate.getInstance(1, 1, 1).asCartesianCoordinate()
				.isEqual(CartesianCoordinate.getInstance(0.454649, 0.708073, 0.540302)));

		assertTrue(SphericCoordinate.getInstance(3, 4, 5).asCartesianCoordinate()
				.isEqual(CartesianCoordinate.getInstance(-0.461211, -0.534, -4.94996)));

		assertTrue(SphericCoordinate.getInstance(1.5708, 1.5708, 1.0).asCartesianCoordinate()
				.isEqual(CartesianCoordinate.getInstance(0, 1, 0)));

		assertTrue(SphericCoordinate.getInstance(1.5708, 0, 1.0).asCartesianCoordinate()
				.isEqual(CartesianCoordinate.getInstance(1, 0, 0)));
	}

	@Test
	public void testGetCartesianDistance() {
		SphericCoordinate c = SphericCoordinate.getInstance(1, 5, 8);
		double distance = c.getCartesianDistance(sphericCoordinate);
		assertEquals(8.230092, distance, EQUAL_DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCartesianDistance__null() {
		sphericCoordinate.getCartesianDistance(null);
	}

	@Test()
	public void testAsSphericCoordinate() {
		assertSame(sphericCoordinate, sphericCoordinate.asSphericCoordinate());
	}

	@Test
	public void testGetCentralAngle() {
		SphericCoordinate s1 = SphericCoordinate.getInstance(0, Math.PI / 2, 6000.0);
		SphericCoordinate s2 = SphericCoordinate.getInstance(Math.PI, Math.PI,500.0);

		assertEquals(1.570796, s1.getCentralAngle(s2), EQUAL_DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCentralAngle__null() {
		sphericCoordinate.getCentralAngle(null);
	}

	@Test
	public void testIsEqual__Phidiffers() {
		SphericCoordinate c1 = SphericCoordinate.getInstance(0, 1, 1);
		assertFalse(c1.isEqual(sphericCoordinate));
	}

	@Test
	public void testIsEqual__Thetadiffers() {
		SphericCoordinate c1 = SphericCoordinate.getInstance(1, 0, 1);
		assertFalse(c1.isEqual(sphericCoordinate));
	}

	@Test
	public void testIsEqual__Radiusdiffers() {
		SphericCoordinate c1 = SphericCoordinate.getInstance(1, 1, 2);
		assertFalse(c1.isEqual(sphericCoordinate));
	}

	@Test
	public void testIsEqual__sameCoordinates() {
		SphericCoordinate c1 = SphericCoordinate.getInstance(1, 1, 1);
		assertTrue(c1.isEqual(sphericCoordinate));
	}

	@Test
	public void testShared__equalObjects() {
		SphericCoordinate same = SphericCoordinate.getInstance(1, 1, 1);
		assertSame(sphericCoordinate, same);
		assertEquals(sphericCoordinate, same);
	}

	@Test
	public void testShared__differentObjects() {
		SphericCoordinate other = SphericCoordinate.getInstance(1, 1, 1);
		assertSame(sphericCoordinate, other);
		assertEquals(sphericCoordinate, other);
	}

	@Test
	public void testHashCode() {
		assertEquals(32535647, sphericCoordinate.hashCode());
	}

	@Test
	public void testClone() {
		SphericCoordinate clone = (SphericCoordinate)sphericCoordinate.clone();
		assertEquals(sphericCoordinate, clone);
	}
}
