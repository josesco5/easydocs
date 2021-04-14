package com.avilapps.easydocs.data.repository;

import com.avilapps.easydocs.common.exceptions.NonImplementedException;
import com.avilapps.easydocs.domain.model.Document;

public interface DocumentRepository {

    default Document createDocument(Document document) {
        throw new NonImplementedException();
    }
}
