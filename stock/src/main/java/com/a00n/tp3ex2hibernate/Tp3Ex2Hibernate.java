/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.a00n.tp3ex2hibernate;

import com.a00n.entities.Categorie;
import com.a00n.entities.Produit;
import com.a00n.service.CategorieService;
import com.a00n.service.ProduitService;
import java.util.Date;

/**
 *
 * @author ay0ub
 */
public class Tp3Ex2Hibernate {

    public static void main(String[] args) {
        try {
            ProduitService ps = new ProduitService();
            CategorieService cs = new CategorieService();
            ps.findGreaterThen100();
            ps.getProductsByCommand(1);
            ps.getProductsOrderedBetweenDates(new Date(), new Date(2023, 11, 10)).forEach(p -> System.out.println(p));
//            Categorie c1 = new Categorie("asdf", "ffff");
//            cs.create(c1);
//            cs.findAll().forEach(cat -> System.out.println(cat));
//            Produit p1 = new Produit("asdf", 1000.2, c1);
//            ps.create(p1);
//            ps.findAll().forEach(produit -> System.out.println(produit));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
