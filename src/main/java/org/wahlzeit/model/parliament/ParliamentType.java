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

import java.util.HashSet;
import java.util.Iterator;


@Entity
public class ParliamentType extends DataObject {

	protected ParliamentType superType = null;
	protected HashSet<ParliamentType> subTypes = new HashSet<>();

	@Id
	protected String architecturStyle;

	public ParliamentType(String architecturStyle) {
		if (architecturStyle == null || architecturStyle.equals("")) {
			throw new IllegalArgumentException("tried to set null sub-type");
		}

		this.architecturStyle = architecturStyle;
		incWriteCount();
	}

	public Parliament createInstance() {
		return new Parliament(this);
	}

	public void setSuperType(ParliamentType newSuperType) {
		superType = newSuperType;
	}

	public ParliamentType getSuperType() {
		return superType;
	}

	public Iterator<ParliamentType> getSubTypeIterator() {
		return subTypes.iterator();
	}

	public void addSubType(ParliamentType newSubType) {
		if (newSubType == null) {
			throw new IllegalArgumentException("tried to set null sub-type");
		}

		newSubType.setSuperType(this);
		subTypes.add(newSubType);
	}

	/**
	 * Returns whether this type is a subtype
	 * @return true, if it is a subtype. Otherwise false.
	 */
	public boolean isSubtype() {
		return getSuperType() != null;
	}

	public boolean hasInstance(Parliament parliament) {
		if (parliament == null) {
			throw new IllegalArgumentException("asked about null object");
		}

		if (parliament.getType() == this) {
			return true;
		}

		for (ParliamentType type : subTypes) {
			if (type.hasInstance(parliament)) {
				return true;
			}
		}

		return false;
	}

	public String getArchitecturStyle() {
		return architecturStyle;
	}
}