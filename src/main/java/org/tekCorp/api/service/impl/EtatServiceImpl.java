package org.tekCorp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekCorp.api.domain.dto.EtatDto;
import org.tekCorp.api.domain.mapper.EtatMapper;
import org.tekCorp.api.repository.EtatRepository;
import org.tekCorp.api.service.EtatService;

@Service
public class EtatServiceImpl implements EtatService {

    @Autowired
    private EtatRepository etatRepository;

    @Autowired
    private EtatMapper etatMapper;

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
