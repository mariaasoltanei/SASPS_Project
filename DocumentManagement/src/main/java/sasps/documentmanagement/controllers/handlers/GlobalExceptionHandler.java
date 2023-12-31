package sasps.documentmanagement.controllers.handlers;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sasps.documentmanagement.exceptions.DocumentExistsException;
import sasps.documentmanagement.exceptions.DocumentNotFoundException;
import sasps.documentmanagement.exceptions.PersonNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDocumentNotFoundException(DocumentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found!\n" + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found!\n" + ex.getMessage());
    }

    public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!\n"+ex.getMessage());
    }
    @ExceptionHandler(DocumentExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDocumentExistsException(DocumentExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Document exists!\n" +ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}
