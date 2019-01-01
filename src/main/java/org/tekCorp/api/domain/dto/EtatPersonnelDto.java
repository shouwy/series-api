package org.tekCorp.api.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


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
