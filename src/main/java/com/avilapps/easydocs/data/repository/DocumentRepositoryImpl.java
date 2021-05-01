package com.avilapps.easydocs.data.repository;

import com.avilapps.easydocs.data.repository.impl.DocumentCreateRepository;
import com.avilapps.easydocs.data.repository.impl.DocumentUpdateRepository;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.repository.DocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private final DocumentCreateRepository documentCreateRepository;
    private final DocumentUpdateRepository documentUpdateRepository;

    public DocumentRepositoryImpl(DocumentCreateRepository documentCreateRepository, DocumentUpdateRepository documentUpdateRepository) {
        this.documentCreateRepository = documentCreateRepository;
        this.documentUpdateRepository = documentUpdateRepository;
    }

    @Override
    public Document createDocument(Document document) {
        return documentCreateRepository.createDocument(document);
    }

    @Override
    public Document updateDocument(Document document) {
        return documentUpdateRepository.updateDocument(document);
    }

}
