package org.tekCorp.api.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
