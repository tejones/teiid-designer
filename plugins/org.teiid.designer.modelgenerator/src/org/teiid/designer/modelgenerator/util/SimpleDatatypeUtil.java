/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.util;

import org.eclipse.emf.ecore.EObject;

/**
 * DatatypeUtil
 *
 * @since 8.0
 */
public interface SimpleDatatypeUtil {
    
    public boolean isSimpleDatatypeNumeric(EObject datatype);
    
    public boolean isSimpleDatatypeString(EObject datatype);

}
