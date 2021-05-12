package com.avilapps.easydocs.data.gateway.impl;

import com.avilapps.easydocs.data.gateway.AttachmentGateway;
import com.avilapps.easydocs.data.model.AttachmentUploadResponse;
import com.avilapps.easydocs.domain.model.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AttachmentUploadGatewayImpl implements AttachmentGateway {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentUploadGatewayImpl.class);

    private final RestTemplate restTemplate;

    public AttachmentUploadGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String uploadAttachment(Document document, MultipartFile file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("file")
                    .filename(file.getOriginalFilename())
                    .build();
            fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);

            String destinationPath = String.format("uploads/attachment/content/%s/%s", document.getId(), file.getOriginalFilename());
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileEntity);
            body.add("destinationPath", destinationPath);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<AttachmentUploadResponse> response = restTemplate.exchange(
                    "https://pdf-services-development.herokuapp.com/documents/upload",
                    HttpMethod.POST,
                    requestEntity,
                    AttachmentUploadResponse.class
            );

            AttachmentUploadResponse attachmentUploadResponse = response.getBody();
            return attachmentUploadResponse.getUrl();

        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return null;
    }

    /*
    public void postFile(String filename, byte[] someByteArray) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // This nested HttpEntiy is important to create the correct
        // Content-Disposition entry with metadata "name" and "filename"
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(filename)
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(someByteArray, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "/urlToPostTo",
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }
     */
}
