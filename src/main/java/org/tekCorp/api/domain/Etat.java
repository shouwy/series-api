package org.tekCorp.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
public class Etat {
    @Id
    private String id;
    @Indexed(unique = true)
    private String etatName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtatName() {
        return etatName;
    }

    public void setEtatName(String etatName) {
        this.etatName = etatName;
    }

    @Override
    public String toString(){
        return "Nom : "+this.etatName;
    }

}
