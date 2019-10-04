
/**
 * This is an example of the Singleton pattern and it should be avoid for any 
 * application that is a candidate for containerization or moving to a cloud platform.
 */
public class SingletonPattern { 

	private SingletonPattern() {  // Noncompliant

    }
	 
	private static SingletonPattern instance = new SingletonPattern();

	public static SingletonPattern getInstance() {
	        return instance;
	}
	    
}
