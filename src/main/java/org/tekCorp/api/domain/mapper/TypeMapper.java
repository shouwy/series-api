package org.tekCorp.api.domain.mapper;

import org.mapstruct.*;
import org.tekCorp.api.domain.dto.EtatPersonnelDto;
import org.tekCorp.api.domain.dto.TypeDto;
import org.tekCorp.api.domain.model.TypeModel;
import org.tekCorp.api.service.EtatPersonalService;
import org.tekCorp.api.service.impl.EtatServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "sptring", uses = {EtatServiceImpl.class})
public interface TypeMapper {

    List<TypeDto> modelToDto(List<TypeModel> all);

    @Mappings({
            @Mapping(target = "etatList", ignore = true)
    })
    TypeDto modelToDto(TypeModel typeModel);

    @Mappings({
            @Mapping(source = "etatList", target = "etatList", qualifiedByName = "etatPersNameToString")
    })
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
