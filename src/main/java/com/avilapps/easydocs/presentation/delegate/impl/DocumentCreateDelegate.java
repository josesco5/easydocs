package com.avilapps.easydocs.presentation.delegate.impl;

import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.services.DocumentService;
import com.avilapps.easydocs.presentation.model.CreateDocumentRequest;
import com.avilapps.easydocs.presentation.model.CreateDocumentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentCreateDelegate {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentCreateDelegate.class);

    private final DocumentService documentService;

    public DocumentCreateDelegate(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ResponseEntity<CreateDocumentResponse> createDocument(CreateDocumentRequest documentRequest) {
        CreateDocumentResponse documentResponse = new CreateDocumentResponse();
        try {
            Document document = new Document();
            document.setSubject(documentRequest.getSubject());
            document.setFolio(documentRequest.getFolio());
            String url = documentService.createDocument(document, documentRequest.getFile());
            documentResponse.setUrl(url);
        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
    }
}
