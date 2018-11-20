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

public abstract class AbstractCoordinate implements Coordinate {

	static final double EQUAL_DELTA = 1E-4;

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

		return asCartesianCoordinate().getCartesianDistance(other);
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
	 * @return true, if @param other and this coordinate are equal. Otherwise false.
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		return asCartesianCoordinate().isEqual(other);
	}
}
