package org.tekCorp.api.domain;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
public class Saison {

    private String nom;
    private String anneeProduction;
    private List<Episode> episodes;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAnneeProduction() {
        return anneeProduction;
    }

    public void setAnneeProduction(String anneeProduction) {
        this.anneeProduction = anneeProduction;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
