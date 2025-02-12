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
package com.ibm.ws.security.javaeesec;

import java.util.Iterator;

import javax.enterprise.inject.Instance;
import javax.security.auth.message.config.AuthConfigFactory;
import javax.security.auth.message.config.AuthConfigProvider;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.ws.security.javaeesec.properties.ModulePropertiesProvider;
import com.ibm.ws.security.javaeesec.properties.ModulePropertiesUtils;
import com.ibm.ws.threadContext.ComponentMetaDataAccessorImpl;
import com.ibm.ws.webcontainer.osgi.webapp.WebAppConfiguration;

import test.common.SharedOutputManager;

public class BridgeBuilderImplTest {

    private final Mockery mockery = new JUnit4Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private static final String APP_CONTEXT = "localhost /contextRoot";

    private BridgeBuilderImpl bridgeBuilder;
    private AuthConfigFactory providerFactory;
    private AuthConfigProvider authConfigProvider;
    private Instance<HttpAuthenticationMechanism> hami;
    private HttpAuthenticationMechanism ham;
    private ModulePropertiesProvider mpp;
    private Iterator itl;
    private boolean isHAM;

    private static SharedOutputManager outputMgr = SharedOutputManager.getInstance();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        outputMgr.captureStreams();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        outputMgr.dumpStreams();
        outputMgr.resetStreams();
        outputMgr.restoreStreams();
    }

    @Before
    public void setUp() throws Exception {
        bridgeBuilder = new BridgeBuilderImplTestDouble();
        providerFactory = mockery.mock(AuthConfigFactory.class);
        authConfigProvider = null;
        mpp = mockery.mock(ModulePropertiesProvider.class);
        hami = mockery.mock(Instance.class, "hami");
        ham = mockery.mock(HttpAuthenticationMechanism.class, "ham");
        itl = mockery.mock(Iterator.class, "itl");
        WebAppConfiguration wac = new WebAppConfiguration(null, null);
        wac.setVirtualHostName("localhost");
        wac.setContextRoot("/contextRoot");
        ComponentMetaDataAccessorImpl.getComponentMetaDataAccessor().beginContext(wac.getDefaultComponentMetaData());
    }

    @After
    public void tearDown() throws Exception {
        ComponentMetaDataAccessorImpl.getComponentMetaDataAccessor().endContext();
        outputMgr.resetStreams();
        mockery.assertIsSatisfied();
    }

    @Test
    public void testNoAuthMechDoesNotRegisterProvider() throws Exception {
        withNoHAM().doesNotRegisterProvider();
        isHAM = false;
        bridgeBuilder.buildBridgeIfNeeded(null, providerFactory);
    }

    @Test
    public void testOneAuthMechRegistersProvider() throws Exception {
        isHAM = true;
        withNoCachedProvider();

        mockery.checking(new Expectations() {
            {
                one(providerFactory).registerConfigProvider(with(aNonNull(AuthConfigProvider.class)), with("HttpServlet"), with(APP_CONTEXT),
                                                            with(aNonNull(String.class)));
            }
        });

        bridgeBuilder.buildBridgeIfNeeded(null, providerFactory);
    }

    @Test
    public void testWithCachedProviderDoesNotRegisterProvider() throws Exception {
        isHAM = true;
        withCachedProvider().doesNotRegisterProvider();
        bridgeBuilder.buildBridgeIfNeeded(null, providerFactory);
    }

    private BridgeBuilderImplTest withNoCachedProvider() throws Exception {
        mockery.checking(new Expectations() {
            {
                exactly(2).of(providerFactory).getConfigProvider("HttpServlet", APP_CONTEXT, null);
                will(returnValue(null));
            }
        });
        return this;
    }

    private BridgeBuilderImplTest withNoHAM() throws Exception {
        mockery.checking(new Expectations() {
            {
                never(providerFactory).getConfigProvider("HttpServlet", APP_CONTEXT, null);
                will(returnValue(null));
            }
        });
        return this;
    }

    private BridgeBuilderImplTest withCachedProvider() throws Exception {
        authConfigProvider = mockery.mock(AuthConfigProvider.class);
        mockery.checking(new Expectations() {
            {
                one(providerFactory).getConfigProvider("HttpServlet", APP_CONTEXT, null);
                will(returnValue(authConfigProvider));
            }
        });
        return this;
    }

    private BridgeBuilderImplTest doesNotRegisterProvider() throws Exception {
        mockery.checking(new Expectations() {
            {
                never(providerFactory).registerConfigProvider(with(aNonNull(AuthConfigProvider.class)), with("HttpServlet"), with(APP_CONTEXT),
                                                              with(aNonNull(String.class)));
            }
        });
        return this;
    }

    class BridgeBuilderImplTestDouble extends BridgeBuilderImpl {

        @Override
        protected ModulePropertiesUtils getModulePropertiesUtils() {
            return new ModulePropertiesUtilsDouble();
        }
    }

    class ModulePropertiesUtilsDouble extends ModulePropertiesUtils {
        @Override
        public boolean isHttpAuthenticationMechanism() {
            return isHAM;
        }

    }

}
