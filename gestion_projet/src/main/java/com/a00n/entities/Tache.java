/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ay0ub
 */
@Entity
@Table(name = "tache")
@NamedQueries({
    @NamedQuery(name = "Tache.findAll", query = "SELECT t FROM Tache t"),
    @NamedQuery(name = "Tache.findById", query = "SELECT t FROM Tache t WHERE t.id = :id"),
    @NamedQuery(name = "Tache.findByNom", query = "SELECT t FROM Tache t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tache.findByDateDebut", query = "SELECT t FROM Tache t WHERE t.dateDebut = :dateDebut"),
    @NamedQuery(name = "Tache.findPriceGreaterThen1000", query = "SELECT t FROM Tache t WHERE t.prix >= 1000"),
    @NamedQuery(name = "Tache.findByDateFin", query = "SELECT t FROM Tache t WHERE t.dateFin = :dateFin"),
    @NamedQuery(name = "Tache.findByPrix", query = "SELECT t FROM Tache t WHERE t.prix = :prix")})
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
//    @OneToMany(mappedBy = "tacheId")
//    private List<Projet> projetList;
    @JoinColumn(name = "projet_id", referencedColumnName = "id")
    @ManyToOne
    private Projet projetId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tache")
    private List<EmployeTache> employeTacheList;

    public Tache() {
    }

    public Tache(String nom, Date dateDebut, Date dateFin, Double prix, Projet projetId) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.projetId = projetId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Projet getProjetId() {
        return projetId;
    }

    public void setProjetId(Projet projetId) {
        this.projetId = projetId;
    }

    public List<EmployeTache> getEmployeTacheList() {
        return employeTacheList;
    }

    public void setEmployeTacheList(List<EmployeTache> employeTacheList) {
        this.employeTacheList = employeTacheList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tache)) {
            return false;
        }
        Tache other = (Tache) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.tp3.ex3.entities.Tache[ id=" + id + " ]";
    }

}
