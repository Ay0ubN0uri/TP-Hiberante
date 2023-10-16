/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ay0ub
 */
@Entity
@Table(name = "femme")
@NamedQueries({
    @NamedQuery(name = "Femme.findAll", query = "SELECT f FROM Femme f"),
    @NamedQuery(
            name = "Femme.findNumberOfChildrenBetweenDates",
            query = "SELECT SUM(m.nbEnfants) FROM Marriage m "
            + "WHERE m.femme = :femme "
            + "AND m.dateDebut >= :startDate "
            + "AND (m.dateFin IS NULL OR m.dateFin <= :endDate)"
    ),
    @NamedQuery(
            name = "Femme.findFemmesMarriedMultipleTimes",
            query = "SELECT f FROM Femme f "
            + "WHERE (SELECT COUNT(m) FROM Marriage m WHERE m.femme = f) >= 2"
    ),
    @NamedQuery(name = "Femme.findById", query = "SELECT f FROM Femme f WHERE f.id = :id")})
public class Femme extends Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "femme")
    private List<Marriage> marriageList;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Personne personne;

    public Femme() {
    }

    public Femme(String nom, String prenom, String address, String telephone, Date dateNaissance) {
        super(nom, prenom, address, telephone, dateNaissance);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Marriage> getMarriageList() {
        return marriageList;
    }

    public void setMarriageList(List<Marriage> marriageList) {
        this.marriageList = marriageList;
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
        if (!(object instanceof Femme)) {
            return false;
        }
        Femme other = (Femme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.entities.Femme[ id=" + id + " ]";
    }

}
