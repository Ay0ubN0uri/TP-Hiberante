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
@Table(name = "homme")
@NamedQueries({
    @NamedQuery(name = "Homme.findAll", query = "SELECT h FROM Homme h"),
    @NamedQuery(name = "Homme.findById", query = "SELECT h FROM Homme h WHERE h.id = :id")})
public class Homme extends Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
//    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Personne personne;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "homme")
    private List<Marriage> marriageList;

    public Homme() {
    }

    public Homme(String nom, String prenom, String address, String telephone, Date dateNaissance) {
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
        if (!(object instanceof Homme)) {
            return false;
        }
        Homme other = (Homme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.entities.Homme[ id=" + id + " ]";
    }

}
