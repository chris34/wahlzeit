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

import static java.lang.Math.*;

/**
 * Represents a cartesian coordinate in a three dimensional room.
 * Thus, a coordinate is described by the three values x,
 * y and z.
 */
public class CartesianCoordinate implements Coordinate {
	private static final double EQUAL_DELTA = 1E-4;

	private final double x;
	private final double y;
	private final double z;

	/**
	 * Constructor to instantiate a new CartesianCoordinate
	 *
	 * @param x value for x-direction
	 * @param y value for y-direction
	 * @param z value for z-direction
	 */
	CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @return this
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/**
	 * Calculates the direct Cartesian distance between @param other
	 * and this coordinate.
	 *
	 * @param other coordinate to calculate distance to
	 * @return direct Cartesian distance between @param other and this
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("other is null");
		}

		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();

		double tmp = pow(this.x - otherCartesian.x, 2);
		tmp += pow(this.y - otherCartesian.y, 2);
		tmp += pow(this.z - otherCartesian.z, 2);
		return sqrt(tmp);
	}

	/**
	 * Converts this coordinate into the spheric coordinate system.
	 *
	 * @return converted spheric coordinate
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));

		if (radius == 0) {
			throw new IllegalStateException("radius is 0");
		}

		double phi = acos(y / radius);
		double theta = atan2(y, x);
		return new SphericCoordinate(phi, theta, radius);
	}

	/**
	 * Calculates the central angle between @param other and this coordinate.
	 *
	 * @param other coordinate to calculate angle with
	 * @return central angle between @param other and this
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("other is null");
		}

		return asSphericCoordinate().getCentralAngle(other);
	}

	/**
	 * Compares, whether @param other and this class point to the same location.
	 *
	 * @param other coordinate to compare with
	 * @return true, if x, y and z of @param other and this coordinate are equal. Otherwise false.
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("other is null");
		}

		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();

		boolean equalX = abs(x - otherCartesian.x) <= EQUAL_DELTA;
		boolean equalY = abs(y - otherCartesian.y) <= EQUAL_DELTA;
		boolean equalZ = abs(z - otherCartesian.z) <= EQUAL_DELTA;

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
		if(!(other instanceof CartesianCoordinate)) {
			return false;
		}

		return isEqual((CartesianCoordinate) other);
	}
}
