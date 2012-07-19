/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Input Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.transformation.InputSet#getMappingClass <em>Mapping Class</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.transformation.InputSet#getInputParameters <em>Input Parameters</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.teiid.designer.metamodels.transformation.TransformationPackage#getInputSet()
 * @model
 * @generated
 *
 * @since 8.0
 */
public interface InputSet extends EObject {

    /**
     * Returns the value of the '<em><b>Mapping Class</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.transformation.MappingClass#getInputSet <em>Input Set</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping Class</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Mapping Class</em>' container reference.
     * @see #setMappingClass(MappingClass)
     * @see org.teiid.designer.metamodels.transformation.TransformationPackage#getInputSet_MappingClass()
     * @see org.teiid.designer.metamodels.transformation.MappingClass#getInputSet
     * @model opposite="inputSet" required="true"
     * @generated
     */
    MappingClass getMappingClass();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.transformation.InputSet#getMappingClass <em>Mapping Class</em>}'
     * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Mapping Class</em>' container reference.
     * @see #getMappingClass()
     * @generated
     */
    void setMappingClass( MappingClass value );

    /**
     * Returns the value of the '<em><b>Input Parameters</b></em>' containment reference list. The list contents are of type
     * {@link org.teiid.designer.metamodels.transformation.InputParameter}. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.transformation.InputParameter#getInputSet <em>Input Set</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Parameters</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Input Parameters</em>' containment reference list.
     * @see org.teiid.designer.metamodels.transformation.TransformationPackage#getInputSet_InputParameters()
     * @see org.teiid.designer.metamodels.transformation.InputParameter#getInputSet
     * @model type="org.teiid.designer.metamodels.transformation.InputParameter" opposite="inputSet" containment="true"
     * @generated
     */
    EList getInputParameters();

} // InputSet
