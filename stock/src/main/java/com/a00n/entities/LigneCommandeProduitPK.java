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
public class LigneCommandeProduitPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "commande_id")
    private int commandeId;
    @Basic(optional = false)
    @Column(name = "produit_id")
    private int produitId;

    public LigneCommandeProduitPK() {
    }

    public LigneCommandeProduitPK(int commandeId, int produitId) {
        this.commandeId = commandeId;
        this.produitId = produitId;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commandeId;
        hash += (int) produitId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommandeProduitPK)) {
            return false;
        }
        LigneCommandeProduitPK other = (LigneCommandeProduitPK) object;
        if (this.commandeId != other.commandeId) {
            return false;
        }
        if (this.produitId != other.produitId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.entities.LigneCommandeProduitPK[ commandeId=" + commandeId + ", produitId=" + produitId + " ]";
    }

}
