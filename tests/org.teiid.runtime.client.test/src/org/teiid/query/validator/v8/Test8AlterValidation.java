/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.query.validator.v8;

import org.junit.Test;
import org.teiid.designer.runtime.version.spi.ITeiidServerVersion;
import org.teiid.designer.runtime.version.spi.TeiidServerVersion.Version;
import org.teiid.query.sql.AbstractTestFactory;
import org.teiid.query.sql.v8.Test8Factory;
import org.teiid.query.validator.AbstractTestAlterValidation;

/**
 *
 */
@SuppressWarnings( {"nls" , "javadoc"} )
public class Test8AlterValidation extends AbstractTestAlterValidation {

    private Test8Factory factory;

    protected Test8AlterValidation(Version teiidVersion) {
        super(teiidVersion);
    }

    public Test8AlterValidation() {
        this(Version.TEIID_8_0);
    }

    @Override
    protected AbstractTestFactory getFactory() {
        if (factory == null)
            factory = new Test8Factory(getQueryParser());

        return factory;
    }

    @Test
    public void testValidateAlterProcedure() {
        helpValidate("alter procedure spTest8a as begin select 1; end",
                     new String[] {"spTest8a"},
                     getMetadataFactory().exampleBQTCached());
        helpValidate("alter procedure MMSP1 as begin select 1; end",
                     new String[] {"SELECT 1;"},
                     getMetadataFactory().exampleBQTCached());
    }
}
