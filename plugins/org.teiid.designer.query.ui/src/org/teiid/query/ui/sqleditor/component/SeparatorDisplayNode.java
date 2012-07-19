/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.query.ui.sqleditor.component;

/**
 * The <code>SeparatorDisplayNode</code> class is used to represent a text separator.
 *
 * @since 8.0
 */
public class SeparatorDisplayNode extends TextDisplayNode {

    // ===========================================================================================================================
    // Constructors

    /**
     * @see TextDisplayNode#TextDisplayNode(DisplayNode, String)
     */
    public SeparatorDisplayNode(DisplayNode parentNode,
                                String text) {
        super(parentNode, text);
    }
}
