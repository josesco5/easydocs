package com.avilapps.easydocs.data.mapper;

import com.avilapps.easydocs.data.model.AttachmentUploadRequest;
import com.avilapps.easydocs.domain.model.Attachment;
import com.avilapps.easydocs.domain.model.Document;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AttachmentUploadDataMapper {

    @Mapping(target = "filename", source = "attachment.filename")
    @Mapping(target = "content", source = "attachment.content")
    @Mapping(target = "path", expression = "java( String.format(\"uploads/attachment/content/%s/%s\", document.getId(), attachment.getFilename()) )")
    AttachmentUploadRequest mapDataSourceRequest(Document document, Attachment attachment);
}
