package org.tekcorp.api.domain.dto;

import java.util.Calendar;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
public class EpisodeDto {

    private String episodeTitle;
    private String synopsis;
    private Calendar dateSortie;
    private EtatPersonnelDto etatPersonnelModel;
}
