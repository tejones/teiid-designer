/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.metamodel.util;

import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.index.IndexConstants;
import org.teiid.designer.core.metamodel.aspect.AspectManager;
import org.teiid.designer.core.metamodel.aspect.sql.SqlAspect;
import org.teiid.designer.core.metamodel.aspect.sql.SqlColumnSetAspect;
import org.teiid.designer.core.metamodel.aspect.sql.SqlModelAspect;
import org.teiid.designer.core.metamodel.aspect.sql.SqlProcedureAspect;
import org.teiid.designer.core.metamodel.aspect.sql.SqlTableAspect;


/**
 * @since 8.0
 */
public class ColumnSetNameFinder extends AbstractNameFinder {

    private final char recordType;

    public ColumnSetNameFinder( final String columnmSetName,
                                final char recordType,
                                final boolean isPartialname ) {
        super(columnmSetName, isPartialname);
        this.recordType = recordType;
    }

    /**
     * @see org.teiid.designer.core.util.ModelVisitor#visit(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean visit( final EObject eObject ) {
        if (!super.visit(eObject)) {
            return false;
        }
        final SqlAspect sqlAspect = AspectManager.getSqlAspect(eObject);
        if (sqlAspect != null) {
            final String fullName = sqlAspect.getFullName(eObject);
            if (isParentAspect(sqlAspect)) {
                // If the fullname of the parentAspect is not in the parent
                // path of the entity fullname then do not check the contents
                // of the parent
                if (fullName != null && !isParent(fullName.toUpperCase())) {
                    return false;
                }
            } else if (sqlAspect instanceof SqlColumnSetAspect && sqlAspect.isRecordType(this.recordType)) {
                // Check the ColumnSetAspect against the element fullname to match
                if (fullName != null && foundMatch(fullName.toUpperCase(), eObject)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isParentAspect( SqlAspect sqlAspect ) {
        if (sqlAspect instanceof SqlModelAspect) {
            return true;
        } else if ((recordType == IndexConstants.RECORD_TYPE.UNIQUE_KEY || recordType == IndexConstants.RECORD_TYPE.PRIMARY_KEY
                    || recordType == IndexConstants.RECORD_TYPE.FOREIGN_KEY
                    || recordType == IndexConstants.RECORD_TYPE.ACCESS_PATTERN || recordType == IndexConstants.RECORD_TYPE.INDEX)
                   && sqlAspect instanceof SqlTableAspect) {
            return true;
        } else if (recordType == IndexConstants.RECORD_TYPE.RESULT_SET && sqlAspect instanceof SqlProcedureAspect) {
            return true;
        }

        return false;
    }

}
