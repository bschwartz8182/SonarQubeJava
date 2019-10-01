public class logExceptionCheck {
    private static Logger logger = Logger.getLogger(logExceptionCheck.class);
    public void loggingWithID(String nonsense) throws myException{
        logger.error("errorID:20160801 this is an error");
        return;
    }

    public void loggingWithOutException(){   // Noncompliant
        logger.error("only the logger");
        try{
            logger.error("this is an error");
        }catch(NullPointerException e){
            logger.error("without an exception");
        }
        return;
    }
    
    public void loggingWithoutID(String nonsens){
        try{
            logger.error("this is an error");
        }catch(NullPointerException e){
            logger.error("with an exception provided",e);
        }
        return;
    }
}