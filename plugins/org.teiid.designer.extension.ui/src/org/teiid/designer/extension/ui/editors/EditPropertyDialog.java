/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.extension.ui.editors;

import static org.teiid.designer.extension.ui.UiConstants.UTIL;
import static org.teiid.designer.extension.ui.UiConstants.Form.COMBO_STYLE;
import static org.teiid.designer.extension.ui.UiConstants.Form.SECTION_STYLE;
import static org.teiid.designer.extension.ui.UiConstants.Form.VIEWER_STYLE;
import static org.teiid.designer.extension.ui.UiConstants.ImageIds.MED_EDITOR;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.teiid.designer.extension.definition.ModelExtensionDefinitionValidator;
import org.teiid.designer.extension.properties.ModelExtensionPropertyDefinition;
import org.teiid.designer.extension.properties.ModelExtensionPropertyDefinition.PropertyName;
import org.teiid.designer.extension.properties.ModelExtensionPropertyDefinition.Type;
import org.teiid.designer.extension.properties.ModelExtensionPropertyDefinitionImpl;
import org.teiid.designer.extension.properties.Translation;
import org.teiid.designer.extension.ui.Activator;
import org.teiid.designer.extension.ui.Messages;
import org.teiid.designer.extension.ui.UiConstants.ImageIds;

import com.metamatrix.core.util.CoreArgCheck;
import com.metamatrix.core.util.CoreStringUtil;
import com.metamatrix.modeler.internal.ui.forms.FormUtil;
import com.metamatrix.ui.internal.util.WidgetUtil;

/**
 * The <code>EditPropertyDialog</code> is used to create or edit a property definition.
 */
final class EditPropertyDialog extends FormDialog {

    private final String metaclassName;
    private final String namespacePrefix;

    private Button btnEditDescription;
    private Button btnEditDisplayName;
    private Button btnOk;
    private Button btnRemoveDescription;
    private Button btnRemoveDisplayName;

    private TableViewer descriptionViewer;
    private TableViewer displayNameViewer;

    private final Collection<String> existingPropIds;

    private IManagedForm managedForm;

    /**
     * The property definition being edited or <code>null</code> when creating a new property definition.
     */
    private ModelExtensionPropertyDefinition propDefnBeingEdited;
    private ModelExtensionPropertyDefinitionImpl propDefn;

    private ScrolledForm scrolledForm;

    private final ErrorMessage advancedError;
    private final ErrorMessage defaultValueError;
    private final ErrorMessage displayNameError;
    private final ErrorMessage descriptionError;
    private final ErrorMessage fixedValueError;
    private final ErrorMessage indexedError;
    private final ErrorMessage maskedError;
    private final ErrorMessage modifiableError;
    private final ErrorMessage requiredError;
    private final ErrorMessage runtimeTypeError;
    private final ErrorMessage simpleIdError;
    private final ErrorMessage typeError;

    public EditPropertyDialog( Shell shell,
                               String namespacePrefix,
                               String metaclassName,
                               Collection<String> existingPropIds ) {
        super(shell);

        CoreArgCheck.isNotNull(metaclassName, "metaclassName is null"); //$NON-NLS-1$
        CoreArgCheck.isNotNull(namespacePrefix, "namespacePrefix is null"); //$NON-NLS-1$
        this.metaclassName = metaclassName;
        this.namespacePrefix = namespacePrefix;

        this.existingPropIds = new ArrayList<String>(existingPropIds);
        this.propDefn = new ModelExtensionPropertyDefinitionImpl();

        // errors
        this.advancedError = new ErrorMessage();
        this.defaultValueError = new ErrorMessage();
        this.displayNameError = new ErrorMessage();
        this.descriptionError = new ErrorMessage();
        this.fixedValueError = new ErrorMessage();
        this.indexedError = new ErrorMessage();
        this.maskedError = new ErrorMessage();
        this.modifiableError = new ErrorMessage();
        this.requiredError = new ErrorMessage();
        this.simpleIdError = new ErrorMessage();
        this.runtimeTypeError = new ErrorMessage();
        this.typeError = new ErrorMessage();
    }

    public EditPropertyDialog( Shell shell,
                               String namespacePrefix,
                               String metaclassName,
                               Collection<String> existingPropIds,
                               ModelExtensionPropertyDefinition propDefnBeingEdited ) {
        this(shell, namespacePrefix, metaclassName, existingPropIds);
        this.propDefnBeingEdited = propDefnBeingEdited;

        if (this.propDefnBeingEdited != null) {
            this.propDefnBeingEdited = propDefnBeingEdited;

            // remove the property definition being edited
            this.existingPropIds.remove(this.propDefnBeingEdited.getSimpleId());

            this.propDefn.setAdvanced(this.propDefnBeingEdited.isAdvanced());
            this.propDefn.setAllowedValues(this.propDefnBeingEdited.getAllowedValues());
            this.propDefn.setDefaultValue(this.propDefnBeingEdited.getDefaultValue());
            this.propDefn.setDescription(this.propDefnBeingEdited.getDescription());
            this.propDefn.setDisplayName(this.propDefnBeingEdited.getDisplayName());
            this.propDefn.setFixedValue(this.propDefnBeingEdited.getFixedValue());
            this.propDefn.setIndex(this.propDefnBeingEdited.shouldBeIndexed());
            this.propDefn.setMasked(this.propDefnBeingEdited.isMasked());
            this.propDefn.setNamespacePrefix(this.propDefnBeingEdited.getNamespacePrefix());
            this.propDefn.setRequired(this.propDefnBeingEdited.isRequired());
            this.propDefn.setSimpleId(this.propDefnBeingEdited.getSimpleId());
            this.propDefn.setType(this.propDefnBeingEdited.getType());
        }
    }

    private void addMessage( ErrorMessage errorMsg ) {
        this.scrolledForm.getMessageManager().addMessage(errorMsg.getKey(), errorMsg.getMessage(), null, errorMsg.getMessageType());
    }

    private void configureColumn( TableViewerColumn viewerColumn,
                                  int columnIndex,
                                  String headerText,
                                  String headerToolTip,
                                  boolean resizable ) {
        viewerColumn.setLabelProvider(new TranslationLabelProvider(columnIndex));

        TableColumn column = viewerColumn.getColumn();
        column.setText(headerText);
        column.setToolTipText(headerToolTip);
        column.setMoveable(false);
        column.setResizable(resizable);
        column.pack();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell newShell ) {
        super.configureShell(newShell);

        if (isEditMode()) {
            newShell.setText(Messages.editMetaclassDialogTitle);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.MessageDialog#createButton(org.eclipse.swt.widgets.Composite, int, java.lang.String, boolean)
     */
    @Override
    protected Button createButton( Composite parent,
                                   int id,
                                   String label,
                                   boolean defaultButton ) {
        Button btn = super.createButton(parent, id, label, defaultButton);

        if (id == IDialogConstants.OK_ID) {
            // disable OK button initially
            this.btnOk = btn;
            btn.setEnabled(false);
        }

        return btn;
    }

    @SuppressWarnings("unused")
    private Section createDescriptionSection( Composite body,
                                              FormToolkit toolkit ) {
        final Section finalSection;

        SECTION: {
            Section section = FormUtil.createSection(this.managedForm, toolkit, body,
                                                     Messages.editPropertyDialogDescriptionSectionTitle,
                                                     Messages.editPropertyDialogDescriptionSectionDescription, SECTION_STYLE, true);
            finalSection = section;

            // configure section toolbar
            Button[] buttons = FormUtil.createSectionToolBar(finalSection, toolkit,
                                                             new Image[] {
                                                                     Activator.getDefault().getImage(ImageIds.ADD_DESCRIPTION),
                                                                     Activator.getDefault().getImage(ImageIds.EDIT_DESCRIPTION),
                                                                     Activator.getDefault().getImage(ImageIds.REMOVE_DESCRIPTION) });

            // configure add button
            buttons[0].addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleAddDescription();
                }
            });
            buttons[0].setToolTipText(Messages.editPropertyDialogAddDescriptionButtonToolTip);

            // configure edit button
            buttons[1].setEnabled(false);
            buttons[1].addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleEditDescription();
                }
            });
            buttons[1].setToolTipText(Messages.editPropertyDialogEditDescriptionButtonToolTip);
            this.btnEditDescription = buttons[1];

            // configure remove button
            buttons[2].setEnabled(false);
            buttons[2].addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleRemoveDescription();
                }
            });
            buttons[2].setToolTipText(Messages.propertiesPageRemoveMetaclassButtonToolTip);
            this.btnRemoveDescription = buttons[2];
        }

        final Composite finalContainer;

        CONTAINER: {
            Composite container = toolkit.createComposite(finalSection);
            finalContainer = container;
            container.setLayout(new GridLayout());
            container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            finalSection.setClient(container);
        }

        VIEWER: {
            Table table = toolkit.createTable(finalContainer, VIEWER_STYLE);
            this.descriptionError.widget = table;
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            table.setLayout(new GridLayout());
            table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            ((GridData)table.getLayoutData()).heightHint = table.getItemHeight() * 5;

            this.descriptionViewer = new TableViewer(table);
            this.descriptionViewer.setContentProvider(new IStructuredContentProvider() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
                 */
                @Override
                public void dispose() {
                    // nothing to do
                }

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
                 */
                @Override
                public Object[] getElements( Object inputElement ) {
                    return new Object[0]; // TODO implement
                }

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
                 *      java.lang.Object)
                 */
                @Override
                public void inputChanged( Viewer viewer,
                                          Object oldInput,
                                          Object newInput ) {
                    // nothing to do
                }
            });
            this.descriptionViewer.setLabelProvider(new LabelProvider());
            this.descriptionViewer.addDoubleClickListener(new IDoubleClickListener() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
                 */
                @Override
                public void doubleClick( DoubleClickEvent event ) {
                    handleEditDescription();
                }
            });
            this.descriptionViewer.addSelectionChangedListener(new ISelectionChangedListener() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
                 */
                @Override
                public void selectionChanged( SelectionChangedEvent event ) {
                    handleDescriptionSelected();
                }
            });

            COLUMNS: {
                final TableViewerColumn firstColumn = new TableViewerColumn(this.descriptionViewer, SWT.LEFT);
                configureColumn(firstColumn, ColumnIndexes.LOCALE, ColumnHeaders.LOCALE, ColumnToolTips.LOCALE, true);

                final TableViewerColumn lastColumn = new TableViewerColumn(this.descriptionViewer, SWT.LEFT);
                configureColumn(lastColumn, ColumnIndexes.TRANSLATION, ColumnHeaders.TRANSLATION, ColumnToolTips.TRANSLATION, true);

                // size last column to extend to width of table
                lastColumn.getColumn().addControlListener(new ControlAdapter() {

                    /**
                     * {@inheritDoc}
                     * 
                     * @see org.eclipse.swt.events.ControlAdapter#controlResized(org.eclipse.swt.events.ControlEvent)
                     */
                    @Override
                    public void controlResized( ControlEvent e ) {
                        lastColumn.getColumn().setWidth(finalContainer.getClientArea().width - firstColumn.getColumn().getWidth());
                    }
                });
            }

            // populate table
            this.descriptionViewer.setInput(this);
            WidgetUtil.pack(this.descriptionViewer);
        }

        return finalSection;
    }

    @SuppressWarnings("unused")
    private Section createDisplayNameSection( Composite body,
                                              FormToolkit toolkit ) {
        final Section finalSection;

        SECTION: {
            Section section = FormUtil.createSection(this.managedForm, toolkit, body,
                                                     Messages.editPropertyDialogDisplayNameSectionTitle,
                                                     Messages.editPropertyDialogDisplayNameSectionDescription, SECTION_STYLE, true);
            finalSection = section;

            // configure section toolbar
            Button[] buttons = FormUtil.createSectionToolBar(finalSection,
                                                             toolkit,
                                                             new Image[] {
                                                                     Activator.getDefault().getImage(ImageIds.ADD_DISPLAY_NAME),
                                                                     Activator.getDefault().getImage(ImageIds.EDIT_DISPLAY_NAME),
                                                                     Activator.getDefault().getImage(ImageIds.REMOVE_DISPLAY_NAME) });

            // configure add button
            buttons[0].addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleAddDisplayName();
                }
            });
            buttons[0].setToolTipText(Messages.editPropertyDialogAddDisplayNameButtonToolTip);

            // configure edit button
            buttons[1].setEnabled(false);
            buttons[1].addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleEditDisplayName();
                }
            });
            buttons[1].setToolTipText(Messages.editPropertyDialogEditDisplayNameButtonToolTip);
            this.btnEditDisplayName = buttons[1];

            // configure remove button
            buttons[2].setEnabled(false);
            buttons[2].addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleRemoveDisplayName();
                }
            });
            buttons[2].setToolTipText(Messages.editPropertyDialogRemoveDisplayNameButtonToolTip);
            this.btnRemoveDisplayName = buttons[2];
        }

        final Composite finalContainer;

        CONTAINER: {
            Composite container = toolkit.createComposite(finalSection);
            finalContainer = container;
            container.setLayout(new GridLayout());
            container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            finalSection.setClient(container);
        }

        VIEWER: {
            Table table = toolkit.createTable(finalContainer, VIEWER_STYLE);
            this.displayNameError.widget = table;
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            table.setLayout(new GridLayout());
            table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            ((GridData)table.getLayoutData()).heightHint = table.getItemHeight() * 5;

            this.displayNameViewer = new TableViewer(table);
            this.displayNameViewer.setContentProvider(new IStructuredContentProvider() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
                 */
                @Override
                public void dispose() {
                    // nothing to do
                }

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
                 */
                @Override
                public Object[] getElements( Object inputElement ) {
                    return new Object[0]; // TODO implement
                }

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
                 *      java.lang.Object)
                 */
                @Override
                public void inputChanged( Viewer viewer,
                                          Object oldInput,
                                          Object newInput ) {
                    // nothing to do
                }
            });
            this.displayNameViewer.setLabelProvider(new LabelProvider());
            this.displayNameViewer.addDoubleClickListener(new IDoubleClickListener() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
                 */
                @Override
                public void doubleClick( DoubleClickEvent event ) {
                    handleEditDisplayName();
                }
            });
            this.displayNameViewer.addSelectionChangedListener(new ISelectionChangedListener() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
                 */
                @Override
                public void selectionChanged( SelectionChangedEvent event ) {
                    handleDisplayNameSelected();
                }
            });

            COLUMNS: {
                final TableViewerColumn firstColumn = new TableViewerColumn(this.displayNameViewer, SWT.LEFT);
                configureColumn(firstColumn, ColumnIndexes.LOCALE, ColumnHeaders.LOCALE, ColumnToolTips.LOCALE, true);

                final TableViewerColumn lastColumn = new TableViewerColumn(this.displayNameViewer, SWT.LEFT);
                configureColumn(lastColumn, ColumnIndexes.TRANSLATION, ColumnHeaders.TRANSLATION, ColumnToolTips.TRANSLATION, true);

                // size last column to extend to width of table
                lastColumn.getColumn().addControlListener(new ControlAdapter() {

                    /**
                     * {@inheritDoc}
                     * 
                     * @see org.eclipse.swt.events.ControlAdapter#controlResized(org.eclipse.swt.events.ControlEvent)
                     */
                    @Override
                    public void controlResized( ControlEvent e ) {
                        lastColumn.getColumn().setWidth(finalContainer.getClientArea().width - firstColumn.getColumn().getWidth());
                    }
                });
            }

            // populate table
            this.displayNameViewer.setInput(this);
            WidgetUtil.pack(this.displayNameViewer);
        }

        return finalSection;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.forms.FormDialog#createFormContent(org.eclipse.ui.forms.IManagedForm)
     */
    @SuppressWarnings("unused")
    @Override
    protected void createFormContent( IManagedForm managedForm ) {
        this.managedForm = managedForm;

        FORM: {
            this.scrolledForm = managedForm.getForm();
            this.scrolledForm.setText(isEditMode() ? Messages.editPropertyDialogTitle : Messages.addPropertyDialogTitle);
            this.scrolledForm.setImage(Activator.getDefault().getImage(ImageIds.MED_EDITOR));
            this.scrolledForm.setMessage(Messages.propertyDialogMessage, IMessageProvider.INFORMATION);
        }

        FormToolkit toolkit = managedForm.getToolkit();
        toolkit.decorateFormHeading(this.scrolledForm.getForm());

        final Composite finalBody;

        BODY: {
            Composite body = this.scrolledForm.getBody();
            finalBody = body;
            body.setLayout(new GridLayout(2, true));
            body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        }

        SECTIONS: {
            final Section infoSection = createInfoSection(finalBody, toolkit);
            final Section propertyValueSection = createPropertyValueSection(finalBody, toolkit);
            final Section descriptionSection = createDescriptionSection(finalBody, toolkit);
            final Section displayNameSection = createDisplayNameSection(finalBody, toolkit);

            infoSection.descriptionVerticalSpacing = propertyValueSection.getTextClientHeightDifference();
            descriptionSection.descriptionVerticalSpacing = displayNameSection.getTextClientHeightDifference();
        }

        // must be done after constructor
        this.propDefn.addListener(new PropertyChangeListener() {

            /**
             * {@inheritDoc}
             * 
             * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
             */
            @Override
            public void propertyChange( PropertyChangeEvent e ) {
                handlePropertyChanged(e);
            }
        });
    }

    @SuppressWarnings("unused")
    private Section createInfoSection( Composite body,
                                       FormToolkit toolkit ) {
        final Section finalSection;

        SECTION: {
            Section section = FormUtil.createSection(this.managedForm, toolkit, body, Messages.editPropertyDialogInfoSectionTitle,
                                                     Messages.editPropertyDialogInfoSectionDescription, SECTION_STYLE, true);
            finalSection = section;
        }

        final Composite finalContainer;

        CONTAINER: {
            Composite container = toolkit.createComposite(finalSection);
            container.setLayout(new GridLayout(2, false));
            container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            finalSection.setClient(container);
            finalContainer = container;
        }

        METACLASS: {
            toolkit.createLabel(finalContainer, Messages.metaclassLabel);

            Label label = toolkit.createLabel(finalContainer, this.metaclassName);
            label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        }

        NAMESPACE_PREFIX: {
            toolkit.createLabel(finalContainer, Messages.namespacePrefixLabel);

            Label label = toolkit.createLabel(finalContainer, this.namespacePrefix);
            label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        }

        final Text finalTxtSimpleId;

        ID: {
            toolkit.createLabel(finalContainer, Messages.simpleIdLabel);

            Text txtSimpleId = toolkit.createText(finalContainer, CoreStringUtil.Constants.EMPTY_STRING, SWT.BORDER);
            finalTxtSimpleId = txtSimpleId;
            this.simpleIdError.widget = txtSimpleId;
            txtSimpleId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            txtSimpleId.setFocus();
            txtSimpleId.addModifyListener(new ModifyListener() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
                 */
                @Override
                public void modifyText( ModifyEvent e ) {
                    handleSimpleIdChanged(((Text)e.widget).getText());
                }
            });

            if (isEditMode()) {
                txtSimpleId.setText(this.propDefn.getSimpleId());
            }
        }

        RUNTIME_TYPE: {
            toolkit.createLabel(finalContainer, Messages.runtimeTypeLabel);

            CCombo cbxRuntimeType = new CCombo(finalContainer, COMBO_STYLE);
            toolkit.adapt(cbxRuntimeType, true, false);
            this.runtimeTypeError.widget = cbxRuntimeType;
            cbxRuntimeType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            // populate runtime types
            for (Type type : Type.values()) {
                cbxRuntimeType.add(type.getRuntimeType());
            }

            // set selection based on property definition
            if (isEditMode()) {
                String runtimeType = this.propDefn.getRuntimeType();

                if (!CoreStringUtil.isEmpty(runtimeType)) {
                    int index = cbxRuntimeType.indexOf(runtimeType);

                    if (index == -1) {
                        UTIL.log(NLS.bind(Messages.editPropertyDialogInvalidRuntimeTypeMsg, runtimeType));
                    } else {
                        cbxRuntimeType.select(index);
                    }
                }
            }

            cbxRuntimeType.addModifyListener(new ModifyListener() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
                 */
                @Override
                public void modifyText( ModifyEvent e ) {
                    handleRuntimeTypeChanged(((CCombo)e.widget).getText());
                }
            });

            cbxRuntimeType.addControlListener(new ControlAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.ControlAdapter#controlResized(org.eclipse.swt.events.ControlEvent)
                 */
                @Override
                public void controlResized( ControlEvent e ) {
                    ((CCombo)e.widget).setSize(finalTxtSimpleId.getSize());
                }
            });
        }

        FLAGS: {
            Section section = toolkit.createSection(finalContainer, ExpandableComposite.TITLE_BAR);
            section.setText(Messages.editPropertyDialogFlagsSectionTitle);
            section.setLayout(new GridLayout());
            section.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            ((GridData)section.getLayoutData()).horizontalSpan = 2;
            ((GridData)section.getLayoutData()).verticalIndent += ((GridLayout)finalContainer.getLayout()).verticalSpacing;
            Composite container = toolkit.createComposite(section, SWT.NONE);
            container.setLayout(new GridLayout(5, true));
            container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            section.setClient(container);

            Button btn = toolkit.createButton(container, Messages.requiredPropertyAttributeColumnHeader, SWT.CHECK);
            this.requiredError.widget = btn;
            btn.setSelection(this.propDefn.isRequired());
            btn.setToolTipText(Messages.requiredPropertyAttributeColumnHeaderToolTip);
            btn.addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleRequiredChanged(((Button)e.widget).getSelection());
                }
            });

            btn = toolkit.createButton(container, Messages.modifiablePropertyAttributeColumnHeader, SWT.CHECK);
            this.modifiableError.widget = btn;
            btn.setSelection(this.propDefn.isModifiable());
            btn.setToolTipText(Messages.modifiablePropertyAttributeColumnHeaderToolTip);
            btn.addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleModifiableChanged(((Button)e.widget).getSelection());
                }
            });

            btn = toolkit.createButton(container, Messages.advancedPropertyAttributeColumnHeader, SWT.CHECK);
            this.advancedError.widget = btn;
            btn.setSelection(this.propDefn.isAdvanced());
            btn.setToolTipText(Messages.advancedPropertyAttributeColumnHeaderToolTip);
            btn.addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleAdvancedChanged(((Button)e.widget).getSelection());
                }
            });

            btn = toolkit.createButton(container, Messages.maskedPropertyAttributeColumnHeader, SWT.CHECK);
            this.maskedError.widget = btn;
            btn.setSelection(this.propDefn.isMasked());
            btn.setToolTipText(Messages.maskedPropertyAttributeColumnHeaderToolTip);
            btn.addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleMaskedChanged(((Button)e.widget).getSelection());
                }
            });

            btn = toolkit.createButton(container, Messages.indexedPropertyAttributeColumnHeader, SWT.CHECK);
            this.indexedError.widget = btn;
            btn.setSelection(this.propDefn.shouldBeIndexed());
            btn.setToolTipText(Messages.indexedPropertyAttributeColumnHeaderToolTip);
            btn.addSelectionListener(new SelectionAdapter() {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e ) {
                    handleIndexedChanged(((Button)e.widget).getSelection());
                }
            });
        }

        return finalSection;
    }

    @SuppressWarnings("unused")
    private Section createPropertyValueSection( Composite body,
                                                FormToolkit toolkit ) {
        // TODO implement createPropertyValueSection
        // TODO assign error widgets here
        final Section finalSection;

        SECTION: {
            Section section = FormUtil.createSection(this.managedForm, toolkit, body,
                                                     Messages.editPropertyDialogPropertyValueSectionTitle,
                                                     Messages.editPropertyDialogPropertyValueSectionDescription, SECTION_STYLE,
                                                     true);
            finalSection = section;
        }

        return finalSection;
    }

    Translation getSelectedDescription() {
        IStructuredSelection selection = (IStructuredSelection)this.descriptionViewer.getSelection();
        return (selection.isEmpty() ? null : (Translation)selection.getFirstElement());
    }

    Translation getSelectedDisplayName() {
        IStructuredSelection selection = (IStructuredSelection)this.displayNameViewer.getSelection();
        return (selection.isEmpty() ? null : (Translation)selection.getFirstElement());
    }

    void handleAddDescription() {
        EditTranslationDialog dialog = new EditTranslationDialog(getShell(),
                                                                 Messages.addPropertyDescriptionDialogTitle,
                                                                 new ArrayList<Locale>());
        dialog.create();
        dialog.getShell().pack();

        if (dialog.open() == Window.OK) {
            Translation description = dialog.getTranslation();
            // TODO add new description
        }
    }

    void handleAddDisplayName() {
        EditTranslationDialog dialog = new EditTranslationDialog(getShell(),
                                                                 Messages.addPropertyDisplayNameDialogTitle,
                                                                 new ArrayList<Locale>());
        dialog.create();
        dialog.getShell().pack();

        if (dialog.open() == Window.OK) {
            Translation displayName = dialog.getTranslation();
            // TODO add new display name
        }
    }

    void handleAdvancedChanged( boolean newValue ) {
        this.propDefn.setAdvanced(newValue);
    }

    void handleDescriptionSelected() {
        IStructuredSelection selection = (IStructuredSelection)this.descriptionViewer.getSelection();
        boolean enable = !selection.isEmpty();

        if (this.btnEditDescription.getEnabled() != enable) {
            this.btnEditDescription.setEnabled(enable);
        }

        if (this.btnRemoveDescription.getEnabled() != enable) {
            this.btnRemoveDescription.setEnabled(enable);
        }
    }

    void handleDisplayNameSelected() {
        IStructuredSelection selection = (IStructuredSelection)this.descriptionViewer.getSelection();
        boolean enable = !selection.isEmpty();

        if (this.btnEditDisplayName.getEnabled() != enable) {
            this.btnEditDisplayName.setEnabled(enable);
        }

        if (this.btnRemoveDisplayName.getEnabled() != enable) {
            this.btnRemoveDisplayName.setEnabled(enable);
        }
    }

    void handleEditDescription() {
        assert (getSelectedDescription() != null) : "Edit description button is enabled and shouldn't be"; //$NON-NLS-1$
        EditTranslationDialog dialog = new EditTranslationDialog(getShell(),
                                                                 Messages.editPropertyDescriptionDialogTitle,
                                                                 new ArrayList<Locale>(),
                                                                 getSelectedDescription());
        dialog.create();
        dialog.getShell().pack();

        if (dialog.open() == Window.OK) {
            Translation translation = dialog.getTranslation();
            // TODO implement handleEditDescription
        }
    }

    void handleEditDisplayName() {
        assert (getSelectedDisplayName() != null) : "Edit display name button is enabled and shouldn't be"; //$NON-NLS-1$
        EditTranslationDialog dialog = new EditTranslationDialog(getShell(),
                                                                 Messages.editPropertyDescriptionDialogTitle,
                                                                 new ArrayList<Locale>(),
                                                                 getSelectedDisplayName());
        dialog.create();
        dialog.getShell().pack();

        if (dialog.open() == Window.OK) {
            Translation displayName = dialog.getTranslation();
            // TODO implement handleEditDisplayName
        }
    }

    void handleIndexedChanged( boolean newValue ) {
        this.propDefn.setIndex(newValue);
    }

    void handleMaskedChanged( boolean newValue ) {
        this.propDefn.setMasked(newValue);
    }

    void handleModifiableChanged( boolean newValue ) {
        // TODO implement handleModifiableChanged
    }

    void handlePropertyChanged( PropertyChangeEvent e ) {
        String propName = e.getPropertyName();
        ErrorMessage errorMsg = null;

        if (PropertyName.ADVANCED.toString().equals(propName)) {
            this.advancedError.message = ModelExtensionDefinitionValidator.validatePropertyAdvancedAttribute(this.propDefn.isAdvanced());
            errorMsg = this.advancedError;
        } else if (PropertyName.ALLOWED_VALUES.toString().equals(propName)) {
            // TODO implement
        } else if (PropertyName.DEFAULT_VALUE.toString().equals(propName)) {
            this.defaultValueError.message = ModelExtensionDefinitionValidator.validatePropertyDefaultValue(this.propDefn.getRuntimeType(),
                                                                                                            this.propDefn.getDefaultValue(),
                                                                                                            this.propDefn.getAllowedValues());
            errorMsg = this.defaultValueError;
            // TODO implement this
            // } else if (PropertyName.DESCRIPTION.equals(propName)) {
            // this.descriptionError.message =
            // ModelExtensionDefinitionValidator.validateTranslation(this.propDefn.getDescription());
            // errorMsg = this.descriptionError;
            // } else if (PropertyName.DISPLAY_NAME.equals(propName)) {
            // this.displayNameError.message =
            // ModelExtensionDefinitionValidator.validateTranslation(this.propDefn.getDisplayName());
            // errorMsg = this.displayNameError;
        } else if (PropertyName.FIXED_VALUE.toString().equals(propName)) {
            this.fixedValueError.message = ModelExtensionDefinitionValidator.validatePropertyFixedValue(this.propDefn.getRuntimeType(),
                                                                                                        this.propDefn.getFixedValue());
            errorMsg = this.fixedValueError;
        } else if (PropertyName.INDEX.toString().equals(propName)) {
            this.indexedError.message = ModelExtensionDefinitionValidator.validatePropertyIndexedAttribute(this.propDefn.shouldBeIndexed());
            errorMsg = this.indexedError;
        } else if (PropertyName.MASKED.toString().equals(propName)) {
            this.maskedError.message = ModelExtensionDefinitionValidator.validatePropertyMaskedAttribute(this.propDefn.isMasked());
            errorMsg = this.maskedError;
        } else if (PropertyName.REQUIRED.toString().equals(propName)) {
            this.requiredError.message = ModelExtensionDefinitionValidator.validatePropertyRequiredAttribute(this.propDefn.isRequired());
            errorMsg = this.requiredError;
        } else if (PropertyName.SIMPLE_ID.toString().equals(propName)) {
            this.simpleIdError.message = ModelExtensionDefinitionValidator.validatePropertySimpleId(this.propDefn.getSimpleId(),
                                                                                                    this.existingPropIds);
            errorMsg = this.simpleIdError;
        } else if (PropertyName.TYPE.toString().equals(propName)) {
            this.typeError.message = ModelExtensionDefinitionValidator.validatePropertyRuntimeType(this.propDefn.getRuntimeType());
            errorMsg = this.typeError;
        } else {
            // should be handling all property changes so this shouldn't happen
            UTIL.log("Property [" + propName + "] is not being handled by property change listener");
        }

        if (errorMsg != null) {
            addMessage(errorMsg);
        }

        if ((errorMsg == null) || CoreStringUtil.isEmpty(errorMsg.message)) {
            this.scrolledForm.setMessage(Messages.propertyDialogMessage, IMessageProvider.INFORMATION);
        }

        // update buttons enabled state
        updateState();
    }

    void handleRemoveDescription() {
        assert (getSelectedDescription() != null) : "Remove description button is enabled and shouldn't be"; //$NON-NLS-1$

        if (FormUtil.openQuestion(getShell(), Messages.removeDescriptionDialogTitle, Activator.getDefault().getImage(MED_EDITOR),
                                  NLS.bind(Messages.removeDescriptionDialogMsg, getSelectedDescription()))) {
            // TODO implement handleRemoveDescription
        }
    }

    void handleRemoveDisplayName() {
        assert (getSelectedDisplayName() != null) : "Remove display name button is enabled and shouldn't be"; //$NON-NLS-1$

        if (FormUtil.openQuestion(getShell(), Messages.removeDisplayNameDialogTitle, Activator.getDefault().getImage(MED_EDITOR),
                                  NLS.bind(Messages.removeDisplayNameDialogMsg, getSelectedDisplayName()))) {
            // TODO implement handleRemoveDisplayName
        }
    }

    void handleRequiredChanged( boolean newValue ) {
        this.propDefn.setRequired(newValue);
    }

    void handleRuntimeTypeChanged( String newValue ) {
        Type newType = null;

        try {
            newType = ModelExtensionPropertyDefinition.Utils.convertRuntimeType(newValue);
        } catch (Exception e) {
            UTIL.log(e);
        }

        this.propDefn.setType(newType);
    }

    void handleSimpleIdChanged( String newValue ) {
        this.propDefn.setSimpleId(newValue);
    }

    private boolean isEditMode() {
        return (this.propDefnBeingEdited != null);
    }

    private void updateState() {
        // TODO implement updateState
        boolean enable = (this.scrolledForm.getMessageType() != IMessageProvider.ERROR);

        // update UI controls
        if (enable) {
            if (isEditMode()) {
                // TODO if no changes then don't enable. need to change equals in prop defn class
                enable = this.propDefn.equals(this.propDefnBeingEdited);
            }

            if (this.btnOk.getEnabled() != enable) {
                this.btnOk.setEnabled(enable);
            }
        } else {
            if (this.btnOk.getEnabled()) {
                this.btnOk.setEnabled(false);
            }
        }
    }

    interface ColumnHeaders {
        String LOCALE = Messages.localeColumnText;
        String TRANSLATION = Messages.translationColumnText;
    }

    interface ColumnIndexes {
        int LOCALE = 0;
        int TRANSLATION = 1;
    }

    interface ColumnToolTips {
        String LOCALE = Messages.localeColumnToolTip;
        String TRANSLATION = Messages.translationColumnToolTip;
    }

    class TranslationLabelProvider extends ColumnLabelProvider {

        private final int columnIndex;

        public TranslationLabelProvider( final int columnIndex ) {
            this.columnIndex = columnIndex;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
         */
        @Override
        public Image getImage( Object element ) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
         */
        @Override
        public String getText( Object element ) {
            Translation translation = (Translation)element;

            if (ColumnIndexes.LOCALE == this.columnIndex) {
                return translation.getLocale().getDisplayLanguage();
            }

            if (ColumnIndexes.TRANSLATION == this.columnIndex) {
                return translation.getTranslation();
            }

            return super.getText(element);
        }

    }

}