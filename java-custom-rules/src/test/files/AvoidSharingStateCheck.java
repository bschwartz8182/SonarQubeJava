
/**
 * This is an example of the Singleton pattern and it should be avoid for any 
 * application that is a candidate for containerization or moving to a cloud platform.
 */
public class SharingStatePattern { 

	private SharingStatePattern() {  // Noncompliant

    }
	 
	private static SharingStatePattern instance = new SharingStatePattern();

	public static SharingStatePattern getInstance() {
	        return instance;
	}
	    
}
