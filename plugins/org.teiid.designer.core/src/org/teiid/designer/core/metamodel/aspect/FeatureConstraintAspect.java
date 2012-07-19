/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.metamodel.aspect;

import org.eclipse.core.runtime.IStatus;

/**
 * FeatureConstraintAspect
 *
 * @since 8.0
 */
public interface FeatureConstraintAspect extends MetamodelAspect {
    /**
     * Return an IStatus after validating the given value for this apsect
     * @param value
     * @return IStatus for validation result
     */
    IStatus isValidValue(Object value);
}
