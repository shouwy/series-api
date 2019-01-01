package org.tekCorp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekCorp.api.domain.model.ElementModel;
import org.tekCorp.api.domain.model.EtatModel;
import org.tekCorp.api.domain.model.TypeModel;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Repository
public interface ElementRepository extends MongoRepository<ElementModel, String> {
    List<ElementModel> findByType(TypeModel typeModel);
    ElementModel findByTitleAndYear(String title, Integer year);
    List<ElementModel> findByEtat(EtatModel etatModel);
}
