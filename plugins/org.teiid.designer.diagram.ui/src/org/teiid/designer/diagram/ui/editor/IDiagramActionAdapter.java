/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.editor;
  
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.teiid.designer.ui.common.actions.GlobalActionsMap;
import org.teiid.designer.ui.editors.ModelEditorPage;


/**
 * DiagramActionAdapter
 *
 * @since 8.0
 */
public interface IDiagramActionAdapter {
    
    void pageActivated();
    
    void pageDeactivated();

    void setDiagramEditor( ModelEditorPage editor);
    
    void contributeToMenuManager(IMenuManager theMenuMgr, ISelection selection);
    
    boolean shouldOverrideMenu(ISelection selection);
    
    void contributeExportedActions(IMenuManager theMenuMgr);
    
    GlobalActionsMap getGlobalActions();
    
    void contributeToDiagramToolBar();
    
    void enableDiagramToolbarActions();
    
    void disposeOfActions();
    
    void handleNotification(Notification theNotification);
        
}
