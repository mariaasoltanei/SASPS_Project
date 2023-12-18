package sasps.documentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sasps.documentmanagement.controllers.interfaces.DocumentService;
import sasps.documentmanagement.dtos.DocumentDTO;
import sasps.documentmanagement.proxy.DocumentProxy;

import java.util.UUID;

@RestController
@RequestMapping("/v1/documents")
@CrossOrigin
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentProxy proxy){
        this.documentService = proxy;
    }

    @GetMapping("/{id}/{token}")
    public ResponseEntity<DocumentDTO> findById(@PathVariable String id, @PathVariable String token) throws Exception {
        DocumentDTO documentDTO = documentService.proxyFindByID(UUID.fromString(id), token);
        return new ResponseEntity<>(documentDTO, HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createDocument(@RequestBody DocumentDTO documentDTO) throws Exception {
        UUID createdDocumentID = documentService.proxyCreateDocument(documentDTO);
        return new ResponseEntity<>(createdDocumentID, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable UUID id, @RequestBody String token) throws Exception {
        UUID deletedDocumentID = documentService.proxyDeleteDocument(id,token);
        return new ResponseEntity<>(deletedDocumentID, HttpStatus.GONE);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDocument(@PathVariable UUID id, @RequestBody DocumentDTO documentDTO, @RequestBody String token) throws Exception {
        UUID updatedDocumentID = documentService.proxyUpdateDocument(id,documentDTO);
        return new ResponseEntity<>(updatedDocumentID, HttpStatus.ACCEPTED);
    }
}
