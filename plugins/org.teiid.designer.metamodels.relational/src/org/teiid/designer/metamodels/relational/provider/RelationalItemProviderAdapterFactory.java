/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.relational.provider;

import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.teiid.designer.metamodels.relational.util.RelationalAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 *
 * @since 8.0
 */
public class RelationalItemProviderAdapterFactory extends RelationalAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
    /**
     * This keeps track of the root adapter factory that delegates to this adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComposedAdapterFactory parentAdapterFactory;

    /**
     * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IChangeNotifier changeNotifier = new ChangeNotifier();

    /**
     * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Collection supportedTypes = new ArrayList();

    /**
     * This constructs an instance.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RelationalItemProviderAdapterFactory() {
        supportedTypes.add(IEditingDomainItemProvider.class);
        supportedTypes.add(IStructuredItemContentProvider.class);
        supportedTypes.add(ITreeItemContentProvider.class);
        supportedTypes.add(IItemLabelProvider.class);
        supportedTypes.add(IItemPropertySource.class);		
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.Column} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ColumnItemProvider columnItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.Column}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createColumnAdapter() {
        if (columnItemProvider == null) {
            columnItemProvider = new ColumnItemProvider(this);
        }

        return columnItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.Schema} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SchemaItemProvider schemaItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.Schema}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSchemaAdapter() {
        if (schemaItemProvider == null) {
            schemaItemProvider = new SchemaItemProvider(this);
        }

        return schemaItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.PrimaryKey} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PrimaryKeyItemProvider primaryKeyItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.PrimaryKey}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createPrimaryKeyAdapter() {
        if (primaryKeyItemProvider == null) {
            primaryKeyItemProvider = new PrimaryKeyItemProvider(this);
        }

        return primaryKeyItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.ForeignKey} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ForeignKeyItemProvider foreignKeyItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.ForeignKey}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createForeignKeyAdapter() {
        if (foreignKeyItemProvider == null) {
            foreignKeyItemProvider = new ForeignKeyItemProvider(this);
        }

        return foreignKeyItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.View} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ViewItemProvider viewItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.View}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createViewAdapter() {
        if (viewItemProvider == null) {
            viewItemProvider = new ViewItemProvider(this);
        }

        return viewItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.Catalog} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CatalogItemProvider catalogItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.Catalog}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createCatalogAdapter() {
        if (catalogItemProvider == null) {
            catalogItemProvider = new CatalogItemProvider(this);
        }

        return catalogItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.Procedure} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ProcedureItemProvider procedureItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.Procedure}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createProcedureAdapter() {
        if (procedureItemProvider == null) {
            procedureItemProvider = new ProcedureItemProvider(this);
        }

        return procedureItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.Index} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IndexItemProvider indexItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.Index}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createIndexAdapter() {
        if (indexItemProvider == null) {
            indexItemProvider = new IndexItemProvider(this);
        }

        return indexItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.ProcedureParameter} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ProcedureParameterItemProvider procedureParameterItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.ProcedureParameter}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createProcedureParameterAdapter() {
        if (procedureParameterItemProvider == null) {
            procedureParameterItemProvider = new ProcedureParameterItemProvider(this);
        }

        return procedureParameterItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.UniqueConstraint} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UniqueConstraintItemProvider uniqueConstraintItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.UniqueConstraint}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createUniqueConstraintAdapter() {
        if (uniqueConstraintItemProvider == null) {
            uniqueConstraintItemProvider = new UniqueConstraintItemProvider(this);
        }

        return uniqueConstraintItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.AccessPattern} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AccessPatternItemProvider accessPatternItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.AccessPattern}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createAccessPatternAdapter() {
        if (accessPatternItemProvider == null) {
            accessPatternItemProvider = new AccessPatternItemProvider(this);
        }

        return accessPatternItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.LogicalRelationship} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LogicalRelationshipItemProvider logicalRelationshipItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.LogicalRelationship}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createLogicalRelationshipAdapter() {
        if (logicalRelationshipItemProvider == null) {
            logicalRelationshipItemProvider = new LogicalRelationshipItemProvider(this);
        }

        return logicalRelationshipItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.LogicalRelationshipEnd} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LogicalRelationshipEndItemProvider logicalRelationshipEndItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.LogicalRelationshipEnd}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createLogicalRelationshipEndAdapter() {
        if (logicalRelationshipEndItemProvider == null) {
            logicalRelationshipEndItemProvider = new LogicalRelationshipEndItemProvider(this);
        }

        return logicalRelationshipEndItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.BaseTable} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BaseTableItemProvider baseTableItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.BaseTable}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createBaseTableAdapter() {
        if (baseTableItemProvider == null) {
            baseTableItemProvider = new BaseTableItemProvider(this);
        }

        return baseTableItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.teiid.designer.metamodels.relational.ProcedureResult} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ProcedureResultItemProvider procedureResultItemProvider;

    /**
     * This creates an adapter for a {@link org.teiid.designer.metamodels.relational.ProcedureResult}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createProcedureResultAdapter() {
        if (procedureResultItemProvider == null) {
            procedureResultItemProvider = new ProcedureResultItemProvider(this);
        }

        return procedureResultItemProvider;
    }

    /**
     * This returns the root adapter factory that contains this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
        return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
    }

    /**
     * This sets the composed adapter factory that contains this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
        this.parentAdapterFactory = parentAdapterFactory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object type) {
        return supportedTypes.contains(type) || super.isFactoryForType(type);
    }

    /**
     * This implementation substitutes the factory itself as the key for the adapter.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter adapt(Notifier notifier, Object type) {
        return super.adapt(notifier, this);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object adapt(Object object, Object type) {
        if (isFactoryForType(type)) {
            Object adapter = super.adapt(object, type);
            if (!(type instanceof Class) || (((Class)type).isInstance(adapter))) {
                return adapter;
            }
        }

        return null;
    }

    /**
     * This adds a listener.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
        changeNotifier.addListener(notifyChangedListener);
    }

    /**
     * This removes a listener.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
        changeNotifier.removeListener(notifyChangedListener);
    }

    /**
     * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public void fireNotifyChanged(Notification notification) {
        changeNotifier.fireNotifyChanged(notification);

        if (parentAdapterFactory != null) {
            parentAdapterFactory.fireNotifyChanged(notification);
        }
    }

    /**
     * This disposes all of the item providers created by this factory. 
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public void dispose() {
        if (columnItemProvider != null) columnItemProvider.dispose();
        if (schemaItemProvider != null) schemaItemProvider.dispose();
        if (primaryKeyItemProvider != null) primaryKeyItemProvider.dispose();
        if (foreignKeyItemProvider != null) foreignKeyItemProvider.dispose();
        if (viewItemProvider != null) viewItemProvider.dispose();
        if (catalogItemProvider != null) catalogItemProvider.dispose();
        if (procedureItemProvider != null) procedureItemProvider.dispose();
        if (indexItemProvider != null) indexItemProvider.dispose();
        if (procedureParameterItemProvider != null) procedureParameterItemProvider.dispose();
        if (uniqueConstraintItemProvider != null) uniqueConstraintItemProvider.dispose();
        if (accessPatternItemProvider != null) accessPatternItemProvider.dispose();
        if (logicalRelationshipItemProvider != null) logicalRelationshipItemProvider.dispose();
        if (logicalRelationshipEndItemProvider != null) logicalRelationshipEndItemProvider.dispose();
        if (baseTableItemProvider != null) baseTableItemProvider.dispose();
        if (procedureResultItemProvider != null) procedureResultItemProvider.dispose();
    }

}
