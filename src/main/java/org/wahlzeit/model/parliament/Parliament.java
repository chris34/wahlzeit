/*
 * Copyright (c) 2019 by Christoph Volkert
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

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.wahlzeit.services.DataObject;

@Entity
public class Parliament extends DataObject {

	private int yearBuild;
	private String name;
	private ParliamentType parliamentType;

	@Id
	long id;

	/**
	 * @methodtype constructor
	 * @param parliamentType ParliamentType of this Parlimant
	 */
	public Parliament(ParliamentType parliamentType, String name, int yearBuild) {
		this.parliamentType = parliamentType;
		this.name = name;
		this.yearBuild = yearBuild;
	}

	/**
	 * @methodtype getter
	 */
	public ParliamentType getType() {
		return parliamentType;
	}

	/**
	 * @methodtype setter
	 */
	public void setType(ParliamentType newType) {
		parliamentType = newType;
	}

	/**
	 * @methodtype getter
	 */
	public int getId() {
		return this.hashCode();
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
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * @methodtype getter
	 */
	public String getName() {
		return name;
	}

}
