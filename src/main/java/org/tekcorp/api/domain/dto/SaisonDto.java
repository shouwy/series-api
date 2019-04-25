package org.tekcorp.api.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
class SaisonDto {

    private String seasonName;
    private String anneeProduction;
    private List<EpisodeDto> episodeDtos;
}
