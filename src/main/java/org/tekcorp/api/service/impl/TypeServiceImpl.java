package org.tekcorp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekcorp.api.domain.dto.TypeDto;
import org.tekcorp.api.domain.mapper.TypeMapper;
import org.tekcorp.api.repository.TypeRepository;
import org.tekcorp.api.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeMapper typeMapper;

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
