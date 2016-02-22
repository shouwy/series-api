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
        @CompoundIndex(name = "element_idx", def = "{'nom' : 1, 'year' : 1}", unique = true)
})
public class Element {
    @Id
    private String id;
    private String name;
    private Integer year;
    private String synopsis;
    private Type type;
    private Etat etat;
    private EtatPersonnel etatPersonal;
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

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Saison> getSaisons() {
        return saisons;
    }

    public void setSaisons(List<Saison> saisons) {
        this.saisons = saisons;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public EtatPersonnel getEtatPersonal() {
        return etatPersonal;
    }

    public void setEtatPersonal(EtatPersonnel etatPersonal) {
        this.etatPersonal = etatPersonal;
    }

    @Override
    public String toString(){
        return "Id : "+this.id+" - Nom : "+this.name;
    }
}
