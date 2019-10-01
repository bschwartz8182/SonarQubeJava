public class EnvironmentLogicCheck {

    public void showEnvironmentLogic(){   
        String environmment = System.getProperty("Environment");
    	
        if (environment.equalsIgnoreCase("PRODUCTION")) {  // Noncompliant
    		//Perform task specific to production
    	}
    	else {
    		//Perform task for all other environments
    	}
        return;
        
        if (environment.equals("PRODUCTION")) {  // Noncompliant
    		//Perform task specific to production
    	}
    	else {
    		//Perform task for all other environments
    	}
        return;
    }
    
    public void showWithoutEnvrironmentLogic(String nonsense){
        
    	// Perform the same logic for all environments  
    	
    	return;
    }
}