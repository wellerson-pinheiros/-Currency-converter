package exceptions;

public class ApiConnectionException extends RuntimeException {
	 public ApiConnectionException(String msg, Exception e) { 
		 super(msg);
		 }
}
