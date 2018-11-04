/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
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
package org.wahlzeit.services.mailing;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailServiceTest {

	EmailService emailService = null;
	EmailAddress validAddress = null;

	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddress = EmailAddress.getFromString("test@test.de");
	}

	@Test(expected = Test.None.class /* no exception expected */)
	public void testSendInvalidEmail() {
		assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
		assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
		assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
	}

	@Test(expected = Test.None.class /* no exception expected */)
	public void testSendValidEmail() {
		assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
	}

	@Test(expected = Test.None.class /* no exception expected */)
	public void testSendInvalidEmail_withBCC() {
		assertFalse(emailService.sendEmailIgnoreException(validAddress, null, validAddress, "lol", "hi"));
		assertFalse(emailService.sendEmailIgnoreException(null, validAddress, validAddress, null, "body"));
		assertFalse(emailService.sendEmailIgnoreException(validAddress, null, validAddress, "hi", "       "));
	}

	@Test(expected = Test.None.class /* no exception expected */)
	public void testSendValidEmail_withBCC() {
		assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, validAddress, "hi", "test"));
	}

}