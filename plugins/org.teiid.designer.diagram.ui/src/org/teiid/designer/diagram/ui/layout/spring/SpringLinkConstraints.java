/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.layout.spring;

/**
 * @since 8.0
 */
public class SpringLinkConstraints {
    private double _weight;

    public SpringLinkConstraints() {
        this(0.0);
    }

    public SpringLinkConstraints(double d) {
        _weight = d;
    }

    public double getWeight() {
        return _weight;
    }

    public void setWeight(double d) {
        _weight = d;
    }
}
