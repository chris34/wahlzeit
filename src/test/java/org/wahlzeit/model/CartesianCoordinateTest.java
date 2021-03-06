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
 * All test cases of the class {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {
	private static final double EQUAL_DELTA = AbstractCoordinate.EQUAL_DELTA;

	private CartesianCoordinate cartesianCoordinate;

	@Before
	public void startUp() {
		cartesianCoordinate = CartesianCoordinate.getInstance(0, 0, 0);
		assertNotNull(cartesianCoordinate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__xNotANumber() {
		CartesianCoordinate.getInstance(Double.NaN, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__yNotANumber() {
		CartesianCoordinate.getInstance(0, Double.NaN, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__zNotANumber() {
		CartesianCoordinate.getInstance(0, 0, Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__xInfinity() {
		CartesianCoordinate.getInstance(Double.POSITIVE_INFINITY, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__yInfinity() {
		CartesianCoordinate.getInstance(0, Double.POSITIVE_INFINITY, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor__zInfinity() {
		CartesianCoordinate.getInstance(0, 0, Double.POSITIVE_INFINITY);
	}

	@Test
	public void testAsCartesianCoordinate() {
		assertSame(cartesianCoordinate, cartesianCoordinate.asCartesianCoordinate());
	}

	@Test
	public void testGetCartesianDistance() {
		CartesianCoordinate c = CartesianCoordinate.getInstance(1, 5, 8);
		double distance = c.getCartesianDistance(cartesianCoordinate);
		assertEquals(9.4868, distance, EQUAL_DELTA);
	}

	@Test
	public void testGetCartesianDistance__Xdiffers() {
		CartesianCoordinate c = CartesianCoordinate.getInstance(1, 0, 0);
		double distance = c.getCartesianDistance(cartesianCoordinate);
		assertEquals(1, distance, EQUAL_DELTA);
	}

	@Test
	public void testGetCartesianDistance__allDifferFloat() {
		CartesianCoordinate c = CartesianCoordinate.getInstance(1, 5, 8);
		CartesianCoordinate c2 = CartesianCoordinate.getInstance(10.5, 5.65, 8.9);
		double distance = c.getCartesianDistance(c2);
		assertEquals(9.5646, distance, EQUAL_DELTA);
	}

	@Test
	public void testGetCartesianDistance__equalCoordinates() {
		double distance = cartesianCoordinate.getCartesianDistance(cartesianCoordinate);
		assertEquals(0, distance, EQUAL_DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCartesianDistance__null() {
		cartesianCoordinate.getCartesianDistance(null);
	}

	@Test(expected = AssertionError.class)
	public void testAsSphericCoordinate__zeroDivision() {
		CartesianCoordinate.getInstance(0,0,0).asSphericCoordinate();
	}

	@Test
	public void testAsSphericCoordinate() {
		assertTrue(CartesianCoordinate.getInstance(1, 0, 0).asSphericCoordinate().
				isEqual(SphericCoordinate.getInstance(1.5708, 0, 1)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCentralAngle__null() {
		cartesianCoordinate.getCentralAngle(null);
	}

	@Test
	public void testIsEqual__Xdiffers() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(.33333, 0, 0);
		CartesianCoordinate c2 = CartesianCoordinate.getInstance(.1, 0, 0);
		assertFalse(c1.isEqual(c2));
	}

	@Test
	public void testIsEqual__Ydiffers() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(0, 1, 0);
		assertFalse(c1.isEqual(cartesianCoordinate));
	}

	@Test
	public void testIsEqual__Zdiffers() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(0, 0, 1);
		assertFalse(c1.isEqual(cartesianCoordinate));
	}

	@Test
	public void testIsEqual__sameCoordinates() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(0, 0, 0);
		assertTrue(c1.isEqual(cartesianCoordinate));
	}

	@Test
	public void testIsEqual__sameCoordinatesFloat() {
		CartesianCoordinate c1 = CartesianCoordinate.getInstance(23.42, 0, 1);
		CartesianCoordinate c2 = CartesianCoordinate.getInstance(23.42, 0, 1);
		assertTrue(c1.isEqual(c2));
	}

	@Test
	public void testEquals__otherClassType() {
		Object o = new Object();
		assertNotEquals(cartesianCoordinate, o);
	}

	@Test
	public void testEquals__otherCoordinate() {
		CartesianCoordinate c = CartesianCoordinate.getInstance(1, 0, 1);
		assertNotEquals(cartesianCoordinate, c);
	}

	@Test
	public void testEquals__sameCoordinate() {
		CartesianCoordinate c = CartesianCoordinate.getInstance(0, 0, 0);
		assertEquals(cartesianCoordinate, c);
	}

	@Test
	public void testShared__equalObjects() {
		CartesianCoordinate same = CartesianCoordinate.getInstance(0, 0, 0);
		assertSame(cartesianCoordinate, same);
		assertEquals(cartesianCoordinate, same);
	}

	@Test
	public void testShared__differentObjects() {
		CartesianCoordinate other = CartesianCoordinate.getInstance(10, 11, 23);
		assertNotSame(cartesianCoordinate, other);
		assertNotEquals(cartesianCoordinate, other);
	}

	@Test
	public void testHashCode() {
		assertEquals(29791, cartesianCoordinate.hashCode());
	}

	@Test
	public void testClone() {
		CartesianCoordinate clone = (CartesianCoordinate)cartesianCoordinate.clone();
		assertEquals(cartesianCoordinate, clone);
	}
}
