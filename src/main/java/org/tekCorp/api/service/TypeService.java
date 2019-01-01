package org.tekCorp.api.service;

import org.tekCorp.api.domain.dto.TypeDto;

import java.util.List;

public abstract class TypeService {
    public abstract List<TypeDto> findAll();

    public abstract TypeDto find(String id);

    public abstract TypeDto save(TypeDto typeDto);

    public abstract TypeDto find(TypeDto typeDto);
}
