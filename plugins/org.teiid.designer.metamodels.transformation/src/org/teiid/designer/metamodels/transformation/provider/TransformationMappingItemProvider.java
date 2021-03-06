/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.transformation.provider;

import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.provider.MappingItemProvider;
import org.teiid.designer.metamodels.transformation.TransformationFactory;

/**
 * This is the item provider adapter for a {@link org.teiid.designer.metamodels.transformation.TransformationMapping} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 *
 * @since 8.0
 */
public class TransformationMappingItemProvider extends MappingItemProvider {

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TransformationMappingItemProvider( AdapterFactory adapterFactory ) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List getPropertyDescriptors( Object object ) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

        }
        return itemPropertyDescriptors;
    }

    /**
     * This returns TransformationMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage( Object object ) {
        return getResourceLocator().getImage("full/obj16/TransformationMapping"); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText( Object object ) {
        return getString("_UI_TransformationMapping_type"); //$NON-NLS-1$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating a viewer
     * notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged( Notification notification ) {
        updateChildren(notification);
        super.notifyChanged(notification);
    }

    /**
     * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s describing all of the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors( Collection newChildDescriptors,
                                               Object object ) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Helper(),
                                                     TransformationFactory.eINSTANCE.createSqlTransformation()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Helper(),
                                                     TransformationFactory.eINSTANCE.createXQueryTransformation()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Nested(),
                                                     TransformationFactory.eINSTANCE.createTransformationMapping()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Nested(),
                                                     TransformationFactory.eINSTANCE.createSqlTransformationMappingRoot()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Nested(),
                                                     TransformationFactory.eINSTANCE.createFragmentMappingRoot()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Nested(),
                                                     TransformationFactory.eINSTANCE.createTreeMappingRoot()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Nested(),
                                                     TransformationFactory.eINSTANCE.createDataFlowMappingRoot()));

        newChildDescriptors.add(createChildParameter(MappingPackage.eINSTANCE.getMapping_Nested(),
                                                     TransformationFactory.eINSTANCE.createXQueryTransformationMappingRoot()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return TransformationEditPlugin.INSTANCE;
    }

}
