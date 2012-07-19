/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.teiid.designer.core.workspace.WorkspaceResourceFinderUtil;

/**
 * WorkspaceUriHelper
 *
 * @since 8.0
 */
public class WorkspaceUriPathConverter extends BasicUriPathConverter {

    // ==================================================================================
    // C O N S T R U C T O R S
    // ==================================================================================

    /**
     * Construct an instance of WorkspaceUriHelper.
     */
    public WorkspaceUriPathConverter() {
        super();
    }

    // ==================================================================================
    // O V E R R I D D E N M E T H O D S
    // ==================================================================================

    /**
     * @see org.teiid.designer.core.util.UriPathConverter#makeAbsolute(org.eclipse.emf.common.util.URI,
     *      org.eclipse.emf.common.util.URI)
     */
    @Override
    public URI makeAbsolute( final URI relativeURI,
                             final URI baseURI ) {
        URI resourceURI = relativeURI;
        final IResource iResource = WorkspaceResourceFinderUtil.findIResource(relativeURI);
        if (iResource != null) {
            resourceURI = URI.createFileURI(iResource.getLocation().toOSString());
        }
        return resourceURI;
    }

}
