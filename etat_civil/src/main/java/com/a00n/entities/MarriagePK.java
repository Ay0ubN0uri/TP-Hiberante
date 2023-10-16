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
public class MarriagePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "homme_id")
    private int hommeId;
    @Basic(optional = false)
    @Column(name = "femme_id")
    private int femmeId;

    public MarriagePK() {
    }

    public MarriagePK(int hommeId, int femmeId) {
        this.hommeId = hommeId;
        this.femmeId = femmeId;
    }

    public int getHommeId() {
        return hommeId;
    }

    public void setHommeId(int hommeId) {
        this.hommeId = hommeId;
    }

    public int getFemmeId() {
        return femmeId;
    }

    public void setFemmeId(int femmeId) {
        this.femmeId = femmeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) hommeId;
        hash += (int) femmeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarriagePK)) {
            return false;
        }
        MarriagePK other = (MarriagePK) object;
        if (this.hommeId != other.hommeId) {
            return false;
        }
        if (this.femmeId != other.femmeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.entities.MarriagePK[ hommeId=" + hommeId + ", femmeId=" + femmeId + " ]";
    }

}
