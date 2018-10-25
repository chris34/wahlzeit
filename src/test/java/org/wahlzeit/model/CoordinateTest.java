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
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {
	private static final double EQUAL_DELTA = 1E-4;

	private Coordinate coordinate;

	@Before
	public void startUp() {
		coordinate = new Coordinate(0, 0, 0);
	}

	/**
	 *
	 */
	@Test
	public void testGetDistance() {
		Coordinate c = new Coordinate(1, 5, 8);
		double distance = c.getDistance(coordinate);
		assertEquals(9.4868, distance, EQUAL_DELTA);
	}

	/**
	 *
	 */
	@Test
	public void testGetDistance__Xdiffers() {
		Coordinate c = new Coordinate(1, 0, 0);
		double distance = c.getDistance(coordinate);
		assertEquals(1, distance, EQUAL_DELTA);
	}

	/**
	 *
	 */
	@Test
	public void testGetDistance__allDifferFloat() {
		Coordinate c = new Coordinate(1, 5, 8);
		Coordinate c2 = new Coordinate(10.5, 5.65, 8.9);
		double distance = c.getDistance(c2);
		assertEquals(9.5646, distance, EQUAL_DELTA);
	}

	/**
	 *
	 */
	@Test
	public void testGetDistance__equalCoordinates() {
		double distance = coordinate.getDistance(coordinate);
		assertEquals(0, distance, EQUAL_DELTA);
	}

	/**
	 *
	 */
	@Test
	public void testIsEqual__Xdiffers() {
		Coordinate c1 = new Coordinate(.33333, 0, 0);
		Coordinate c2 = new Coordinate(.1, 0, 0);
		assertFalse(c1.isEqual(c2));
	}

	/**
	 *
	 */
	@Test
	public void testIsEqual__sameCoordinates() {
		Coordinate c1 = new Coordinate(0, 0, 0);
		Coordinate c2 = new Coordinate(0, 0, 0);
		assertTrue(c1.isEqual(c2));
	}

	/**
	 *
	 */
	@Test
	public void testIsEqual__sameCoordinatesFloat() {
		Coordinate c1 = new Coordinate(23.42, 0, 1);
		Coordinate c2 = new Coordinate(23.42, 0, 1);
		assertTrue(c1.isEqual(c2));
	}

	/**
	 *
	 */
	@Test
	public void testIsEqual__Ydiffer() {
		Coordinate c1 = new Coordinate(0, 1, 0);
		Coordinate c2 = new Coordinate(0, 0, 0);
		assertFalse(c1.isEqual(c2));
	}

	/**
	 *
	 */
	@Test
	public void testEquals__otherClassType() {
		Object o = new Object();
		assertNotEquals(coordinate, o);
	}

	/**
	 *
	 */
	@Test
	public void testEquals__otherCoordinate() {
		Coordinate c = new Coordinate(1, 0, 1);
		assertNotEquals(coordinate, c);
	}

	/**
	 *
	 */
	@Test
	public void testEquals__sameCoordinate() {
		Coordinate c = new Coordinate(0, 0, 0);
		assertEquals(coordinate, c);
	}
}
