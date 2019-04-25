package org.tekcorp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekcorp.api.domain.model.EtatModel;

/**
 * Created by FRERES Thierry on 22/02/2016.
 */
@Repository
public interface EtatRepository extends MongoRepository<EtatModel, String> {
    EtatModel findByEtatName(String etatName);
}
