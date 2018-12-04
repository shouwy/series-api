package org.tekCorp.api.domain;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
@Data
public class Etat {
    @Id
    private String id;
    @Indexed(unique = true)
    private String etatName;

    @Override
    public String toString(){
        return "Nom : "+this.etatName;
    }

}
