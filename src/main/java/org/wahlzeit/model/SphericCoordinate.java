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
 * Represents a spheric coordinate in three dimensions.
 * Thus, a coordinate is described by the three values phi,
 * theta and radius.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private final double phi;
	private final double theta;
	private final double radius;

	/**
	 * Constructor to instantiate a new SphericCoordinate
	 *
	 * @param phi φ: Zenith angle (0 ≤ φ ≤ π)
	 * @param theta θ: Azimuth angle (0 ≤ θ ≤ 2π)
	 * @param radius r: Radial distance
	 */
	SphericCoordinate(double phi, double theta, double radius) {
		if(phi < 0 || phi > PI) {
			throw new IllegalArgumentException("value of φ");
		}

		if(theta < 0 || theta > 2*PI) {
			throw new IllegalArgumentException("value of θ");
		}

		this.phi = phi;
		this.theta = theta;
		this.radius = radius;
	}

	/**
	 * Converts this spheric coordinate to an equal cartesian coordinate
	 *
	 * @return converted cartesian coordinate
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return new CartesianCoordinate(
				radius * sin(phi) * cos(theta),
				radius * sin(phi) * sin(theta),
				radius * cos(phi)
		);
	}

	/**
	 * @return this
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	public double getPhi() {
		return phi;
	}

	public double getTheta() {
		return theta;
	}
}
