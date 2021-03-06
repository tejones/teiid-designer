/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.transformation.ui.builder.criteria;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.teiid.designer.transformation.ui.builder.AbstractLanguageObjectEditor;
import org.teiid.query.ui.builder.model.AbstractPredicateCriteriaTypeEditorModel;


/**
 * AbstractPredicateCriteriaTypeEditor
 *
 * @since 8.0
 */
public abstract class AbstractPredicateCriteriaTypeEditor extends AbstractLanguageObjectEditor
		implements IPredicateCriteriaTypeEditor {
			
	protected AbstractPredicateCriteriaTypeEditor(Composite theParent, Class theEditorType,
			AbstractPredicateCriteriaTypeEditorModel theModel) {
		super(theParent, theEditorType, theModel);
	}
	
	@Override
    protected final void createUi(Composite parent) {
		// don't all this method to be called by subclasses.
		// the parameter being passed in is ignored because all these editors don't use the parent
		// all subclasses left and right components are added to different containers.
		// SWT must reserve some size to empty Composites. so doing the following reduced the size. 
		GridData gd = new GridData();
		gd.widthHint = 0;
		gd.heightHint = 0;
		parent.setLayoutData(gd);
		parent.setVisible(false);
		
		if (parent.getLayout() instanceof GridLayout) {
			GridLayout layout = (GridLayout)parent.getLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
		}
	}
}
