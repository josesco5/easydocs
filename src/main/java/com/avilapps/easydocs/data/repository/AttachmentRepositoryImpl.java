package com.avilapps.easydocs.data.repository;

import com.avilapps.easydocs.data.gateway.AttachmentGateway;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.repository.AttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Component
public class AttachmentRepositoryImpl implements AttachmentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentRepositoryImpl.class);

    private final AttachmentGateway attachmentGateway;

    public AttachmentRepositoryImpl(AttachmentGateway attachmentGateway) {
        this.attachmentGateway = attachmentGateway;
    }

    @Override
    public URL uploadAttachment(Document document, MultipartFile file) {
        try {
            String url = attachmentGateway.uploadAttachment(document, file);
            return new URL(url);
        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return null;
    }
}
