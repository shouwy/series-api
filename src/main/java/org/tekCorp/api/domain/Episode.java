package org.tekCorp.api.domain;

import java.util.Calendar;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
public class Episode {

    private String titre;
    private String synopsis;
    private Calendar dateSortie;
    private EtatPersonnel etatPersonnel;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public EtatPersonnel getEtatPersonnel() {
        return etatPersonnel;
    }

    public void setEtatPersonnel(EtatPersonnel etatPersonnel) {
        this.etatPersonnel = etatPersonnel;
    }

    public Calendar getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Calendar dateSortie) {
        this.dateSortie = dateSortie;
    }
}
