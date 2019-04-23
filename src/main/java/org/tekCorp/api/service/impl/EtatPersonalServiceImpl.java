package org.tekCorp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tekCorp.api.domain.dto.EtatPersonnelDto;
import org.tekCorp.api.domain.mapper.EtatPersonalMapper;
import org.tekCorp.api.repository.EtatPersonnelRepository;
import org.tekCorp.api.service.EtatPersonalService;

@Service
public class EtatPersonalServiceImpl implements EtatPersonalService {

    @Autowired
    private EtatPersonnelRepository etatPersonnelRepository;

    @Autowired
    private EtatPersonalMapper etatPersonalMapper;

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
