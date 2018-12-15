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

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.*;

/**
 * Represents a cartesian coordinate in a three dimensional room.
 * Thus, a coordinate is described by the three values x,
 * y and z.
 */
public class CartesianCoordinate extends AbstractCoordinate {
	private final double x;
	private final double y;
	private final double z;

	private static ConcurrentHashMap<Integer, CartesianCoordinate> objects = new ConcurrentHashMap<>();

	/**
	 * Use getInstance() to get a new CartesianCoordinate.
	 *
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
	}

	/**
	 * Factory method that returns a value object with the specified attributes.
	 * Internally, the objects are saved in a HashMap and are reused/shared. New
	 * objects are only created, if a object with given attributes is absent.
	 *
	 * @methodtype factory
	 *
	 * @param x value for x-direction
	 * @param y value for y-direction
	 * @param z value for z-direction
	 * @return an value object that has the specified attributes.
	 */
	public static CartesianCoordinate getInstance(double x, double y, double z) {
		if (!Double.isFinite(x)) {
			throw new IllegalArgumentException("x is not finite");
		}
		if (!Double.isFinite(y)) {
			throw new IllegalArgumentException("y is not finite");
		}
		if (!Double.isFinite(z)) {
			throw new IllegalArgumentException("z is not finite");
		}

		int hash = calculateHash(x, y, z);
		return objects.computeIfAbsent(hash, key -> new CartesianCoordinate(x, y, z));
	}

	/**
	 * @methodtype conversion
	 *
	 * @return this
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/**
	 * Converts this coordinate into the spheric coordinate system.
	 *
	 * @methodtype conversion
	 *
	 * @return converted spheric coordinate
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();

		double radius = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
		assert radius != 0 : "radius is 0";

		double phi = acos(y / radius);
		double theta = atan2(y, x);

		assertClassInvariants();

		return SphericCoordinate.getInstance(phi, theta, radius);
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Checks that all three dimensions are not NaN or infinity.
	 *
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() {
		assert Double.isFinite(x);
		assert Double.isFinite(y);
		assert Double.isFinite(z);
	}

	/**
	 * @methodtype helper
	 *
	 * @param x value for x-direction
	 * @param y value for y-direction
	 * @param z value for z-direction
	 * @return calculated hash
	 */
	private static int calculateHash(double x, double y, double z) {
		return Objects.hash(x, y, z);
	}

	/**
	 * see abstract super class for further description
	 * uses calculateHash
	 *
	 * @methodtype getter
	 *
	 * @return hashCode of current object
	 */
	@Override
	public int hashCode() {
		return calculateHash(x, y, z);
	}
}
