/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.diagram.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.teiid.designer.metamodels.diagram.Diagram;
import org.teiid.designer.metamodels.diagram.DiagramContainer;
import org.teiid.designer.metamodels.diagram.DiagramEntity;
import org.teiid.designer.metamodels.diagram.DiagramFactory;
import org.teiid.designer.metamodels.diagram.DiagramLink;
import org.teiid.designer.metamodels.diagram.DiagramLinkType;
import org.teiid.designer.metamodels.diagram.DiagramPackage;
import org.teiid.designer.metamodels.diagram.DiagramPosition;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 *
 * @since 8.0
 */
public class DiagramFactoryImpl extends EFactoryImpl implements DiagramFactory {

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create( EClass eClass ) {
        switch (eClass.getClassifierID()) {
            case DiagramPackage.DIAGRAM_ENTITY:
                return createDiagramEntity();
            case DiagramPackage.DIAGRAM:
                return createDiagram();
            case DiagramPackage.DIAGRAM_CONTAINER:
                return createDiagramContainer();
            case DiagramPackage.DIAGRAM_LINK:
                return createDiagramLink();
            case DiagramPackage.DIAGRAM_POSITION:
                return createDiagramPosition();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString( EDataType eDataType,
                                    String initialValue ) {
        switch (eDataType.getClassifierID()) {
            case DiagramPackage.DIAGRAM_LINK_TYPE: {
                DiagramLinkType result = DiagramLinkType.get(initialValue);
                if (result == null) throw new IllegalArgumentException(
                                                                       "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                return result;
            }
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString( EDataType eDataType,
                                   Object instanceValue ) {
        switch (eDataType.getClassifierID()) {
            case DiagramPackage.DIAGRAM_LINK_TYPE:
                return instanceValue == null ? null : instanceValue.toString();
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public DiagramEntity createDiagramEntity() {
        DiagramEntityImpl diagramEntity = new DiagramEntityImpl();
        return diagramEntity;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public Diagram createDiagram() {
        DiagramImpl diagram = new DiagramImpl();
        return diagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public DiagramContainer createDiagramContainer() {
        DiagramContainerImpl diagramContainer = new DiagramContainerImpl();
        return diagramContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public DiagramLink createDiagramLink() {
        DiagramLinkImpl diagramLink = new DiagramLinkImpl();
        return diagramLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public DiagramPosition createDiagramPosition() {
        DiagramPositionImpl diagramPosition = new DiagramPositionImpl();
        return diagramPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public DiagramPackage getDiagramPackage() {
        return (DiagramPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DiagramPackage getPackage() { // NO_UCD
        return DiagramPackage.eINSTANCE;
    }

} // DiagramFactoryImpl
