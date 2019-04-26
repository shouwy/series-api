package org.tekcorp.api.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.tekcorp.api.domain.dto.EtatPersonnelDto;
import org.tekcorp.api.domain.dto.TypeDto;
import org.tekcorp.api.domain.model.TypeModel;
import org.tekcorp.api.service.EtatPersonalService;
import org.tekcorp.api.service.impl.EtatPersonalServiceImpl;

@Mapper(componentModel = "spring", uses = {EtatPersonalServiceImpl.class})
public interface TypeMapper {

    List<TypeDto> modelToDto(List<TypeModel> all);

    @Mapping(target = "etatList", ignore = true)
    TypeDto modelToDto(TypeModel typeModel);

    @Mapping(source = "etatList", target = "etatList", qualifiedByName = "etatPersNameToString")
    TypeModel dtoToModel(TypeDto typeDto);

    @AfterMapping
    default void convertStringToEtatPers(TypeModel typeModel, @MappingTarget TypeDto typeDto, @Context EtatPersonalService etatService) {
        List<EtatPersonnelDto> etatPersonnelDtos = new ArrayList<>();

        typeModel.getEtatList().forEach(id-> etatPersonnelDtos.add(etatService.find(id)));

        typeDto.setEtatList(etatPersonnelDtos);
    }

    @Named("etatPersNameToString")
    default List<String> etatPersNameToString(List<EtatPersonnelDto> etatPersonnelDtos) {
        List<String> stringList = new ArrayList<>();

        etatPersonnelDtos.forEach(etatPersonnelDto -> stringList.add(etatPersonnelDto.getEtatPersName()));

        return stringList;
    }

}
