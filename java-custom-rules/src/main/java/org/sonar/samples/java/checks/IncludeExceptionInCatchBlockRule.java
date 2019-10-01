package org.sonar.samples.java.checks;


import java.util.List;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.CatchTree;
import org.sonar.plugins.java.api.tree.ExpressionStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TryStatementTree;


@Rule(
		key = "IncludeExceptionInCatchBlock",
		name = "Always log a summary and the exception in a catch block",
		description = "Always log both a summary of the error and the exception in a catch block, unless the exception is being re-thrown.",
		tags = {"codesmell"})
public class IncludeExceptionInCatchBlockRule extends IssuableSubscriptionVisitor {


	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Kind.METHOD);
	}
	@Override
	public void visitNode(Tree tree){
	    MethodTree method = (MethodTree) tree;
	    if(method.throwsClauses().size()==0){
	        BlockTree bt = method.block();
	        for(StatementTree st:bt.body()){
	            if(st.is(Kind.TRY_STATEMENT)){
	                TryStatementTree tst = (TryStatementTree) st;
	                for(CatchTree ct:tst.catches()){
	                    for(StatementTree state:ct.block().body()){
	                        ExpressionStatementTree ex = (ExpressionStatementTree)state;
	                        MethodInvocationTree mit = (MethodInvocationTree) ex.expression();
	                        if(mit.arguments().size()!=2){
	                            reportIssue(method.simpleName(), "Include both the summary and the exception itself when logging in catch blocks.");
	                        }
	                    }
	                }
	            }
	        }
	    };
	 }
}
