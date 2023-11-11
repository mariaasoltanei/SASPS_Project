package sasps.documentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sasps.documentmanagement.services.DocumentService;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }
}
