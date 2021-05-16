package com.avilapps.easydocs.presentation.mapper;

import com.avilapps.easydocs.domain.model.Attachment;
import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.presentation.model.CreateDocumentRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DocumentCreateApiMapper {

    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "folio", source = "folio")
    @Mapping(target = "attachment", source = "file")
    Document mapApiRequest(CreateDocumentRequest documentRequest);

    default Attachment multipartFileToAttachment(MultipartFile multipartFile) throws IOException {
        Attachment attachment = new Attachment();
        attachment.setFilename(multipartFile.getOriginalFilename());
        attachment.setContent(multipartFile.getBytes());

        return attachment;
    }
}
