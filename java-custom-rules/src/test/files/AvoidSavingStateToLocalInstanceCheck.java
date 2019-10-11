
/**
 * Example of creating a file locally
 */

public class WriteStateLocally {
    
	public void setResults() {

		// Do some processing here...
		// Saving state locally
		File file = new File("/home/myAppFolder/firstresults.txt"); // Noncompliant
		
		FileWriter fileWriter = null;
		fileWriter = new FileWriter("/home/myAppFolder/secondresults.txt"); //Noncompliant
		fileWriter.write(65);
		
		fop = new FileOutputStream(file); //Noncompliant

		
		if (!file.exists()) {
			file.createNewFile();
		}
	
	}
	
    
}
