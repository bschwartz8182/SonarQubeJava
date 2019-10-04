package org.sonar.samples.java.checks;


import java.lang.reflect.Array;
import java.util.List;

import org.sonar.api.internal.google.common.collect.Lists;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.ModifiersUtils;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.ModifierKeywordTree;
import org.sonar.plugins.java.api.tree.ModifierTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TypeParameters;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(
		key = "AvoidSingletonPattern",
		name = "Avoid the use of the Singleton pattern",
		description = 
		 "Singleton patterns introduce global state to the application, which makes it difficult " +
		 "to run unit tests and violates a class instance's single responsibility principle.  This " +
		 "can cause many issues with containerized applications.",
		priority = Priority.MAJOR,
		tags = {"code_smell"})
public class AvoidSingletonPatternRule extends IssuableSubscriptionVisitor {

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
			
		if (constructorName.equalsIgnoreCase(className) &&
			    ModifiersUtils.hasModifier(methodTree.modifiers(), Modifier.PRIVATE)) {
			  reportIssue(tree, "Avoid the use of the Singleton pattern");
		}
			
		super.visitNode(tree);
	}
}
