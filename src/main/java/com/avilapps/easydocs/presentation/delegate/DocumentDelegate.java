package com.avilapps.easydocs.presentation.delegate;

import com.avilapps.easydocs.presentation.delegate.impl.DocumentCreateDelegate;
import com.avilapps.easydocs.presentation.model.CreateDocumentRequest;
import com.avilapps.easydocs.presentation.model.CreateDocumentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentDelegate {
    DocumentCreateDelegate documentCreateDelegate;

    public DocumentDelegate(DocumentCreateDelegate documentCreateDelegate) {
        this.documentCreateDelegate = documentCreateDelegate;
    }

    public ResponseEntity<CreateDocumentResponse> createDocument(CreateDocumentRequest documentRequest) {
        return documentCreateDelegate.createDocument(documentRequest);
    }
}
