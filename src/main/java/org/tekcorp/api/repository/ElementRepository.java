package org.tekcorp.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekcorp.api.domain.model.ElementModel;
import org.tekcorp.api.domain.model.EtatModel;
import org.tekcorp.api.domain.model.TypeModel;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Repository
public interface ElementRepository extends MongoRepository<ElementModel, String> {
    List<ElementModel> findByTypeModel(TypeModel typeModel);
    ElementModel findByTitleAndYear(String title, Integer year);
    List<ElementModel> findByEtatModel(EtatModel etatModel);

}
