package org.tekCorp.api.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class ElementDto {
    private String id;
    private String title;
    private Integer year;
    private String synopsis;
    private TypeDto typeDto;
    private EtatDto etatDto;
    private List<SaisonDto> saisonDtos;
    private String image;

    @Override
    public String toString(){
        return "Id : "+this.id+" - Title : "+this.title;
    }
}
