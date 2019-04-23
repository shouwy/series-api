package org.tekCorp.api.domain.mapper;

import org.mapstruct.Mapper;
import org.tekCorp.api.domain.dto.EtatDto;
import org.tekCorp.api.domain.model.EtatModel;

import java.util.List;

@Mapper(componentModel="spring")
public interface EtatMapper {
    List<EtatDto> modelToDto(List<EtatModel> all);
    EtatDto modelToDto(EtatModel etatModel);
    EtatModel dtoToModel(EtatDto etatDto);
}
