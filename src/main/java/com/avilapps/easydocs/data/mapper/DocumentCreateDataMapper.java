package com.avilapps.easydocs.data.mapper;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import com.avilapps.easydocs.domain.model.Document;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DocumentCreateDataMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "folio", source = "folio")
    @Mapping(target = "path", ignore = true)
    DocumentEntity mapDataRequest(Document document);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "folio", source = "folio")
    @Mapping(target = "path", ignore = true)
    Document mapDataResponse(DocumentEntity documentEntity);
}
