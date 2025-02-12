/*******************************************************************************
 * Copyright (c) 2017, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fvtweb.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.mail.MailSessionDefinition;

/**
 * EJB to test Javamail annotation.
 */
@Stateless
@MailSessionDefinition(name = "javamail/jmEjbDef", user = "ejbTest", password = "testPass")
public class JavamailTestBean implements JavamailTestLocal {

    @Resource(name = "javamail/jmEjb",
              lookup = "java:comp/env/javamail/jmEjbDef")
    jakarta.mail.Session ms;

    @Override
    public void testLookupJavamailAnnotation() throws Exception {

        if (ms == null)
            throw new Exception("Mail session was null");
    }
}
