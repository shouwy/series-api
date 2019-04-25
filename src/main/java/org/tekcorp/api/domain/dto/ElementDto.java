package org.tekcorp.api.domain.dto;

import java.util.List;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class ElementDto {
    private String id;
    private String title;
    private Integer year;
    private String synopsis;
    private TypeDto type;
    private EtatDto etat;
    private List<SaisonDto> seasons;
    private String image;

    @Override
    public String toString(){
        return "Id : "+this.id+" - Title : "+this.title;
    }
}
