/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.compare.diagram;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.teiid.designer.core.compare.EObjectMatcherFactory;
import org.teiid.designer.metamodels.diagram.Diagram;
import org.teiid.designer.metamodels.diagram.DiagramContainer;
import org.teiid.designer.metamodels.diagram.DiagramPackage;



/** 
 * DiagramMatcherFactory
 *
 * @since 8.0
 */
public class DiagramMatcherFactory implements EObjectMatcherFactory {

    private final List standardMatchers;

    /** 
     * DiagramMatcherFactory
     * @since 4.2
     */
    public DiagramMatcherFactory() {
        super();
        this.standardMatchers = new LinkedList();
        this.standardMatchers.add( new DiagramRootObjectMatcher() );
        this.standardMatchers.add( new PresentationEntityNameToNameMatcher() );
        this.standardMatchers.add( new PresentationEntityNameToNameIgnoreCaseMatcher() );
    }

    /** 
     * @see org.teiid.designer.core.compare.EObjectMatcherFactory#createEObjectMatchersForRoots()
     * @since 4.2
     */
    @Override
	public List createEObjectMatchersForRoots() {
        // diagram entities are root level objects
        return standardMatchers;
    }

    /** 
     * @see org.teiid.designer.core.compare.EObjectMatcherFactory#createEObjectMatchers(org.eclipse.emf.ecore.EReference)
     * @since 4.2
     */
    @Override
	public List createEObjectMatchers(EReference reference) {
	    // Make sure the reference is in the diagram metamodel ...
	    final EClass containingClass = reference.getEContainingClass();
	    final EPackage metamodel = containingClass.getEPackage();
	    if (!DiagramPackage.eINSTANCE.equals(metamodel)) {
	         //The feature isn't in the transformation or mapping metamodel so return nothing ...
	        return Collections.EMPTY_LIST;
	    }

	    // Create the appropriate matchers ...
        final List results = new LinkedList();
        final int featureId = reference.getFeatureID();
	    if(containingClass.getInstanceClass().equals(DiagramContainer.class)) {
	        if(featureId == DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM) {
	            results.add( new DiagramTargetMatcher() );	            
	        }
	    } else if(containingClass.getInstanceClass().equals(Diagram.class)) {
	        if(featureId == DiagramPackage.DIAGRAM__DIAGRAM_ENTITY ||
	           featureId == DiagramPackage.DIAGRAM__DIAGRAM_LINKS) {
                results.add( new DiagramEntityTargetMatcher() );
	        }
	    }
        return results;
    }
}
