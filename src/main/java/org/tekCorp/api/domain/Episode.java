package org.tekCorp.api.domain;

import java.util.Calendar;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
class Episode {

    private String episodeTitle;
    private String synopsis;
    private Calendar dateSortie;
    private EtatPersonnel etatPersonnel;
}
