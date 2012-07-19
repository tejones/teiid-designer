/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.xml.modelextension;


/**
 * @since 8.0
 */
public interface ExtensionReplaceAction {

	public abstract String getExtensionName();

	public abstract ExtensionManager getExtensionManager();
}
