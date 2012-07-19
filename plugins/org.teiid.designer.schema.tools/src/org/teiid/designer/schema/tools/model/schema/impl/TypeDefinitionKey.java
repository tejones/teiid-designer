/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.schema.tools.model.schema.impl;

import org.eclipse.xsd.XSDTypeDefinition;
import org.teiid.designer.schema.tools.model.schema.SchemaObjectKey;

/**
 * @since 8.0
 */
public class TypeDefinitionKey implements SchemaObjectKey {

	XSDTypeDefinition type;
	
	public TypeDefinitionKey(XSDTypeDefinition type) {
		this.type = type;
	}
	
	@Override
    public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof TypeDefinitionKey){
			TypeDefinitionKey other = (TypeDefinitionKey)obj;
			if(other.type == this.type){
				result = true;
			}
		}
		return result;
	}

	@Override
    public int hashCode() {
		return type.hashCode();
	}
}
