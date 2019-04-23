package org.tekCorp.api.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class EtatDto {
    private String id;
    private String etatName;

    @Override
    public String toString(){
        return "Nom : "+this.etatName;
    }

}
