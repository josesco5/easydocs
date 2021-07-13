package com.avilapps.easydocs.presentation.controller;

import com.avilapps.easydocs.presentation.delegate.DocumentDelegate;
import com.avilapps.easydocs.presentation.model.CreateDocumentRequest;
import com.avilapps.easydocs.presentation.model.CreateDocumentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentDelegate documentDelegate;

    public DocumentController(DocumentDelegate documentDelegate) {
        this.documentDelegate = documentDelegate;
    }

    @PostMapping
    public ResponseEntity<CreateDocumentResponse> createDocument(@ModelAttribute CreateDocumentRequest documentRequest) {
        return documentDelegate.createDocument(documentRequest);
    }
}
