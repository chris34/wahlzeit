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

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.PhotoId;

import static org.junit.Assert.assertEquals;

/**
 * All test cases of the class {@link ParliamentPhoto}.
 */
public class ParliamentPhotoTest {
	private ParliamentPhoto p;

	@Before
	public void setUp() {
		p = new ParliamentPhoto();
	}

	@Test
	public void testConstructor() {
		PhotoId id = PhotoId.getRandomId();
		ParliamentPhoto p2 = new ParliamentPhoto(id);
		assertEquals(id, p2.getId());
	}

	@Test
	public void testYearbuild() {
		int year = 1887;
		p.setYearBuild(year);
		assertEquals(year, p.getYearBuild());
	}

	@Test
	public void testArchitectureStyle() {
		String style = "Neorenaissance";
		p.setArchitectureStyle(style);
		assertEquals(style, p.getArchitectureStyle());
	}
}
