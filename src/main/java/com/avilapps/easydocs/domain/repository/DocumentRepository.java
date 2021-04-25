package com.avilapps.easydocs.domain.repository;

import com.avilapps.easydocs.common.exceptions.NonImplementedException;
import com.avilapps.easydocs.domain.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface DocumentRepository {

    default Document createDocument(Document document) {
        throw new NonImplementedException();
    }

    default URL uploadAttachment(Document document, MultipartFile file) {
        throw new NonImplementedException();
    }
    
    default Document updateDocument(Document document) {
        throw new NonImplementedException();
    }
}
