package sasps.documentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.exceptions.DocumentNotFoundException;
import sasps.documentmanagement.services.DocumentService;

import java.util.UUID;

@RestController
// CAPI
@RequestMapping("/v1/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> findById(@PathVariable String id){
        DocumentDTO documentDTO = documentService.findByID(UUID.fromString(id));
        return new ResponseEntity<>(documentDTO, HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createDocument(@RequestBody DocumentDTO documentDTO) throws DocumentNotFoundException {
        UUID createdDocumentId = documentService.createDocument(documentDTO);
        return new ResponseEntity<>(createdDocumentId, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable String id) throws ResourceNotFoundException {
        UUID deletedDocumentID = documentService.deleteDocument(UUID.fromString(id));
        return new ResponseEntity<>(deletedDocumentID, HttpStatus.GONE);
    }
}
