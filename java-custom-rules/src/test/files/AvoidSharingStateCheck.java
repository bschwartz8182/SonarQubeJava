import javax.ejb.Stateful; 

/**
 * This is a classic pojo stateful session bean
 */

@Stateful  // Noncompliant
public class Counter {

    private int count = 0;

    
}
