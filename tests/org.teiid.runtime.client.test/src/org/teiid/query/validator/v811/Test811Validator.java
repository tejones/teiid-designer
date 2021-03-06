/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.query.validator.v811;

import org.teiid.designer.runtime.version.spi.TeiidServerVersion.Version;
import org.teiid.query.validator.v810.Test810Validator;

/**
 *
 */
@SuppressWarnings( {"javadoc"} )
public class Test811Validator extends Test810Validator {

    protected Test811Validator(Version teiidVersion) {
        super(teiidVersion);
    }

    public Test811Validator() {
        this(Version.TEIID_8_11);
    }
}
