package org.tekcorp.api.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.tekcorp.api.domain.dto.EtatPersonnelDto;
import org.tekcorp.api.domain.dto.TypeDto;
import org.tekcorp.api.domain.model.TypeModel;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    List<TypeDto> modelToDto(List<TypeModel> all);

    @Mapping(source = "etatList", target = "etatList", qualifiedByName = "convertStringToEtatPers")
    TypeDto modelToDto(TypeModel typeModel);

    @Mapping(source = "etatList", target = "etatList", qualifiedByName = "etatPersNameToString")
    TypeModel dtoToModel(TypeDto typeDto);

    @Named("convertStringToEtatPers")
    default List<EtatPersonnelDto> convertStringToEtatPers(List<String> etatPersonnelModels) {
        List<EtatPersonnelDto> etatPersonnelDtos = new ArrayList<>();

        etatPersonnelModels.forEach(name -> {
            EtatPersonnelDto dto = new EtatPersonnelDto();
            dto.setEtatPersName(name);
            etatPersonnelDtos.add(dto);
        });

        return etatPersonnelDtos;
    }

    @Named("etatPersNameToString")
    default List<String> etatPersNameToString(List<EtatPersonnelDto> etatPersonnelDtos) {
        List<String> stringList = new ArrayList<>();

        etatPersonnelDtos.forEach(etatPersonnelDto -> stringList.add(etatPersonnelDto.getEtatPersName()));

        return stringList;
    }

}
