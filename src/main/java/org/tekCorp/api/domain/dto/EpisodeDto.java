package org.tekCorp.api.domain.dto;

import lombok.Data;

import java.util.Calendar;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
class EpisodeDto {

    private String episodeTitle;
    private String synopsis;
    private Calendar dateSortie;
    private EtatPersonnelDto etatPersonnelModel;
}
