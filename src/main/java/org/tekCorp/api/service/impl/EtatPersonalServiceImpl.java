package org.tekCorp.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tekCorp.api.domain.dto.EtatPersonnelDto;
import org.tekCorp.api.domain.mapper.EtatPersonalMapper;
import org.tekCorp.api.repository.EtatPersonnelRepository;
import org.tekCorp.api.service.EtatPersonalService;

import java.util.List;

public class EtatPersonalServiceImpl implements EtatPersonalService {

    private EtatPersonnelRepository etatPersonnelRepository;
    private EtatPersonalMapper etatPersonalMapper;


    @Autowired
    EtatPersonalServiceImpl(EtatPersonnelRepository etatPersonnelRepository, EtatPersonalMapper etatPersonalMapper) {
        this.etatPersonnelRepository = etatPersonnelRepository;
        this.etatPersonalMapper = etatPersonalMapper;
    }

    @Override
    public List<EtatPersonnelDto> findAll() {
        return etatPersonalMapper.modelToDto(etatPersonnelRepository.findAll());
    }

    @Override
    public EtatPersonnelDto find(String id) {
        return etatPersonalMapper.modelToDto(etatPersonnelRepository.findById(id).orElse(null));
    }

    @Override
    public EtatPersonnelDto find(EtatPersonnelDto etatPersonnel) {
        return etatPersonalMapper.modelToDto(etatPersonnelRepository.findByEtatPersName(etatPersonnel.getEtatPersName()));
    }

    @Override
    public EtatPersonnelDto save(EtatPersonnelDto etatPersonnel) {
        return etatPersonalMapper.modelToDto(etatPersonnelRepository.save(etatPersonalMapper.dtoToModel(etatPersonnel)));
    }
}
