/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.transformation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Operation Node</b></em>'. <!-- end-user-doc -->
 * 
 * @see org.teiid.designer.metamodels.transformation.TransformationPackage#getOperationNode()
 * @model
 * @generated
 *
 * @since 8.0
 */
public interface OperationNode extends AbstractOperationNode {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model parameters=""
     * @generated
     */
    int getMinInputs(); // NO_UCD

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model parameters=""
     * @generated
     */
    int getMaxInputs(); // NO_UCD

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model parameters=""
     * @generated
     */
    int getMinOutputs(); // NO_UCD

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model parameters=""
     * @generated
     */
    int getMaxOutputs(); // NO_UCD

} // OperationNode
