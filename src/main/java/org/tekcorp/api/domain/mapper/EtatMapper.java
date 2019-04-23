package org.tekcorp.api.domain.mapper;

import org.mapstruct.Mapper;
import org.tekcorp.api.domain.dto.EtatDto;
import org.tekcorp.api.domain.model.EtatModel;

import java.util.List;

@Mapper(componentModel="spring")
public interface EtatMapper {
    List<EtatDto> modelToDto(List<EtatModel> all);
    EtatDto modelToDto(EtatModel etatModel);
    EtatModel dtoToModel(EtatDto etatDto);
}
