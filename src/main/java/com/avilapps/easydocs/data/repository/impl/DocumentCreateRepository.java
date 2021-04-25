package com.avilapps.easydocs.data.repository.impl;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.data.gateway.DocumentGateway;
import com.avilapps.easydocs.data.mapper.DocumentCreateDataMapper;
import com.avilapps.easydocs.data.repository.DocumentRepository;
import com.avilapps.easydocs.domain.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DocumentCreateRepository implements DocumentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentCreateRepository.class);

    private final DocumentGateway documentGateway;
    private final DocumentCreateDataMapper documentCreateDataMapper;

    public DocumentCreateRepository(DocumentGateway documentGateway, DocumentCreateDataMapper documentCreateDataMapper) {
        this.documentGateway = documentGateway;
        this.documentCreateDataMapper = documentCreateDataMapper;
    }

    @Override
    public Document createDocument(Document document) {
        try {
            DocumentEntity documentEntity = documentCreateDataMapper.mapDataRequest(document);
            DocumentEntity savedDocumentEntity = documentGateway.save(documentEntity);

            return documentCreateDataMapper.mapDataResponse(savedDocumentEntity);
        }
        catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return null;
    }
}
