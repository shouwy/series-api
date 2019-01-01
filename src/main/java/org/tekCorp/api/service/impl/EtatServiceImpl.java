package org.tekCorp.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekCorp.api.domain.dto.EtatDto;
import org.tekCorp.api.domain.mapper.EtatMapper;
import org.tekCorp.api.repository.EtatRepository;
import org.tekCorp.api.service.EtatService;

import java.util.List;

@Service
public class EtatServiceImpl extends EtatService {

    private EtatRepository etatRepository;

    private final EtatMapper etatMapper;

    @Autowired
    EtatServiceImpl(EtatRepository etatRepository, EtatMapper etatMapper) {
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
