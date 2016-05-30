package by.epam.atl.google.exception;

public class PageException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public PageException(String message){
		super(message);
	}
	
	public PageException(String message, Exception e){
		super(message, e);
	}
	
	public PageException(Exception e){
		super(e);
	}

}
