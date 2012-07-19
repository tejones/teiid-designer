/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.function.aspects.validation.rules;

import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.metamodels.function.FunctionParameter;
import org.teiid.designer.metamodels.function.util.FunctionUtil;

/**
 * FunctionParameterUniquenessRule
 *
 * @since 8.0
 */
public class FunctionParameterUniquenessRule extends FunctionEntityUniquenessRule {

    /**
     * Construct an instance of FunctionParameterUniquenessRule.
     * 
     */
    public FunctionParameterUniquenessRule() {
        super();
    }

    @Override
    protected String computeSignature(EObject eObject) {
        return FunctionUtil.getSignature((FunctionParameter)eObject);
    }

}
