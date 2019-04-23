package org.tekCorp.api.domain.model;

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
public class ElementModel {
    @Id
    private String id;
    private String title;
    private Integer year;
    private String synopsis;
    private TypeModel typeModel;
    private EtatModel etatModel;
    private List<SaisonModel> saisonModels;
    private String image;

    @Override
    public String toString(){
        return "Id : "+this.id+" - Title : "+this.title;
    }
}
