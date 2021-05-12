package com.avilapps.easydocs.data.gateway;

import com.avilapps.easydocs.common.exceptions.NonImplementedException;
import com.avilapps.easydocs.domain.model.Document;
import org.springframework.web.multipart.MultipartFile;


public interface AttachmentGateway {

    default String uploadAttachment(Document document, MultipartFile file) {
        throw new NonImplementedException();
    }
}
