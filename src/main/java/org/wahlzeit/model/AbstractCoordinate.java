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

public abstract class AbstractCoordinate implements Coordinate {

	static final double EQUAL_DELTA = 1E-4;

	/**
	 * Calculates the direct Cartesian distance between @param other
	 * and this coordinate.
	 * Internally, converts the two involved coordinates to cartesian coordinates.
	 *
	 * @methodtype get
	 *
	 * @param other coordinate to calculate distance to
	 * @return direct Cartesian distance between @param other and this
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		assertOtherNotNull(other);
		assertClassInvariants();

		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();

		double distance = pow(thisCartesian.getX() - otherCartesian.getX(), 2);
		distance += pow(thisCartesian.getY() - otherCartesian.getY(), 2);
		distance += pow(thisCartesian.getZ() - otherCartesian.getZ(), 2);
		distance = sqrt(distance);

		assert Double.isFinite(distance);
		assert distance >= 0;
		assertClassInvariants();

		return distance;
	}

	/**
	 * Calculates the central angle between @param other and this coordinate.
	 * Internally, converts the two involved coordinates to spheric coordinates.
	 *
	 * @methodtype get
	 *
	 * @param other coordinate to calculate angle with
	 * @return central angle between @param other and this
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		assertOtherNotNull(other);
		assertClassInvariants();

		SphericCoordinate thisSpheric = this.asSphericCoordinate();
		SphericCoordinate otherSpheric = other.asSphericCoordinate();

		double Δx = cos(otherSpheric.getPhi()) * cos(otherSpheric.getTheta())
				    - cos(thisSpheric.getPhi()) * cos(thisSpheric.getTheta());
		double Δy = cos(otherSpheric.getPhi()) * sin(otherSpheric.getTheta())
				    - cos(thisSpheric.getPhi()) * sin(thisSpheric.getTheta());
		double Δz = sin(otherSpheric.getPhi()) - sin(thisSpheric.getPhi());
		double c = sqrt(pow(Δx, 2) + pow(Δy, 2) + pow(Δz, 2));
		double angle = 2 * asin(c / 2);

		assert Double.isFinite(angle);
		assert angle > 0;
		assert angle < 2*PI;
		assertClassInvariants();

		return angle;
	}

	/**
	 * As all Coordinats are value object, this can be simplified to return
	 * itself. So there will be no copy object.
	 *
	 * @methodtype clone
	 *
	 * @return this
	 */
	@Override
	protected Object clone() {
		return this;
	}

   /**
	* Compares, whether @param other and this class point to the same location.
	* As all Coordinates are value objects, this can be a simple comparison with ==.
	*
	* @methodtype boolean-query
	*
	* @param o an Object to compare with
	* @return true, if @param o and this coordinate are equal. Otherwise false.
	*/
	public boolean equals(Object o) {
		return this == o;
	}

	/**
	 * Compares, whether @param other and this class point to the same location.
	 * Internally, converts the two involved coordinates to cartesian coordinates.
	 *
	 * @methodtype boolean-query
	 *
	 * @param other coordinate to compare with
	 * @return true, if x, y and z of @param other and this coordinate are equal. Otherwise false.
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		assertOtherNotNull(other);
		assertClassInvariants();

		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();

		boolean equalX = abs(thisCartesian.getX() - otherCartesian.getX()) <= EQUAL_DELTA;
		boolean equalY = abs(thisCartesian.getY() - otherCartesian.getY()) <= EQUAL_DELTA;
		boolean equalZ = abs(thisCartesian.getZ() - otherCartesian.getZ()) <= EQUAL_DELTA;
		boolean isEqual = equalX && equalY && equalZ;

		assertClassInvariants();

		return isEqual;
	}

	/**
	 * Assertion method to test, whether a parameter named "other" is not null. Otherwise raises an
	 * IllegalArgumentException.
	 *
	 * @methodtype assertion
	 *
	 * @param o Object to test, whether it is null
	 * @exception IllegalArgumentException, if o was null
	 */
	private void assertOtherNotNull(Object o) {
		if(o == null) {
			throw new IllegalArgumentException("Other is null");
		}
	}

	/**
	 * Checks the invariants of this class.
	 *
	 * @methodtype assertion
	 */
	protected abstract void assertClassInvariants();

	/**
	 * Require a custom hashCode in every Coordinate subclass, as those
	 * instantiate value objects. The hash should be calculated with the
	 * involvement of every class attribute.
	 *
	 * @methodtype getter
	 *
	 * @return hashCode of current object
	 */
	@Override
	abstract public int hashCode();
}
