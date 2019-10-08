package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.ModifierKeywordTree;
import org.sonar.plugins.java.api.tree.Tree;
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

public class AvoidSharingStateRule extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return Lists.newArrayList(Kind.CONSTRUCTOR);
	}

	@Override
	public void visitNode(Tree tree) {
		ClassTree classTree = (ClassTree) tree.parent();
		String className = classTree.simpleName().toString();
		MethodTree methodTree = (MethodTree) tree;
		String constructorName = methodTree.simpleName().toString();
		
		if (constructorName.equalsIgnoreCase(className)) {
			List<ModifierKeywordTree> modifiers = methodTree.modifiers().modifiers();
			for (ModifierKeywordTree modifier : modifiers) {
				if (modifier.modifier() == Modifier.PRIVATE) {
					reportIssue(tree, "Avoid the use of the Singleton pattern");
				}
			}
		}
			
		super.visitNode(tree);
	}
}