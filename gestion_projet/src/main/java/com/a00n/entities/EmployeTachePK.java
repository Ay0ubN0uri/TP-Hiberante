/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author ay0ub
 */
@Embeddable
public class EmployeTachePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "tache_id")
    private int tacheId;
    @Basic(optional = false)
    @Column(name = "employe_id")
    private int employeId;

    public EmployeTachePK() {
    }

    public EmployeTachePK(int tacheId, int employeId) {
        this.tacheId = tacheId;
        this.employeId = employeId;
    }

    public int getTacheId() {
        return tacheId;
    }

    public void setTacheId(int tacheId) {
        this.tacheId = tacheId;
    }

    public int getEmployeId() {
        return employeId;
    }

    public void setEmployeId(int employeId) {
        this.employeId = employeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tacheId;
        hash += (int) employeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeTachePK)) {
            return false;
        }
        EmployeTachePK other = (EmployeTachePK) object;
        if (this.tacheId != other.tacheId) {
            return false;
        }
        if (this.employeId != other.employeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.tp3.ex3.entities.EmployeTachePK[ tacheId=" + tacheId + ", employeId=" + employeId + " ]";
    }

}
