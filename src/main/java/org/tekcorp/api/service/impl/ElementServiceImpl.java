package org.tekcorp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekcorp.api.domain.dto.ElementDto;
import org.tekcorp.api.domain.dto.EtatDto;
import org.tekcorp.api.domain.dto.TypeDto;
import org.tekcorp.api.domain.mapper.ElementMapper;
import org.tekcorp.api.domain.mapper.EtatMapper;
import org.tekcorp.api.domain.mapper.TypeMapper;
import org.tekcorp.api.repository.ElementRepository;
import org.tekcorp.api.service.ElementService;

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private ElementMapper elementMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private EtatMapper etatMapper;

    @Override
    public List<ElementDto> findAll() {
        return elementMapper.modelToDto(elementRepository.findAll());
    }

    @Override
    public ElementDto find(String id) {
        return elementMapper.modelToDto(elementRepository.findById(id).orElse(null));
    }

    @Override
    public ElementDto findByTitleAndYear(ElementDto elementDto) {
        return elementMapper.modelToDto(elementRepository.findByTitleAndYear(elementDto.getTitle(), elementDto.getYear()));
    }

    @Override
    public List<ElementDto> findByTypeModel(TypeDto typeModel) {
        return elementMapper.modelToDto(elementRepository.findByTypeModel(typeMapper.dtoToModel(typeModel)));
    }

    @Override
    public List<ElementDto> findByEtatModel(EtatDto etatModel) {
        return elementMapper.modelToDto(elementRepository.findByEtatModel(etatMapper.dtoToModel(etatModel)));
    }

    @Override
    public ElementDto save(ElementDto etatPersonnel) {
        return elementMapper.modelToDto(elementRepository.save(elementMapper.dtoToModel(etatPersonnel)));
    }
}
