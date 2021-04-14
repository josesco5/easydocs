package com.avilapps.easydocs.data.repository.impl;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.data.gateway.DocumentGateway;
import com.avilapps.easydocs.data.repository.DocumentRepository;
import com.avilapps.easydocs.domain.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DocumentCreateRepository implements DocumentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentCreateRepository.class);

    private final DocumentGateway documentGateway;

    public DocumentCreateRepository(DocumentGateway documentGateway) {
        this.documentGateway = documentGateway;
    }

    @Override
    public Document createDocument(Document document) {
        try {
            DocumentEntity documentEntity = new DocumentEntity();
            documentEntity.setSubject(document.getSubject());
            documentEntity.setFolio(document.getFolio());
            DocumentEntity savedDocumentEntity = documentGateway.save(documentEntity);

            Document savedDocument = new Document();
            savedDocument.setId(savedDocumentEntity.getId());
            savedDocument.setFolio(savedDocumentEntity.getFolio());
            savedDocument.setSubject(savedDocumentEntity.getSubject());

            return savedDocument;
        }
        catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return null;
    }
}
