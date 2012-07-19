/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.xsd;

import org.eclipse.xsd.util.XSDConstants;

/**
 * XSDInitializer1999
 *
 * @since 8.0
 */
public class XsdInitializer1999 extends AbstractXsdInitializer {

    /**
     * Construct an instance of XSDInitializer1999.
     * 
     */
    public XsdInitializer1999() {
        super();
    }
    
    /**
     * @see org.teiid.designer.metamodels.xsd.AbstractXsdInitializer#getXsdNamespace()
     */
    @Override
    protected String getXsdNamespace() {
        return XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999;
    }


}
