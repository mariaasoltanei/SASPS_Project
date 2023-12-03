package sasps.documentmanagement.exceptions;

public class DocumentExistsException extends RuntimeException {
    public DocumentExistsException(String message){
        super(message);
    }
}
