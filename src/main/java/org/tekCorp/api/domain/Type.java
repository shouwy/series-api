package org.tekCorp.api.domain;

import java.util.List;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
@Data
public class Type {

    @Id
    private String id;
    private List<EtatPersonnel> etatList;
    @Indexed(unique = true)
    private String typeName;

    @Override
    public String toString(){
        return "Nom : "+this.typeName;
    }
}
