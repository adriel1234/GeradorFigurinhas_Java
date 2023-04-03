package stickers;

public class ClienteHttpExcepetion extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ClienteHttpExcepetion(String message) {
		super(message);
	}

}