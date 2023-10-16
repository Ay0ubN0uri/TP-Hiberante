package com.a00n.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author ay0ub
 */
@Entity
@Table(name = "ligneCommandeProduit")
@NamedQueries({
    @NamedQuery(name = "LigneCommandeProduit.findAll", query = "SELECT l FROM LigneCommandeProduit l"),
    @NamedQuery(name = "LigneCommandeProduit.findByCommandeId", query = "SELECT l FROM LigneCommandeProduit l WHERE l.ligneCommandeProduitPK.commandeId = :commandeId"),
    @NamedQuery(name = "LigneCommandeProduit.findByProduitId", query = "SELECT l FROM LigneCommandeProduit l WHERE l.ligneCommandeProduitPK.produitId = :produitId"),
    @NamedQuery(name = "LigneCommandeProduit.findByQuantity", query = "SELECT l FROM LigneCommandeProduit l WHERE l.quantity = :quantity")})
public class LigneCommandeProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LigneCommandeProduitPK ligneCommandeProduitPK;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "commande_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commande commande;
    @JoinColumn(name = "produit_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produit produit;

    public LigneCommandeProduit() {
    }

    public LigneCommandeProduit(LigneCommandeProduitPK ligneCommandeProduitPK) {
        this.ligneCommandeProduitPK = ligneCommandeProduitPK;
    }

    public LigneCommandeProduit(int commandeId, int produitId) {
        this.ligneCommandeProduitPK = new LigneCommandeProduitPK(commandeId, produitId);
    }

    public LigneCommandeProduitPK getLigneCommandeProduitPK() {
        return ligneCommandeProduitPK;
    }

    public void setLigneCommandeProduitPK(LigneCommandeProduitPK ligneCommandeProduitPK) {
        this.ligneCommandeProduitPK = ligneCommandeProduitPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ligneCommandeProduitPK != null ? ligneCommandeProduitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommandeProduit)) {
            return false;
        }
        LigneCommandeProduit other = (LigneCommandeProduit) object;
        if ((this.ligneCommandeProduitPK == null && other.ligneCommandeProduitPK != null) || (this.ligneCommandeProduitPK != null && !this.ligneCommandeProduitPK.equals(other.ligneCommandeProduitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.a00n.entities.LigneCommandeProduit[ ligneCommandeProduitPK=" + ligneCommandeProduitPK + " ]";
    }

}
