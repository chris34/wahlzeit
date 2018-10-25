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

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Represents a coordinate in a three dimensional room.
 * Thus, a coordinate is described by the three values x,
 * y and z.
 */
public class Coordinate {
	private static final double EQUAL_DELTA = 1E-4;

	private double x;
	private double y;
	private double z;

	/**
	 * Constructor to instantiate a new Coordinate
	 *
	 * @param x value for x-direction
	 * @param y value for y-direction
	 * @param z value for z-direction
	 */
	Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Calculates the direct Cartesian distance between @param other
	 * and this coordinate.
	 *
	 * @param other coordinate to calculate distance to
	 * @return direct Cartesian distance between @param other and this
	 */
	public double getDistance(Coordinate other) {
		double tmp = pow(this.x - other.x, 2);
		tmp += pow(this.y - other.y, 2);
		tmp += pow(this.z - other.z, 2);
		return sqrt(tmp);
	}

	/**
	 * Compares, whether @param other and this class point to the same location.
	 *
	 * @param other coordinate to compare with
	 * @return true, if x, y and z of @param other and this coordinate are equal. Otherwise false.
	 */
	public boolean isEqual(Coordinate other) {
		boolean equalX = Math.abs(x - other.x) <= EQUAL_DELTA;
		boolean equalY = Math.abs(y - other.y) <= EQUAL_DELTA;
		boolean equalZ = Math.abs(z - other.z) <= EQUAL_DELTA;

		return equalX && equalY && equalZ;
	}


	/**
	 * Compares, whether @param other and this class point to the same location.
	 * Uses isEqual(). In comparison, this method accepts an Object.
	 *
	 * @param other an Object to compare with
	 * @return true, if @param other and this coordinate are equal. Otherwise false.
	 */
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Coordinate)) {
			return false;
		}

		return isEqual((Coordinate) other);
	}
}
