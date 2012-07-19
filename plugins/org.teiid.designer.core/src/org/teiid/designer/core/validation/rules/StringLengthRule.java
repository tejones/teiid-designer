/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.validation.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.teiid.designer.core.validation.StructuralFeatureValidationRule;
import org.teiid.designer.core.validation.ValidationContext;
import org.teiid.designer.core.validation.ValidationResult;
import org.teiid.designer.core.validation.ValidationResultImpl;


/**
 * StringLengthRule, this rule checks the length of name of an entity against a known length. The length to validate against is
 * provided by the aspect creating this rule.
 *
 * @since 8.0
 */
public class StringLengthRule implements StructuralFeatureValidationRule {

    // maximum allowed length for the entity
    private int maxStringLength = StringNameValidator.DEFAULT_MAXIMUM_LENGTH;

    // id of the feature being validated
    private final int featureID;

    /**
     * Construct an instance of StringLengthRule.
     * 
     * @param maxStringLength Maximum length allowed for the entity
     * @param featureID ID of the feature to validate
     */
    public StringLengthRule( final int maxStringLength,
                             final int featureID ) {
        this.maxStringLength = maxStringLength;
        this.featureID = featureID;
    }

    /**
     * Construct an instance of StringLengthRule.
     * 
     * @param featureID ID of the feature to validate
     */
    public StringLengthRule( final int featureID ) {
        this.featureID = featureID;
    }

    /* (non-Javadoc)
     * @See org.teiid.designer.core.validation.ValidationRule#validate(java.lang.Object, org.teiid.designer.core.validation.ValidationContext)
     */
    @Override
	public void validate( final EStructuralFeature eStructuralFeature,
                          final EObject eObject,
                          final Object value,
                          final ValidationContext context ) {
        // nothing to validate
        if (value == null) {
            return;
        }
        // check if the feature matches the given feature
        if (eStructuralFeature.getFeatureID() != this.featureID) {
            return;
        }

        // Check that the value is an instance of java.lang.String
        // otherwise we cannot apply this rule
        if (!(value instanceof String)) {
            return;
        }

        // Apply the length validation to the string
        final String name = (String)value;

        // create a validation result
        final ValidationResult result = new ValidationResultImpl(eObject);
        // validate the string length
        CoreValidationRulesUtil.validateStringLength(result, this.maxStringLength, name);
        // add the result to the context
        context.addResult(result);
    }

}
