/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.function.aspects.validation.rules;

import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.metamodels.function.ScalarFunction;
import org.teiid.designer.metamodels.function.util.FunctionUtil;

/**
 * ScalarFunctionUniquenessRule
 *
 * @since 8.0
 */
public class ScalarFunctionUniquenessRule extends FunctionEntityUniquenessRule {

    /**
     * Construct an instance of ScalarFunctionUniquenessRule.
     * 
     */
    public ScalarFunctionUniquenessRule() {
        super();
    }

    @Override
    protected String computeSignature(EObject eObject) {
        return FunctionUtil.getSignature((ScalarFunction)eObject);
    }
    
}
