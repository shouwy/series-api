package org.tekcorp.api.service;

import java.util.List;

import org.tekcorp.api.domain.dto.ElementDto;
import org.tekcorp.api.domain.model.EtatModel;
import org.tekcorp.api.domain.model.TypeModel;

public interface ElementService {
    List<ElementDto> findAll();

    ElementDto find(String id);

    ElementDto save(ElementDto elementDto);

    ElementDto findByTitleAndYear(ElementDto elementDto);

    List<ElementDto> findByTypeModel(TypeModel typeModel);

    List<ElementDto> findByEtatModel(EtatModel etatModel);
}
