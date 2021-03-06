/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.runtime.ui.views;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.teiid.designer.ui.common.util.WidgetFactory;

/**
 * The <code>MaskedTextPropertyDescriptor</code> class is a property editor for masked properties. The editor defaults to being
 * editable.
 *
 * @since 8.0
 */
public class MaskedTextPropertyDescriptor extends TextPropertyDescriptor {
    
    private boolean editable = true;

    /**
     * Creates an property descriptor with the given id and display name.
     * 
     * @param id the id of the property
     * @param displayName the name to display for the property
     */
    public MaskedTextPropertyDescriptor( Object id,
                                         String displayName ) {
        super(id, displayName);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.ui.views.properties.TextPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public CellEditor createPropertyEditor( Composite theParent ) {
        CellEditor result = super.createPropertyEditor(theParent);
        Text editor = (Text)result.getControl();
        editor.setEditable(this.editable);
        editor.setEchoChar(WidgetFactory.PASSWORD_ECHO_CHAR);
        return result;
    }
    
    /**
     * @param editable a flag indicating if the property editor is editable
     */
    public void setEditable(boolean editable) {
        this.editable = !editable;
    }

}
