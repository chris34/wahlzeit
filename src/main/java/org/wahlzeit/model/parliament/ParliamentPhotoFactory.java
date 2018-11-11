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

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

/**
 * An Factory for creating parliament photos and related objects.
 */
public class ParliamentPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(ParliamentPhotoFactory.class.getName());

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	protected static ParliamentPhotoFactory instance = null;

	/**
	 * @methodtype constructor
	 */
	protected ParliamentPhotoFactory() {
		super();
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized ParliamentPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting ParliamentPhotoFactory").toString());
			setInstance(new ParliamentPhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(ParliamentPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize ParliamentPhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	public ParliamentPhoto createPhoto() {
		return new ParliamentPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 */
	public ParliamentPhoto createPhoto(PhotoId id) {
		return new ParliamentPhoto(id);
	}
}
