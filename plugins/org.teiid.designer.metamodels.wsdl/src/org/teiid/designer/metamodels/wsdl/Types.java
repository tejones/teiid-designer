/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.wsdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Types</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.wsdl.Types#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.teiid.designer.metamodels.wsdl.Types#getSchemas <em>Schemas</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getTypes()
 * @model
 * @generated
 *
 * @since 8.0
 */
public interface Types extends ExtensibleDocumented {

    /**
     * Returns the value of the '<em><b>Definitions</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.Definitions#getTypes <em>Types</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Definitions</em>' container reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Definitions</em>' container reference.
     * @see #setDefinitions(Definitions)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getTypes_Definitions()
     * @see org.teiid.designer.metamodels.wsdl.Definitions#getTypes
     * @model opposite="types" required="true"
     * @generated
     */
    Definitions getDefinitions();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.Types#getDefinitions <em>Definitions</em>}' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Definitions</em>' container reference.
     * @see #getDefinitions()
     * @generated
     */
    void setDefinitions( Definitions value );

    /**
     * Returns the value of the '<em><b>Schemas</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.xsd.XSDSchema}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Schemas</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Schemas</em>' containment reference list.
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getTypes_Schemas()
     * @model type="org.eclipse.xsd.XSDSchema" containment="true"
     * @generated
     */
    EList getSchemas();

} // Types
