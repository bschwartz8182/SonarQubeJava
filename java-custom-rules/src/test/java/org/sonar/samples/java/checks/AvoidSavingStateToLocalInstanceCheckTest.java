package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidSavingStateToLocalInstanceCheckTest {
	
 @Test
 public void test() {
	 JavaCheckVerifier.verify("src/test/files/AvoidSavingStateToLocalInstanceCheck.java", new AvoidSavingStateToLocalInstanceRule());
 }

}
