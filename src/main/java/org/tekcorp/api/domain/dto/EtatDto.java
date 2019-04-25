package org.tekcorp.api.domain.dto;

import lombok.Data;


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
