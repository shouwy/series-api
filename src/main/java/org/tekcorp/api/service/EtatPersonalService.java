package org.tekcorp.api.service;

import java.util.List;

import org.tekcorp.api.domain.dto.EtatPersonnelDto;

public interface EtatPersonalService {
    List<EtatPersonnelDto> findAll();

    EtatPersonnelDto find(String id);

    EtatPersonnelDto find(EtatPersonnelDto etatPersonnelDto);

    EtatPersonnelDto save(EtatPersonnelDto etatPersonnelDto);

}
