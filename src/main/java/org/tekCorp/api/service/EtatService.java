package org.tekCorp.api.service;

import java.util.List;

import org.tekCorp.api.domain.dto.EtatDto;

public interface EtatService {
    List<EtatDto> findAll();

    EtatDto find(String id);

    EtatDto find(EtatDto etatDto);

    EtatDto save(EtatDto etatDto);
}
