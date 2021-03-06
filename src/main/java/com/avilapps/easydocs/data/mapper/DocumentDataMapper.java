package com.avilapps.easydocs.data.mapper;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.domain.model.Document;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DocumentDataMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "folio", source = "folio")
    @Mapping(target = "path", source = "path")
    DocumentEntity documentToDocumentEntity(Document document);

    @InheritInverseConfiguration
    Document documentEntityToDocument(DocumentEntity documentEntity);
}
