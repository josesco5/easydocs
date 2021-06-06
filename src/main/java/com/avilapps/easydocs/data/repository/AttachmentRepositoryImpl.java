package com.avilapps.easydocs.data.repository;

import com.avilapps.easydocs.common.exceptions.GatewayException;
import com.avilapps.easydocs.common.exceptions.RepositoryException;
import com.avilapps.easydocs.data.gateway.AttachmentGateway;
import com.avilapps.easydocs.data.mapper.AttachmentUploadDataMapper;
import com.avilapps.easydocs.data.model.AttachmentUploadRequest;
import com.avilapps.easydocs.data.model.AttachmentUploadResponse;
import com.avilapps.easydocs.domain.model.Attachment;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.domain.repository.AttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class AttachmentRepositoryImpl implements AttachmentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentRepositoryImpl.class);

    private final AttachmentGateway attachmentGateway;
    private final AttachmentUploadDataMapper attachmentUploadDataMapper;

    public AttachmentRepositoryImpl(AttachmentGateway attachmentGateway, AttachmentUploadDataMapper attachmentUploadDataMapper) {
        this.attachmentGateway = attachmentGateway;
        this.attachmentUploadDataMapper = attachmentUploadDataMapper;
    }

    @Override
    public URL uploadAttachment(Document document, Attachment attachment) {
        try {
            AttachmentUploadRequest attachmentUploadRequest = attachmentUploadDataMapper.mapDataSourceRequest(document, attachment);
            AttachmentUploadResponse attachmentUploadResponse = attachmentGateway.uploadAttachment(attachmentUploadRequest);
            return new URL(attachmentUploadResponse.getUrl());
        } catch (GatewayException exception) {
            throw exception;
        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
            throw  new RepositoryException(exception.getMessage(), exception);
        }
    }
}
