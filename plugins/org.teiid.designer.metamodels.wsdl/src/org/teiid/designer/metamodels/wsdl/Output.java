/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.wsdl;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Output</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.wsdl.Output#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getOutput()
 * @model
 * @generated
 *
 * @since 8.0
 */
public interface Output extends ParamType {

    /**
     * Returns the value of the '<em><b>Operation</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link org.teiid.designer.metamodels.wsdl.Operation#getOutput <em>Output</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' container reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation</em>' container reference.
     * @see #setOperation(Operation)
     * @see org.teiid.designer.metamodels.wsdl.WsdlPackage#getOutput_Operation()
     * @see org.teiid.designer.metamodels.wsdl.Operation#getOutput
     * @model opposite="output" required="true"
     * @generated
     */
    Operation getOperation();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.wsdl.Output#getOperation <em>Operation</em>}' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Operation</em>' container reference.
     * @see #getOperation()
     * @generated
     */
    void setOperation( Operation value );

} // Output
