/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.wsdl.http.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.teiid.designer.metamodels.wsdl.http.HttpAddress;
import org.teiid.designer.metamodels.wsdl.http.HttpBinding;
import org.teiid.designer.metamodels.wsdl.http.HttpFactory;
import org.teiid.designer.metamodels.wsdl.http.HttpOperation;
import org.teiid.designer.metamodels.wsdl.http.HttpPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 *
 * @since 8.0
 */
public class HttpFactoryImpl extends EFactoryImpl implements HttpFactory {

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public HttpFactoryImpl() {
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
            case HttpPackage.HTTP_ADDRESS:
                return createHttpAddress();
            case HttpPackage.HTTP_BINDING:
                return createHttpBinding();
            case HttpPackage.HTTP_OPERATION:
                return createHttpOperation();
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
	public HttpAddress createHttpAddress() {
        HttpAddressImpl httpAddress = new HttpAddressImpl();
        return httpAddress;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public HttpBinding createHttpBinding() {
        HttpBindingImpl httpBinding = new HttpBindingImpl();
        return httpBinding;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public HttpOperation createHttpOperation() {
        HttpOperationImpl httpOperation = new HttpOperationImpl();
        return httpOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public HttpPackage getHttpPackage() {
        return (HttpPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static HttpPackage getPackage() { // NO_UCD
        return HttpPackage.eINSTANCE;
    }

} // HttpFactoryImpl
