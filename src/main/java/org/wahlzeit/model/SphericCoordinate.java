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
 * Represents a spheric coordinate in three dimensions.
 * Thus, a coordinate is described by the three values phi,
 * theta and radius.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private final double phi;
	private final double theta;
	private final double radius;

	private static ConcurrentHashMap<Integer, SphericCoordinate> objects = new ConcurrentHashMap<>();


	/**
	 * Use getInstance() to get a new SphericCoordinate.
	 *
	 * @methodtype constructor
	 */
	private SphericCoordinate(double phi, double theta, double radius) {
		this.phi = phi;
		this.theta = theta;
		this.radius = radius;

		assertClassInvariants();
	}

	/**
	 * Factory method that returns a value object with the specified attributes.
	 * Internally, the objects are saved in a HashMap and are reused/shared. New
	 * objects are only created, if a object with given attributes is absent.
	 *
	 * @methodtype factory
	 *
	 * @param phi φ: Zenith angle (0 ≤ φ ≤ π)
	 * @param theta θ: Azimuth angle (0 ≤ θ ≤ 2π)
	 * @param radius r: Radial distance
	 * @return an value object that has the specified attributes.
	 */
	public static SphericCoordinate getInstance(double phi, double theta, double radius) {
		if (!Double.isFinite(phi) || phi < 0 || phi > PI) {
			throw new IllegalArgumentException("value of φ");
		}
		if (!Double.isFinite(theta) || theta < 0 || theta > 2*PI) {
			throw new IllegalArgumentException("value of θ");
		}
		if (!Double.isFinite(radius) || radius <= 0) {
			throw new IllegalArgumentException("radius has to be greater than 0");
		}

		int hash = calculateHash(phi, theta, radius);
		return objects.computeIfAbsent(hash, key -> new SphericCoordinate(phi, theta, radius));
	}

	/**
	 * Converts this spheric coordinate to an equal cartesian coordinate
	 *
	 * @methodtype conversion
	 *
	 * @return converted cartesian coordinate
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();

		return CartesianCoordinate.getInstance(
				radius * sin(phi) * cos(theta),
				radius * sin(phi) * sin(theta),
				radius * cos(phi)
		);
	}

	/**
	 * @methodtype conversion
	 *
	 * @return this
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/**
	 * @methodtype get
	 */
	public double getPhi() {
		return phi;
	}

	/**
	 * @methodtype get
	 */
	public double getTheta() {
		return theta;
	}

	/**
	 * Checks whether phi, theta and radius have an valid value.
	 *
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() {
		assert Double.isFinite(phi);
		assert phi >= 0;
		assert phi <= PI;

		assert Double.isFinite(theta);
		assert theta >= 0;
		assert theta <= 2*PI;

		assert Double.isFinite(radius);
		assert radius > 0;
	}

	/**
	 * @methodtype helper
	 *
	 * @param phi φ: Zenith angle (0 ≤ φ ≤ π)
	 * @param theta θ: Azimuth angle (0 ≤ θ ≤ 2π)
	 * @param radius r: Radial distance
	 * @return calculated hash
	 */
	private static int calculateHash(double phi, double theta, double radius) {
		return Objects.hash(phi, theta, radius);
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
		return calculateHash(phi, theta, radius);
	}
}
