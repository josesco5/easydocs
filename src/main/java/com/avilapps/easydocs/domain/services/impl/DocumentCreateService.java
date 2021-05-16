package com.avilapps.easydocs.domain.services.impl;

import com.avilapps.easydocs.domain.repository.AttachmentRepository;
import com.avilapps.easydocs.domain.repository.DocumentRepository;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.services.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Service
public class DocumentCreateService implements DocumentService {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentCreateService.class);

    private final DocumentRepository documentRepository;
    private final AttachmentRepository attachmentRepository;

    public DocumentCreateService(DocumentRepository documentRepository, AttachmentRepository attachmentRepository) {
        this.documentRepository = documentRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public String createDocument(Document document, MultipartFile file) {
        try {
            Document createdDocument = documentRepository.createDocument(document);
            URL url = attachmentRepository.uploadAttachment(createdDocument, document.getAttachment());
            String path = url.getPath();
            document.setPath(path);
            documentRepository.updateDocument(document);

            return url.toString();
        }
        catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return "";
    }
}
