package com.avilapps.easydocs.data.gateway;

import com.avilapps.easydocs.common.exceptions.NonImplementedException;
import com.avilapps.easydocs.data.model.AttachmentUploadRequest;
import com.avilapps.easydocs.data.model.AttachmentUploadResponse;


public interface AttachmentGateway {

    default AttachmentUploadResponse uploadAttachment(AttachmentUploadRequest attachmentUploadRequest) {
        throw new NonImplementedException();
    }
}
