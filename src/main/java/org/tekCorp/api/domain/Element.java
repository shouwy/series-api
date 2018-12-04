package org.tekCorp.api.domain;

import java.util.List;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "element_idx", def = "{'title' : 1, 'year' : 1}", unique = true)
})
@Data
public class Element {
    @Id
    private String id;
    private String title;
    private Integer year;
    private String synopsis;
    private Type type;
    private Etat etat;
    private List<Saison> saisons;
    private String image;

    @Override
    public String toString(){
        return "Id : "+this.id+" - Title : "+this.title;
    }
}
