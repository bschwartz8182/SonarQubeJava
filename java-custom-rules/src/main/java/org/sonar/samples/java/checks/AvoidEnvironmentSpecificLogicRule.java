package org.sonar.samples.java.checks;


import java.util.ArrayList;
import java.util.List;

import org.sonar.api.internal.google.common.collect.Lists;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(
		key = "AvoidEnvironmentSpecificLogic",
		name = "Avoid having code that is environment-specific",
		description = "Avoid having code in place that is specific to an environment.  Environment-specifics should be maintained in environment variables "
				+ "    and referenced.",
		priority = Priority.MAJOR,
		tags = {"bug"})
public class AvoidEnvironmentSpecificLogicRule extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return Lists.newArrayList(Kind.STRING_LITERAL);
	}

	@Override
	public void visitNode(Tree stringLiteral) {
		
		List<String> environmentNames = new ArrayList<String>();
		environmentNames.add("DEV");
		environmentNames.add("DEVELOPMENT");
		environmentNames.add("EVAL");
		environmentNames.add("EVALUATION");
		environmentNames.add("INT");
		environmentNames.add("INTEGRATION");
		environmentNames.add("PRE-PROD");
		environmentNames.add("PRE-PRODUCTION");
		environmentNames.add("PROD");
		environmentNames.add("PRODUCTION");
		environmentNames.add("QA");
		environmentNames.add("SANDBOX");
		environmentNames.add("SBX");
		environmentNames.add("TEST");
		//test
		
		
		    	
		String literalValue = removeQuotes(((LiteralTree) stringLiteral).value());
		
		for (String str : environmentNames) {
			if ( str.equalsIgnoreCase(literalValue)) {
				reportIssue(stringLiteral, "Avoid having code that is environment-specific");
			}
		}
		super.visitNode(stringLiteral);
	}

	private String removeQuotes(String value) {
		return value.replaceAll("^\"|\"$", "");
	}
}
