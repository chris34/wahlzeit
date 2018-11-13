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

public interface Coordinate {

	/**
	 * Converts a spheric coordinate to an equal cartesian coordinate. If it is already
	 * an cartesian coordinate, it is just returned.
	 *
	 * @return the cartesian coordinate
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Calculates the direct Cartesian distance between @param other
	 * and this coordinate.
	 *
	 * @param other coordinate to calculate distance to
	 * @return direct Cartesian distance between @param other and this
	 */
	double getCartesianDistance(Coordinate other);

	/**
	 * Converts a cartesian coordinate to an equal spheric coordinate. If it is already
	 * an spheric coordinate, it is just returned.
	 *
	 * @return the spheric coordinate
	 */
	SphericCoordinate asSphericCoordinate();

	/**
	 * Calculates the central angle between @param other and this coordinate.
	 * See https://en.wikipedia.org/wiki/Great-circle_distance for a definition
	 *
	 * @param other coordinate to calculate angle with
	 * @return central angle between @param other and this
	 */
	double getCentralAngle(Coordinate other);

	/**
	 * Compares, whether @param other and this class point to the same location.
	 *
	 * @param other coordinate to compare with
	 * @return true, if @param other and this coordinate are equal. Otherwise false.
	 */
	boolean isEqual(Coordinate other);
}
