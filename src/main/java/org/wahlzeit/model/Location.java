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

/**
 * Represent an place
 */
public class Location {
	public Coordinate coordinate;

	/**
	 * Construct that takes the three dimensions to create
	 * a new Coordinate instance. Latter will then be used as coordinate.
	 *
	 * @param x value for x-direction
	 * @param y value for y-direction
	 * @param z value for z-direction
	 */
	Location(double x, double y, double z) {
		coordinate = new Coordinate(x, y, z);
	}

	/**
	 * Constructor that takes an Coordinate object
	 *
	 * @param coordinate Coordinate object to use for this location
	 */
	Location(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Construct that initializes coordinate with null
	 */
	Location() {
		coordinate = null;
	}
}
