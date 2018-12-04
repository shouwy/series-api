package org.tekCorp.api.domain;

import java.util.List;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
class Saison {

    private String seasonName;
    private String anneeProduction;
    private List<Episode> episodes;
}
