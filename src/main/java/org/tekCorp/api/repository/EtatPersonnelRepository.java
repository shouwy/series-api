package org.tekCorp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekCorp.api.domain.model.EtatPersonnelModel;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Repository
public interface EtatPersonnelRepository extends MongoRepository<EtatPersonnelModel, String> {
    EtatPersonnelModel findByEtatPersName(String etatPers);
    //List<EtatPersonnelDto> findByType(TypeDto type);
}
