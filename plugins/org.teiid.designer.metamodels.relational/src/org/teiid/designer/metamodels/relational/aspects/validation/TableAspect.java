/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.relational.aspects.validation;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.metamodel.aspect.AspectManager;
import org.teiid.designer.core.metamodel.aspect.MetamodelEntity;
import org.teiid.designer.core.metamodel.aspect.sql.SqlTableAspect;
import org.teiid.designer.core.validation.ValidationContext;
import org.teiid.designer.core.validation.ValidationRuleSet;


/**
 * TableAspect
 *
 * @since 8.0
 */
public abstract class TableAspect extends RelationalEntityAspect {
    
    /**
     * Construct an instance of TableAspect.
     * @param entity
     */
    public TableAspect(MetamodelEntity entity){
        super(entity);
    }
    
	/**
	 * Get all the validation rules for Table.
	 */
	@Override
    public ValidationRuleSet getValidationRules() {
		addRule(TABLE_UPDATABILITY_RULE);
        addRule(TABLE_UNIQUE_KEYS_RULE);
        addRule(TABLE_MATERIALIZED_RULE);
        addRule(TABLE_MISSING_NAME_IN_SOURCE_RULE);
		return super.getValidationRules();		
	}

	/* (non-Javadoc)
	 * @See org.teiid.designer.core.metamodel.aspect.ValidationAspect#updateContext(org.teiid.designer.core.validation.ValidationContext)
	 */
	@Override
    public void updateContext(final EObject eObject, final ValidationContext context) {
		Map transformMap = context.getTargetTransformMap();
		SqlTableAspect tableAspect = (SqlTableAspect) AspectManager.getSqlAspect(eObject);
		if(tableAspect.isVirtual(eObject)) {
			if(transformMap != null) {
				if(transformMap.containsKey(eObject)) {
					return;
				}
			}
			context.addTargetTransform(eObject, null);
		}
	}
}
