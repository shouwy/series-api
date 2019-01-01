package org.tekCorp.api.domain.model;

import java.util.List;

import lombok.Data;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Data
class SaisonModel {

    private String seasonName;
    private String anneeProduction;
    private List<EpisodeModel> episodeModels;
}
