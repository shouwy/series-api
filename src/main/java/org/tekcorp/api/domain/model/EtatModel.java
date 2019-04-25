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
public class EtatModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String etatName;

    @Override
    public String toString(){
        return "Nom : "+this.etatName;
    }

}
