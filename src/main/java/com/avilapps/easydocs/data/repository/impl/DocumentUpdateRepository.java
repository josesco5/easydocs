package com.avilapps.easydocs.data.repository.impl;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.data.gateway.DocumentGateway;
import com.avilapps.easydocs.data.mapper.DocumentUpdateDataMapper;
import com.avilapps.easydocs.domain.model.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DocumentUpdateRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentCreateRepository.class);

    private final DocumentGateway documentGateway;
    private final DocumentUpdateDataMapper documentUpdateDataMapper;

    public DocumentUpdateRepository(DocumentGateway documentGateway, DocumentUpdateDataMapper documentUpdateDataMapper) {
        this.documentGateway = documentGateway;
        this.documentUpdateDataMapper = documentUpdateDataMapper;
    }

    public Document updateDocument(Document document) {
        try {
            DocumentEntity documentEntity = documentUpdateDataMapper.mapDataRequest(document);
            DocumentEntity savedDocumentEntity = documentGateway.save(documentEntity);

            return documentUpdateDataMapper.mapDataResponse(savedDocumentEntity);
        }
        catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return null;
    }
}
