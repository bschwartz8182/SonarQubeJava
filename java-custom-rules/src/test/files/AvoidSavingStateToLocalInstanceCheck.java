
public class SavingStateExample {

    public SavingStateExample() { 
      FileOutputStream example1 = new FileOutputStream(); // Noncompliant
	  FileWriter example2 = new FileWriter(); // Noncompliant  
      BufferedWriter example3 = new BufferedWriter(); // Noncompliant
    }
    
}
