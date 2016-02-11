package org.tekCorp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekCorp.api.domain.Etat;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Repository
public interface EtatRepository extends MongoRepository<Etat, String> {
    Etat findByNom(String nom);
}
