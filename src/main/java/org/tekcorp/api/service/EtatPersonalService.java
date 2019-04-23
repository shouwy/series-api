package org.tekcorp.api.service;

import org.tekcorp.api.domain.dto.EtatPersonnelDto;

import java.util.List;

public interface EtatPersonalService {
    List<EtatPersonnelDto> findAll();

    EtatPersonnelDto find(String id);

    EtatPersonnelDto find(EtatPersonnelDto EtatPersonnelDto);

    EtatPersonnelDto save(EtatPersonnelDto EtatPersonnelDto);

}
