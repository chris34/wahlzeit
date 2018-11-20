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

	private final double EQUAL_DELTA = 1E-4;

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

		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();

		double tmp = pow(thisCartesian.getX() - otherCartesian.getX(), 2);
		tmp += pow(thisCartesian.getY() - otherCartesian.getY(), 2);
		tmp += pow(thisCartesian.getZ() - otherCartesian.getZ(), 2);
		return sqrt(tmp);
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

		SphericCoordinate thisSpheric = this.asSphericCoordinate();
		SphericCoordinate otherSpheric = other.asSphericCoordinate();

		double Δx = cos(otherSpheric.getPhi()) * cos(otherSpheric.getTheta())
				    - cos(thisSpheric.getPhi()) * cos(thisSpheric.getTheta());
		double Δy = cos(otherSpheric.getPhi()) * sin(otherSpheric.getTheta())
				    - cos(thisSpheric.getPhi()) * sin(thisSpheric.getTheta());
		double Δz = sin(otherSpheric.getPhi()) - sin(thisSpheric.getPhi());
		double c = sqrt(pow(Δx, 2) + pow(Δy, 2) + pow(Δz, 2));

		return 2 * asin(c / 2);
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

		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();

		boolean equalX = abs(thisCartesian.getX() - otherCartesian.getX()) <= EQUAL_DELTA;
		boolean equalY = abs(thisCartesian.getY() - otherCartesian.getY()) <= EQUAL_DELTA;
		boolean equalZ = abs(thisCartesian.getZ() - otherCartesian.getZ()) <= EQUAL_DELTA;

		return equalX && equalY && equalZ;
	}
}
