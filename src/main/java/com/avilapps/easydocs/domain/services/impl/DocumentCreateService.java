package com.avilapps.easydocs.domain.services.impl;

import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.services.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentCreateService implements DocumentService {

    @Override
    public String createDocument(Document document, MultipartFile file) {
        return "";
    }
}
