package org.sonar.samples.java.checks;


import java.util.List;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(
		key = "AvoidHardCodedDebug",
		name = "Use a logger utility instead of System.out.println",
		description = "Always use a logging utility so that all messages are persisted and logger levels can be set appropriately.",
		tags = {"bug"})
public class AvoidHardCodedDebugRule extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Kind.METHOD_INVOCATION);
	}
	@Override
	public void visitNode(Tree tree) {
		if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
			MethodInvocationTree mit = (MethodInvocationTree) tree;
	        String mname = mit.symbol().name();
	       if (mname != null) {
	    	   if (mname.equalsIgnoreCase("println") || mname.equalsIgnoreCase("print")) {
	    		   reportIssue(mit.parent(),"Do not use System.out.println directly.  Use a logger utility like log4J instead.");  
	    	   }           
	       }
        }
	}
}
