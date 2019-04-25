package org.tekcorp.api.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class TypeDto {

    private String id;
    private List<EtatPersonnelDto> etatList;
    private String typeName;

    @Override
    public String toString(){
        return "Nom : "+this.typeName;
    }
}
