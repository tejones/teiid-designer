/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.designer.runtime.ui.actions;

import java.util.Iterator;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.teiid.designer.runtime.ui.DqpUiConstants;
import org.teiid.designer.runtime.ui.DqpUiPlugin;
import org.teiid.designer.runtime.ui.Messages;
import org.teiid.designer.runtime.ui.wizards.vdbs.GenerateDynamicVdbWizard;
import org.teiid.designer.ui.actions.SortableSelectionAction;
import org.teiid.designer.ui.common.eventsupport.SelectionUtilities;
import org.teiid.designer.ui.viewsupport.ModelUtilities;
import org.teiid.designer.vdb.ui.VdbUiPlugin;

public class GenerateDynamicVdbAction extends SortableSelectionAction implements DqpUiConstants {
    private static final String label = DqpUiConstants.UTIL.getString("label"); //$NON-NLS-1$

    /**
     * @since 5.0
     */
    public GenerateDynamicVdbAction() {
        super(label, SWT.DEFAULT);
        setImageDescriptor(DqpUiPlugin.getDefault().getImageDescriptor(Images.DYNAMIC_VDB));
    }

    /**
     * @see org.teiid.designer.ui.actions.SortableSelectionAction#isValidSelection(org.eclipse.jface.viewers.ISelection)
     * @since 5.0
     */
    @Override
    public boolean isValidSelection( ISelection selection ) {
        // Enable for single/multiple Virtual Tables
        return vdbSelected(selection);
    }

    /**
     * @see org.eclipse.jface.action.IAction#run()
     * @since 5.0
     */
    @Override
    public void run() {
        final IWorkbenchWindow iww = VdbUiPlugin.singleton.getCurrentWorkbenchWindow();
        
        Object obj = SelectionUtilities.getSelectedObject(getSelection());
        if (obj instanceof IFile) {
            IFile vdbXmlFile = (IFile)obj;

            try {
                GenerateDynamicVdbWizard wizard = new GenerateDynamicVdbWizard(vdbXmlFile);

                WizardDialog wd = new WizardDialog(getShell(), wizard);
                wd.open();
                return;
            } catch (Exception ex) {
                MessageDialog.openError(iww.getShell(),
                                        Messages.GenerateDynamicVdbAction_exceptionTitle,
                                        ex.getLocalizedMessage());
            }
        }

        MessageDialog.openInformation(iww.getShell(), 
        		Messages.GenerateDynamicVdbAction_nothingExportedTitle, 
        		Messages.GenerateDynamicVdbAction_nothingExportedMessage);

    }
    
    /**
     * @see org.teiid.designer.ui.actions.ISelectionAction#isApplicable(org.eclipse.jface.viewers.ISelection)
     * @since 5.0
     */
    @Override
    public boolean isApplicable( ISelection selection ) {
        return vdbSelected(selection);
    }

    private boolean vdbSelected( ISelection theSelection ) {
        boolean result = false;
        List<Object> allObjs = SelectionUtilities.getSelectedObjects(theSelection);
        if (!allObjs.isEmpty() && allObjs.size() == 1) {
            Iterator<Object> iter = allObjs.iterator();
            result = true;
            Object nextObj = null;
            while (iter.hasNext() && result) {
                nextObj = iter.next();

                if (nextObj instanceof IFile) {
                    result = ModelUtilities.isVdbFile((IFile)nextObj);
                } else {
                    result = false;
                }
            }
        }

        return result;
    }

    private Shell getShell() {
        return Display.getCurrent().getActiveShell();
    }
}
