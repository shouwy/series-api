package org.tekCorp.api.domain.mapper;

import org.mapstruct.Mapper;
import org.tekCorp.api.domain.dto.EtatPersonnelDto;
import org.tekCorp.api.domain.model.EtatPersonnelModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EtatPersonalMapper {
    List<EtatPersonnelDto> modelToDto(List<EtatPersonnelModel> all);
    EtatPersonnelDto modelToDto(EtatPersonnelModel etatPersonnelModel);
    EtatPersonnelModel dtoToModel(EtatPersonnelDto etatPersonnel);
}
