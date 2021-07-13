package com.avilapps.easydocs.domain.repository;

import com.avilapps.easydocs.common.exceptions.NonImplementedException;
import com.avilapps.easydocs.domain.model.Attachment;
import com.avilapps.easydocs.domain.model.Document;

import java.net.URL;

public interface AttachmentRepository {

    default URL uploadAttachment(Document document, Attachment attachment) {
        throw new NonImplementedException();
    }
}
