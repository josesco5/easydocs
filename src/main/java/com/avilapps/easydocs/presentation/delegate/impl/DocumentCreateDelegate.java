package com.avilapps.easydocs.presentation.delegate.impl;

import com.avilapps.easydocs.common.exceptions.DelegateException;
import com.avilapps.easydocs.common.exceptions.GatewayException;
import com.avilapps.easydocs.common.exceptions.RepositoryException;
import com.avilapps.easydocs.common.exceptions.ServiceException;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.services.DocumentService;
import com.avilapps.easydocs.presentation.mapper.DocumentCreateApiMapper;
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
    private final DocumentCreateApiMapper documentCreateApiMapper;

    public DocumentCreateDelegate(DocumentService documentService, DocumentCreateApiMapper documentCreateApiMapper) {
        this.documentService = documentService;
        this.documentCreateApiMapper = documentCreateApiMapper;
    }

    public ResponseEntity<CreateDocumentResponse> createDocument(CreateDocumentRequest documentRequest) {
        CreateDocumentResponse documentResponse = new CreateDocumentResponse();
        try {
            Document document = documentCreateApiMapper.mapApiRequest(documentRequest);
            String url = documentService.createDocument(document, documentRequest.getFile());
            documentResponse.setUrl(url);
        } catch (ServiceException | RepositoryException | GatewayException exception) {
            throw exception;
        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
            throw new DelegateException(exception.getMessage(), exception);
        }
        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
    }
}
