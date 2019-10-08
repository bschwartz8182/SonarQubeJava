# SonarQube Custom Java Rules
### Customized from SonarQube Custom Java Rules Project: ###
https://github.com/bschwartz8182/SonarQubeJava/tree/develop/java-custom-rules


## Steps to Write Custom Java Rules: ##

1. Setup Maven project in Eclipse to build SonarQube custom rule plugin.  Clone this project and import it into Eclipse as a Maven project. Don’t start with a new project.  Modify and use this one instead.  Refer to this video for details: https://www.youtube.com/watch?v=PT-HPH4C-_k

2. Write the custom rules in the sample project.  This will be one plug-in that holds all automation to validate containerization readiness and compliance: https://www.youtube.com/watch?v=HwQtWJ66Y7c

3. Deploy the custom rule plugin onto the SonarQube server.  This is copying the customrule jar from the maven /target folder to the /extensions/plugins folder of your SonarQube instance  (NOTE: Don't forget to restart the sonarqube server after installing the custom plug-in) https://www.youtube.com/watch?v=YxrbpwqRC8Y

4. Extend the default Java Sonar Way quality gate plug in to use the custom rule.  Once this has been extended, you will need to activate each custom rule that you created (NOTE: This requires sonar-administrative privileges to do so):  https://www.youtube.com/watch?v=kzCCZ3RucsQ

5. Either create a new or update the existing Quality Gate to use the new Quality Profile: https://youtu.be/EvOUyh5kpk0

6. Update the existing SonarQube projects to use the new QualityGate

### Other References: ###

* https://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101
