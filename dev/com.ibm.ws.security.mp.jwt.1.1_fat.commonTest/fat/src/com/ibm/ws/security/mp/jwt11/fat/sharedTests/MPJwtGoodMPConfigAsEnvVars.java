/*******************************************************************************
 * Copyright (c) 2018, 2020 IBM Corporation and others.
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
package com.ibm.ws.security.mp.jwt11.fat.sharedTests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.ws.security.fat.common.mp.jwt.MPJwt11FatConstants;
import com.ibm.ws.security.fat.common.mp.jwt.sharedTests.MPJwt11MPConfigTests;

import componenttest.annotation.Server;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;

/**
 * This is the test class that will verify that we get the correct behavior when we
 * have mp-config defined as environment variables.
 * The classes that extend this class will set the environment variables in a variety of
 * valid ways.
 */

@RunWith(FATRunner.class)
public class MPJwtGoodMPConfigAsEnvVars extends MPJwt11MPConfigTests {

    @Server("com.ibm.ws.security.mp.jwt.1.1.fat")
    public static LibertyServer resourceServer;

    /**
     * The server will be started with all mp-config properties correctly set in environment variables.
     * The server.xml has a valid mp_jwt config specified.
     * The config settings should come from server.xml.
     * The test should run successfully .
     *
     * @throws Exception
     */
    @Test
    public void MPJwtGoodMPConfigAsEnvVars_GoodMpJwtConfigSpecifiedInServerXml() throws Exception {

        resourceServer.reconfigureServerUsingExpandedConfiguration(_testName, "rs_server_AltConfigNotInApp_goodServerXmlConfig.xml");
        standard11TestFlow(resourceServer, MPJwt11FatConstants.NO_MP_CONFIG_IN_APP_ROOT_CONTEXT,
                           MPJwt11FatConstants.NO_MP_CONFIG_IN_APP_APP, MPJwt11FatConstants.MPJWT_APP_CLASS_NO_MP_CONFIG_IN_APP);

    }

    /**
     * The server will be started with all mp-config properties correctly set in environment variables.
     * The server.xml has NO mp_jwt config specified.
     * The config settings should come from the env vars.
     * The test should run successfully.
     *
     * @throws Exception
     */
    //@Mode(TestMode.LITE)
    @Test
    public void MPJwtGoodMPConfigAsEnvVars_MpJwtConfigNotSpecifiedInServerXml() throws Exception {

        standard11TestFlow(resourceServer, MPJwt11FatConstants.NO_MP_CONFIG_IN_APP_ROOT_CONTEXT,
                           MPJwt11FatConstants.NO_MP_CONFIG_IN_APP_APP, MPJwt11FatConstants.MPJWT_APP_CLASS_NO_MP_CONFIG_IN_APP);

    }

}
