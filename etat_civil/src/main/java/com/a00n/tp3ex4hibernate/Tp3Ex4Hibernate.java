package com.a00n.tp3ex4hibernate;

import com.a00n.entities.Femme;
import com.a00n.entities.Homme;
import com.a00n.entities.Marriage;
import com.a00n.entities.MarriagePK;
import com.a00n.service.FemmeService;
import com.a00n.service.HommeService;
import com.a00n.service.MarriageService;
import com.a00n.utils.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ay0ub
 */
public class Tp3Ex4Hibernate {
    
    public static void main(String[] args) {
        try {
            HommeService hs = new HommeService();
            FemmeService fs = new FemmeService();
            MarriageService ms = new MarriageService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Homme h = new Homme("ayoub", "asdf", "asdf", "asdf", new Date());
            Homme h2 = new Homme("ayoub", "asdf", "asdf", "asdf", new Date());
            Femme f = new Femme("kali", "asdf", "asdf", "asdfafd", new Date());
            Femme f2 = new Femme("kali2", "asdf", "asdf", "asdfafd", new Date());
            Femme f3 = new Femme("kali2", "asdf", "asdf", "asdfafd", new Date());
            Femme f4 = new Femme("kali2", "asdf", "asdf", "asdfafd", new Date());
            hs.create(h);
            hs.create(h2);
            fs.create(f);
            fs.create(f2);
            fs.create(f3);
            fs.create(f4);
            MarriagePK marriagePK = new MarriagePK(h.getId(), f.getId());
            MarriagePK marriagePK2 = new MarriagePK(h2.getId(), f.getId());
            MarriagePK marriagePK3 = new MarriagePK(h2.getId(), f2.getId());
            MarriagePK marriagePK4 = new MarriagePK(h2.getId(), f3.getId());
            MarriagePK marriagePK5 = new MarriagePK(h2.getId(), f4.getId());
            Marriage m1 = new Marriage(marriagePK, 2);
            Marriage m2 = new Marriage(marriagePK2, 4);
            Marriage m3 = new Marriage(marriagePK3, 4);
            Marriage m4 = new Marriage(marriagePK4, 4);
            Marriage m5 = new Marriage(marriagePK5, 4);
            ms.create(m1);
            ms.create(m2);
            ms.create(m3);
            ms.create(m4);
            ms.create(m5);
            ms.displayMarriagesDetails(h2);
//            System.out.println(hs.getHommesMarriedToFourFemmesBetweenDates(dateFormat.parse("2023-1-1"), dateFormat.parse("2025-1-1")));
//            fs.getFemmesMarriedMultipleTimes().forEach(femme -> System.out.println(femme));
//            hs.getEpousesOfHommeBetweenDates(h, dateFormat.parse("2024-1-1"), dateFormat.parse("2025-1-1"))
//                    .forEach(e -> System.out.println(e));
//
//            System.out.println(fs.getNumberOfChildrenBetweenDates(f, dateFormat.parse("2023-1-1"), dateFormat.parse("2025-1-1")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
