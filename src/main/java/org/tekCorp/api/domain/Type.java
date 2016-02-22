package org.tekCorp.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
public class Type {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Nom : "+this.name;
    }

    public String getId() {
        return id;
    }
}
