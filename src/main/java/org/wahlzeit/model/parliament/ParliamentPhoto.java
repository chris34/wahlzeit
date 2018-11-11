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

package org.wahlzeit.model.parliament;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

@Subclass
public class ParliamentPhoto extends Photo {

	private int yearBuild;
	private String architectureStyle;

	/**
	 * @methodtype constructor
	 */
	public ParliamentPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public ParliamentPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype setter
	 */
	public void setYearBuild(int newYear) {
		yearBuild = newYear;
	}

	/**
	 * @methodtype getter
	 */
	public int getYearBuild() {
		return yearBuild;
	}

	/**
	 * @methodtype setter
	 */
	public void setArchitectureStyle(String newStyle) {
		architectureStyle = newStyle;
	}

	/**
	 * @methodtype getter
	 */
	public String getArchitectureStyle() {
		return architectureStyle;
	}
}
