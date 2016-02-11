package org.tekCorp.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by FRERES Thierry on 05/02/2016.
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "element_idx", def = "{'nom' : 1, 'year' : 1}")
})
public class Element {
    @Id
    private String id;
    private String nom;
    private Integer year;
    private String synopsis;
    private String type;
    private String etat;
    private String etatPersonnel;
    private List<Saison> saisons;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<Saison> getSaisons() {
        return saisons;
    }

    public void setSaisons(List<Saison> saisons) {
        this.saisons = saisons;
    }

    public String getEtatPersonnel() {
        return etatPersonnel;
    }

    public void setEtatPersonnel(String etatPersonnel) {
        this.etatPersonnel = etatPersonnel;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return "Id : "+this.id+" - Nom : "+this.nom;
    }
}
