package org.tekcorp.api.domain.dto;

import lombok.Data;

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
