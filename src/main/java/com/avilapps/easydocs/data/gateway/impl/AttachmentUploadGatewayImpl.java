package com.avilapps.easydocs.data.gateway.impl;

import com.avilapps.easydocs.data.gateway.AttachmentGateway;
import com.avilapps.easydocs.data.model.AttachmentUploadRequest;
import com.avilapps.easydocs.data.model.AttachmentUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AttachmentUploadGatewayImpl implements AttachmentGateway {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentUploadGatewayImpl.class);

    private final RestTemplate restTemplate;
    private final String endpointUrl;

    public AttachmentUploadGatewayImpl(RestTemplate restTemplate,
                                       @Value("${application.connector.path.upload-attachment}") String endpointUrl) {
        this.restTemplate = restTemplate;
        this.endpointUrl = endpointUrl;
    }

    @Override
    public AttachmentUploadResponse uploadAttachment(AttachmentUploadRequest attachmentUploadRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("file")
                    .filename(attachmentUploadRequest.getFilename())
                    .build();
            fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(attachmentUploadRequest.getContent(), fileMap);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileEntity);
            body.add("destinationPath", attachmentUploadRequest.getPath());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<AttachmentUploadResponse> response = restTemplate.exchange(
                    endpointUrl,
                    HttpMethod.POST,
                    requestEntity,
                    AttachmentUploadResponse.class
            );

            return response.getBody();

        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return null;
    }
}
