package org.tekcorp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekcorp.api.domain.dto.EtatDto;
import org.tekcorp.api.domain.mapper.EtatMapper;
import org.tekcorp.api.repository.EtatRepository;
import org.tekcorp.api.service.EtatService;

@Service
public class EtatServiceImpl implements EtatService {

    private final EtatRepository etatRepository;

    private final EtatMapper etatMapper;

    @Autowired
    public EtatServiceImpl(EtatRepository etatRepository, EtatMapper etatMapper) {
        this.etatRepository = etatRepository;
        this.etatMapper = etatMapper;
    }

    @Override
    public List<EtatDto> findAll() {
        return etatMapper.modelToDto(etatRepository.findAll());
    }

    @Override
    public EtatDto find(String id) {
        return etatMapper.modelToDto(etatRepository.findById(id).orElse(null));
    }

    @Override
    public EtatDto find(EtatDto etatDto) {
        return etatMapper.modelToDto(etatRepository.findByEtatName(etatDto.getEtatName()));
    }

    @Override
    public EtatDto save(EtatDto etatDto) {
        return etatMapper.modelToDto(etatRepository.save(etatMapper.dtoToModel(etatDto)));
    }
}
