package org.tekCorp.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekCorp.api.domain.dto.TypeDto;
import org.tekCorp.api.domain.mapper.TypeMapper;
import org.tekCorp.api.repository.TypeRepository;
import org.tekCorp.api.service.EtatService;
import org.tekCorp.api.service.TypeService;

import java.util.List;

@Service
public class TypeServiceImpl extends TypeService {

    private TypeRepository typeRepository;
    private TypeMapper typeMapper;

    private EtatService etatService;

    @Autowired
    TypeServiceImpl(TypeRepository typeRepository, TypeMapper typeMapper) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
    }

    @Override
    public List<TypeDto> findAll() {
        return typeMapper.modelToDto(typeRepository.findAll());
    }

    @Override
    public TypeDto find(String id) {
        return typeMapper.modelToDto(typeRepository.findById(id).orElse(null));
    }

    @Override
    public TypeDto save(TypeDto typeDto) {
        return typeMapper.modelToDto(typeRepository.save(typeMapper.dtoToModel(typeDto)));
    }

    @Override
    public TypeDto find(TypeDto typeDto) {
        return typeMapper.modelToDto(typeRepository.findByTypeName(typeDto.getTypeName()));
    }
}
