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


import com.googlecode.objectify.ObjectifyService;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.ObjectManager;

import java.util.*;
import java.util.logging.Logger;

public class ParliamentManager extends ObjectManager {

	private static ParliamentManager instance = new ParliamentManager();
	private static final Logger log = Logger.getLogger(ParliamentManager.class.getName()); // TODO rm

	private HashSet<Parliament> parliaments = new HashSet<>();
	private Map<String, ParliamentType> parliamentTypes = new HashMap<>();

	/**
	 * @methodtype constructor
	 * private, so from the outside getInstance() has to be used
	 */
	private ParliamentManager() {}

	/**
	 * @methodtype getter
	 */
	public static ParliamentManager getInstance() {
		return instance;
	}

	/**
	 * @methodtype getter
	 */
	private ParliamentType getParliamentType(String typeName) {
		return parliamentTypes.computeIfAbsent(typeName, key -> new ParliamentType(typeName));
	}

	public Parliament createParliament(String typeName) {
		ParliamentType pt = getParliamentType(typeName);
		Parliament parliament = pt.createInstance();
		// TODO replace with hashmap?
		//parliaments.put(parliament.getId(), parliament);
		parliaments.add(parliament);
		return parliament;
	}

	public void init() {
		loadParliamentTypes();
		loadParliaments();
	}

	/**
	 * @methodtype command
	 *
	 * Load all persisted parliaments. Executed when Wahlzeit is restarted.
	 */
	public void loadParliaments() {
		Collection<Parliament> existingParliaments = ObjectifyService.run(() -> {
			Collection<Parliament> parliamentsDB = new ArrayList<>();
			readObjects(parliamentsDB, Parliament.class);
			return parliamentsDB;
		});

		/* TODO
		for (Parliament parliament : existingParliaments) {
			parliaments.put(parliament.getId(), parliament);
		}*/
		log.info(LogBuilder.createSystemMessage().addMessage("All parliaments loaded.").toString());
	}

	/**
	 * @methodtype command
	 *
	 * Load all persisted parliaments. Executed when Wahlzeit is restarted.
	 */
	public void loadParliamentTypes() {
		Collection<ParliamentType> existingParliaments = ObjectifyService.run(() -> {
			Collection<ParliamentType> typesDB = new ArrayList<>();
			readObjects(typesDB, ParliamentType.class);
			return typesDB;
		});

		/* TODO
		for (ParliamentType type : existingParliaments) {
			parliamentTypes.put(type.getName(), type);
		}
		*/

		log.info(LogBuilder.createSystemMessage().addMessage("All parliamentTypes loaded.").toString());
	}

	public void save() {
		updateObjects(parliamentTypes.values());
		updateObjects(parliaments);
	}
}
