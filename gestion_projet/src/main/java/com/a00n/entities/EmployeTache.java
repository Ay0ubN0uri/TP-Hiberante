/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "employeTache")
@NamedQueries({
    @NamedQuery(name = "EmployeTache.findAll", query = "SELECT e FROM EmployeTache e"),
    @NamedQuery(name = "EmployeTache.findByTacheId", query = "SELECT e FROM EmployeTache e WHERE e.employeTachePK.tacheId = :tacheId"),
    @NamedQuery(name = "EmployeTache.findByEmployeId", query = "SELECT e FROM EmployeTache e WHERE e.employeTachePK.employeId = :employeId"),
    @NamedQuery(name = "EmployeTache.findByDateDebutReelle", query = "SELECT e FROM EmployeTache e WHERE e.dateDebutReelle = :dateDebutReelle"),
    @NamedQuery(name = "EmployeTache.findByDateFinReelle", query = "SELECT e FROM EmployeTache e WHERE e.dateFinReelle = :dateFinReelle")})
public class EmployeTache implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeTachePK employeTachePK;
    @Column(name = "dateDebutReelle")
    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;
    @Column(name = "dateFinReelle")
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;
    @JoinColumn(name = "tache_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tache tache;
    @JoinColumn(name = "employe_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    public EmployeTache() {
    }

    public EmployeTache(EmployeTachePK employeTachePK, Date dateDebutReelle, Date dateFinReelle) {
        this.employeTachePK = employeTachePK;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinReelle = dateFinReelle;
    }

    public EmployeTache(EmployeTachePK employeTachePK) {
        this.employeTachePK = employeTachePK;
    }

    public EmployeTache(int tacheId, int employeId) {
        this.employeTachePK = new EmployeTachePK(tacheId, employeId);
    }

    public EmployeTachePK getEmployeTachePK() {
        return employeTachePK;
    }

    public void setEmployeTachePK(EmployeTachePK employeTachePK) {
        this.employeTachePK = employeTachePK;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeTachePK != null ? employeTachePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeTache)) {
            return false;
        }
        EmployeTache other = (EmployeTache) object;
        if ((this.employeTachePK == null && other.employeTachePK != null) || (this.employeTachePK != null && !this.employeTachePK.equals(other.employeTachePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.tp3.ex3.entities.EmployeTache[ employeTachePK=" + employeTachePK + " ]";
    }

}
