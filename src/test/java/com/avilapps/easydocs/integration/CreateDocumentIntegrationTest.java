package com.avilapps.easydocs.integration;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.data.gateway.DocumentGateway;
import com.avilapps.easydocs.data.model.AttachmentUploadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateDocumentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @MockBean
    private DocumentGateway documentGateway;

    private MockRestServiceServer mockServer;

    private DocumentEntity savedDocumentEntity;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);

        savedDocumentEntity = new DocumentEntity();
        savedDocumentEntity.setId(1);
    }

    @Test
    void shouldCreateANewDocument() throws Exception {
        AttachmentUploadResponse attachmentUploadResponse = new AttachmentUploadResponse();
        attachmentUploadResponse.setUrl("http://localhost/file.pdf");

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("https://pdf-services-development.herokuapp.com/documents/upload")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(attachmentUploadResponse))
                );

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "brief.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "content".getBytes()
        );

        Mockito.when(documentGateway.save(any(DocumentEntity.class)))
                .thenReturn(savedDocumentEntity);

        mockMvc.perform(multipart("/documents")
                .file(file)
                .param("subject", "Test document")
                .param("folio", "2"))
                .andExpect(status().isCreated());

        mockServer.verify();
    }
}
