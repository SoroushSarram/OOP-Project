//FileInvalidException class
public class FileInvalidException extends Exception 
{
	    
	    //Creating a constructor with an error message
		public FileInvalidException() 
		{
			super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
			// TODO Auto-generated constructor stub
		}
		
		public String getMessage()
		{
	        return super.getMessage();//return the message of happenned error to string type
	    }

	}