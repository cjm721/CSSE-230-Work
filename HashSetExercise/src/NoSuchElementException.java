/** package weiss.util; */
public class NoSuchElementException extends RuntimeException
{
    /**
     * Constructs a NoSuchElementException with no detail message.
     */
    public NoSuchElementException( )
    {
    	// empty
    }
    
    /**
     * Constructs a NoSuchElementException with a detail message.
     * @param msg the detailed message pertaining to this exception.
     */
    public NoSuchElementException( String msg )
    {
        super( msg );
    }
}