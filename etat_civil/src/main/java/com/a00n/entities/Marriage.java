/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.entities;

import com.a00n.service.HommeService;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ay0ub
 */
@Entity
@Table(name = "marriage")
@NamedQueries({
    @NamedQuery(name = "Marriage.findAll", query = "SELECT m FROM Marriage m"),
    @NamedQuery(name = "Marriage.findByDateDebut", query = "SELECT m FROM Marriage m WHERE m.dateDebut = :dateDebut"),
    @NamedQuery(name = "Marriage.findByDateFin", query = "SELECT m FROM Marriage m WHERE m.dateFin = :dateFin"),
    @NamedQuery(name = "Marriage.findByNbEnfants", query = "SELECT m FROM Marriage m WHERE m.nbEnfants = :nbEnfants"),
    @NamedQuery(name = "Marriage.findByHommeId", query = "SELECT m FROM Marriage m WHERE m.marriagePK.hommeId = :hommeId"),
    @NamedQuery(name = "Marriage.findByFemmeId", query = "SELECT m FROM Marriage m WHERE m.marriagePK.femmeId = :femmeId")})
public class Marriage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MarriagePK marriagePK;
    @Basic(optional = false)
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Column(name = "nbEnfants")
    private Integer nbEnfants;
    @JoinColumn(name = "homme_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Homme homme;
    @JoinColumn(name = "femme_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Femme femme;

    public Marriage() {
        this.dateDebut = new Date();
    }

    public Marriage(MarriagePK marriagePK, int nbEnfants) {
        super();
        this.dateDebut = new Date();
        this.marriagePK = marriagePK;
        this.nbEnfants = nbEnfants;
    }

    public Marriage(int hommeId, int femmeId) {
        super();
        this.marriagePK = new MarriagePK(hommeId, femmeId);
    }

    @PrePersist
    public void beforeInsert() {
        System.out.println("================================================================");
        System.out.println(this.marriagePK);
    }

    public MarriagePK getMarriagePK() {
        return marriagePK;
    }

    public void setMarriagePK(MarriagePK marriagePK) {
        this.marriagePK = marriagePK;
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

    public Integer getNbEnfants() {
        return nbEnfants;
    }

    public void setNbEnfants(Integer nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marriagePK != null ? marriagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marriage)) {
            return false;
        }
        Marriage other = (Marriage) object;
        if ((this.marriagePK == null && other.marriagePK != null) || (this.marriagePK != null && !this.marriagePK.equals(other.marriagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.entities.Marriage[ marriagePK=" + marriagePK + " ]";
    }

}
