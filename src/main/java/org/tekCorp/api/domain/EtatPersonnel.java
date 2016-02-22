package org.tekCorp.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document

public class EtatPersonnel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String etatPersName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtatPersName() {
        return etatPersName;
    }

    public void setEtatPersName(String etatPersName) {
        this.etatPersName = etatPersName;
    }

    @Override
    public String toString(){
        return "Nom : "+this.etatPersName;
    }
}
