package org.tekcorp.api.service;

import java.util.List;

import org.tekcorp.api.domain.dto.ElementDto;
import org.tekcorp.api.domain.dto.EtatDto;
import org.tekcorp.api.domain.dto.TypeDto;

public interface ElementService {
    List<ElementDto> findAll();

    ElementDto find(String id);

    ElementDto save(ElementDto elementDto);

    ElementDto findByTitleAndYear(ElementDto elementDto);

    List<ElementDto> findByTypeModel(TypeDto typeModel);

    List<ElementDto> findByEtatModel(EtatDto etatModel);
}
