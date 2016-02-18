package org.tekCorp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekCorp.api.domain.EtatPersonnel;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Repository
public interface EtatPersonnelRepository extends MongoRepository<EtatPersonnel, String> {
    EtatPersonnel findByNomAndIdType(String nom, String idType);
    List<EtatPersonnel> findByIdType(String idType);
}
