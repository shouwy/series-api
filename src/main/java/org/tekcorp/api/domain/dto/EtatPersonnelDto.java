package org.tekcorp.api.domain.dto;

import lombok.Data;


/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class EtatPersonnelDto {
    private String id;
    private String etatPersName;

    @Override
    public String toString(){
        return "Nom : "+this.etatPersName;
    }
}
