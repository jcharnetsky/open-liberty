/*******************************************************************************
 * Copyright (c) 1997, 2009 IBM Corporation and others.
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
package com.ibm.websphere.command.web;

/**
 * This CacheableCommandImpl abstract class provides an implementation
 * for all CacheableCommand interface methods except those that the
 * command writer must write.
 * This class provides a runtime for command execution that interacts
 * with the CommandCache.
 * It also provides the contract between this command runtime and
 * the command writer.
 * <p>
 * CacheableCommandImpl is a super class of all CacheableCommands.
 * @ibm-api 
 */
public abstract class CacheableCommandImpl extends com.ibm.websphere.command.CacheableCommandImpl {
}
