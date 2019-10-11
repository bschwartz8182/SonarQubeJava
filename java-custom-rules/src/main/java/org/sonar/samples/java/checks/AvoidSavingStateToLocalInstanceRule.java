package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.google.common.collect.Lists;

@Rule(
		key = "AvoidSavingStateToLocalInstance",
		name = "Avoid saving state directly to a local instance",
		description = 
		 "Avoid saving session or other state information to a local instance.  Application nodes " +
		 "Running in a containerized environment are ephemeral and can at anytime without warning. " +
		 "Any state information should be written to an external store, such as a database or an " +
		 "in memory cache platform that allows for persisting state (e.g. Redis)",
		priority = Priority.MAJOR,
		tags = {"codesmell"})

public class AvoidSavingStateToLocalInstanceRule extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return Lists.newArrayList(Kind.METHOD_INVOCATION);
	}

	@Override
	public void visitNode(Tree tree) {
		
		if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
			MethodInvocationTree mit = (MethodInvocationTree) tree;
			String mname = mit.symbol().name();
			System.out.println("Invoked Method  "+ mname );
	    	reportIssue(tree, "Avoid sharing state information with other processes");
        }
		
		/*
		ClassTree classTree = (ClassTree) tree;
				
  		List<AnnotationTree> annotations = classTree.modifiers().annotations();

	    for (AnnotationTree annotationTree : annotations) {
	        TypeTree annotationType = annotationTree.annotationType();
	          String annotationName = ((IdentifierTree) annotationType).name();
	          if (annotationName.equalsIgnoreCase("Stateful")) {
	        	 reportIssue(tree, "Avoid sharing state information with other processes");
	          }
	    }
	    */
	    super.visitNode(tree);

	}
}