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
public class ParliamentType extends DataObject {

	@Id
	protected String architectureStyle;

	public ParliamentType(String architectureStyle) {
		if (architectureStyle == null || architectureStyle.equals("")) {
			throw new IllegalArgumentException("tried to set null sub-type");
		}

		this.architectureStyle = architectureStyle;
	}

	public Parliament createInstance(String name, int yearBuild) {
		return new Parliament(this, name, yearBuild);
	}

	/**
	 * @methodtype query
	 *
	 * Returns whether this type is a subtype
	 * @return Currently always returns false as no hierarchie is implemented
	 */
	public boolean isSubtype() {
		return false;
	}

	/**
	 * @methodtype getter
	 */
	public String getArchitectureStyle() {
		return architectureStyle;
	}
}