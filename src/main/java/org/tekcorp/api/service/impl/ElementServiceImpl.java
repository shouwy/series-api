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
import org.tekcorp.api.domain.model.ElementModel;
import org.tekcorp.api.repository.ElementRepository;
import org.tekcorp.api.service.ElementService;

@Service
public class ElementServiceImpl implements ElementService {

    private final ElementRepository elementRepository;

    private final ElementMapper elementMapper;

    private final TypeMapper typeMapper;

    private final EtatMapper etatMapper;

    @Autowired
    public ElementServiceImpl(ElementRepository elementRepository, ElementMapper elementMapper, TypeMapper typeMapper, EtatMapper etatMapper) {
        this.elementRepository = elementRepository;
        this.elementMapper = elementMapper;
        this.typeMapper = typeMapper;
        this.etatMapper = etatMapper;
    }

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
        List<ElementModel> models = elementRepository.findByTypeModel(typeMapper.dtoToModel(typeModel));
        return elementMapper.modelToDto(models);
    }

    @Override
    public List<ElementDto> findByEtatModel(EtatDto etatModel) {
        List<ElementModel> models = elementRepository.findByEtatModel(etatMapper.dtoToModel(etatModel));
        return elementMapper.modelToDto(models);
    }

    @Override
    public ElementDto save(ElementDto etatPersonnel) {
        return elementMapper.modelToDto(elementRepository.save(elementMapper.dtoToModel(etatPersonnel)));
    }
}
