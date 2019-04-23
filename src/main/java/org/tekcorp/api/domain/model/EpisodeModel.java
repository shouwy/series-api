package org.tekcorp.api.domain.model;

import java.util.Calendar;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
class EpisodeModel {

    private String episodeTitle;
    private String synopsis;
    private Calendar dateSortie;
    private EtatPersonnelModel etatPersonnelModel;
}
