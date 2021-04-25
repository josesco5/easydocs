package com.avilapps.easydocs.presentation.mapper;

import com.avilapps.easydocs.domain.model.Document;
import com.avilapps.easydocs.presentation.model.CreateDocumentRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DocumentCreateApiMapper {

    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "folio", source = "folio")
    Document mapApiRequest(CreateDocumentRequest documentRequest);
}
