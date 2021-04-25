package com.avilapps.easydocs.data.repository;

import com.avilapps.easydocs.data.repository.impl.DocumentCreateRepository;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.repository.DocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private final DocumentCreateRepository documentCreateRepository;

    public DocumentRepositoryImpl(DocumentCreateRepository documentCreateRepository) {
        this.documentCreateRepository = documentCreateRepository;
    }

    @Override
    public Document createDocument(Document document) {
        return documentCreateRepository.createDocument(document);
    }
}
