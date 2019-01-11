package org.tekCorp.api.service;

import java.util.List;

import org.tekCorp.api.domain.dto.TypeDto;

public interface TypeService {
    List<TypeDto> findAll();

    TypeDto find(String id);

    TypeDto save(TypeDto typeDto);

    TypeDto find(TypeDto typeDto);
}
