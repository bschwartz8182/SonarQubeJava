package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.api.internal.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.expression.IdentifierTreeImpl;
import org.sonar.java.model.expression.NewClassTreeImpl;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
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
		key = "AvoidSharingState",
		name = "Avoid sharing state information with other processes",
		description = 
		 "It is bad practice to assume that anything cached in memory or on disk will be available " +
		 "on a future request or job – with many processes of each type running, chances are high that a future " +
		 "request will be served by a different process.  This is especially critical in containerized and/or cloud applications. " +
		 "Any state that needs to be saved should be persisted to an external store, such as a database.",
		priority = Priority.MAJOR,
		tags = {"codesmell"})

public class AvoidSavingStateToLocalInstanceRule extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return Lists.newArrayList(Kind.NEW_CLASS);
	}

	@Override
	public void visitNode(Tree tree) {
		/*
		if (tree.is(Tree.Kind.CLASS)) {
			ClassTree classTree = (ClassTree) tree;
				
			List<AnnotationTree> annotations = classTree.modifiers().annotations();

			for (AnnotationTree annotationTree : annotations) {
				TypeTree annotationType = annotationTree.annotationType();
				String annotationName = ((IdentifierTree) annotationType).name();
				if (annotationName.equalsIgnoreCase("Stateful")) {
	        	 reportIssue(tree, "Avoid sharing state information with other processes");
				}
			}
		}
		*/
		//AssignmentExpressionTree assignment = (AssignmentExpressionTree) tree;
		
			NewClassTreeImpl expression = (NewClassTreeImpl) tree;
			//IdentifierTreeImpl identifier = (IdentifierTreeImpl) expression.identifier();
			System.out.println("name: " + expression.getConstructorIdentifier().name());
	        if (expression.getConstructorIdentifier().name().startsWith("File")) {
	        	 reportIssue(tree, "Avoid sharing state information with other processes");
			}

		super.visitNode(tree);

	}
}