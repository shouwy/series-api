package org.tekCorp.api.service;

import org.tekCorp.api.domain.dto.EtatDto;

import java.util.List;

public abstract class EtatService {
    public abstract List<EtatDto> findAll();

    public abstract EtatDto find(String id);

    public abstract EtatDto find(EtatDto etatDto);

    public abstract EtatDto save(EtatDto etatDto);
}
