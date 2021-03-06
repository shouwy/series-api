package org.tekcorp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekcorp.api.domain.model.TypeModel;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Repository
public interface TypeRepository extends MongoRepository<TypeModel, String> {
    TypeModel findByTypeName(String typeName);
}
