package org.tekcorp.api.domain.dto;

import java.util.List;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class SaisonDto {

    private String seasonName;
    private String anneeProduction;
    private List<EpisodeDto> episodeDtos;
}
