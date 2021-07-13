package com.avilapps.easydocs.domain.services;

import com.avilapps.easydocs.common.exceptions.NonImplementedException;
import com.avilapps.easydocs.domain.model.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    default String createDocument(Document document, MultipartFile file) {
        throw new NonImplementedException();
    }
}
