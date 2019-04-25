package org.tekcorp.api.domain.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
@Data
public class EtatPersonnelModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String etatPersName;

    @Override
    public String toString(){
        return "Nom : "+this.etatPersName;
    }
}
