/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.samples.java.checks.AvoidAnnotationRule;
import org.sonar.samples.java.checks.AvoidBrandInMethodNamesRule;
import org.sonar.samples.java.checks.AvoidEnvironmentSpecificLogicRule;
import org.sonar.samples.java.checks.AvoidHardCodedDebugRule;
import org.sonar.samples.java.checks.AvoidMethodDeclarationRule;
import org.sonar.samples.java.checks.AvoidSharingStateRule;
import org.sonar.samples.java.checks.AvoidSingletonPatternRule;
import org.sonar.samples.java.checks.AvoidSuperClassRule;
import org.sonar.samples.java.checks.AvoidUnmodifiableListRule;
import org.sonar.samples.java.checks.IncludeExceptionInCatchBlockRule;
import org.sonar.samples.java.checks.MyCustomSubscriptionRule;
import org.sonar.samples.java.checks.SecurityAnnotationMandatoryRule;
import org.sonar.samples.java.checks.SpringControllerRequestMappingEntityRule;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class<? extends JavaCheck>> getChecks() {
    List<Class<? extends JavaCheck>> checks = new ArrayList<>();
    checks.addAll(getJavaChecks());
    checks.addAll(getJavaTestChecks());
    return Collections.unmodifiableList(checks);
  }

  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return Collections.unmodifiableList(Arrays.asList(
      SpringControllerRequestMappingEntityRule.class,
      AvoidAnnotationRule.class,
      AvoidBrandInMethodNamesRule.class,
      AvoidMethodDeclarationRule.class,
      AvoidSuperClassRule.class,
      AvoidUnmodifiableListRule.class,
      MyCustomSubscriptionRule.class,
      SecurityAnnotationMandatoryRule.class,
      AvoidHardCodedDebugRule.class,
      IncludeExceptionInCatchBlockRule.class,
      AvoidEnvironmentSpecificLogicRule.class,
      AvoidSingletonPatternRule.class,
      AvoidSharingStateRule.class));
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return Collections.emptyList();
  }
}
