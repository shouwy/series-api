package org.tekcorp.api.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.tekcorp.api.domain.dto.ElementDto;
import org.tekcorp.api.domain.model.ElementModel;

@Mapper(componentModel = "spring", uses = {TypeMapper.class, EtatMapper.class})
public interface ElementMapper {
    List<ElementDto> modelToDto(List<ElementModel> all);
    ElementDto modelToDto(ElementModel etatModel);
    ElementModel dtoToModel(ElementDto etatDto);
}
