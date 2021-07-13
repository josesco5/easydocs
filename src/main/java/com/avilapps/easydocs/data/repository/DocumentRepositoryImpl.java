package com.avilapps.easydocs.data.repository;

import com.avilapps.easydocs.common.exceptions.RepositoryException;
import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.data.gateway.DocumentGateway;
import com.avilapps.easydocs.data.mapper.DocumentDataMapper;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentRepositoryImpl.class);

    private final DocumentGateway documentGateway;
    private final DocumentDataMapper documentDataMapper;

    public DocumentRepositoryImpl(DocumentGateway documentGateway,
                                  DocumentDataMapper documentDataMapper) {
        this.documentGateway = documentGateway;
        this.documentDataMapper = documentDataMapper;
    }

    @Override
    public Document createDocument(Document document) {
        try {
            DocumentEntity documentEntity = documentDataMapper.documentToDocumentEntity(document);
            DocumentEntity savedDocumentEntity = documentGateway.save(documentEntity);

            return documentDataMapper.documentEntityToDocument(savedDocumentEntity);
        }
        catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
            throw  new RepositoryException(exception.getMessage(), exception);
        }
    }

    @Override
    public Document updateDocument(Document document) {
        try {
            DocumentEntity documentEntity = documentDataMapper.documentToDocumentEntity(document);
            DocumentEntity savedDocumentEntity = documentGateway.save(documentEntity);

            return documentDataMapper.documentEntityToDocument(savedDocumentEntity);
        }
        catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
            throw  new RepositoryException(exception.getMessage(), exception);
        }
    }

}
