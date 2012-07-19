/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.relational;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Procedure Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.teiid.designer.metamodels.relational.ProcedureResult#getProcedure <em>Procedure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.teiid.designer.metamodels.relational.RelationalPackage#getProcedureResult()
 * @model
 * @generated
 *
 * @since 8.0
 */
public interface ProcedureResult extends ColumnSet{
    /**
     * Returns the value of the '<em><b>Procedure</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.teiid.designer.metamodels.relational.Procedure#getResult <em>Result</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Procedure</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Procedure</em>' container reference.
     * @see #setProcedure(Procedure)
     * @see org.teiid.designer.metamodels.relational.RelationalPackage#getProcedureResult_Procedure()
     * @see org.teiid.designer.metamodels.relational.Procedure#getResult
     * @model opposite="result"
     * @generated
     */
    Procedure getProcedure();

    /**
     * Sets the value of the '{@link org.teiid.designer.metamodels.relational.ProcedureResult#getProcedure <em>Procedure</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Procedure</em>' container reference.
     * @see #getProcedure()
     * @generated
     */
    void setProcedure(Procedure value);

} // ProcedureResult
