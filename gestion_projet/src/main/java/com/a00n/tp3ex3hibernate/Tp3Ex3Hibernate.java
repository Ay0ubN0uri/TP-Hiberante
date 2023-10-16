/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.a00n.tp3ex3hibernate;

import com.a00n.entities.EmployeTache;
import com.a00n.entities.EmployeTachePK;
import com.a00n.entities.Employee;
import com.a00n.entities.Projet;
import com.a00n.entities.Tache;
import com.a00n.service.EmployeTacheService;
import com.a00n.service.EmployeeService;
import com.a00n.service.ProjetService;
import com.a00n.service.TacheService;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ay0ub
 */
public class Tp3Ex3Hibernate {

    public static void main(String[] args) {
        try {
            EmployeeService es = new EmployeeService();
            TacheService ts = new TacheService();
            ProjetService ps = new ProjetService();
            EmployeTacheService ets = new EmployeTacheService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Projet p = new Projet("projet 1", new Date(), dateFormat.parse("2023-10-23"));
            Tache t = new Tache("tache1", new Date(), dateFormat.parse("2023-10-11"), 19119.9, p);
            Tache t2 = new Tache("tache2", dateFormat.parse("2023-3-11"), dateFormat.parse("2023-5-11"), 199.9, p);
            Employee e = new Employee("ayoub", "nouri", 10092);
            es.create(e);
            p.setEmployee(e);
            ps.create(p);
            ts.create(t);
            ts.create(t2);
            EmployeTachePK etpk = new EmployeTachePK(t.getId(), e.getId());
            EmployeTache et = new EmployeTache(etpk, dateFormat.parse("2023-1-10"), dateFormat.parse("2023-2-10"));
            ets.create(et);
//            System.out.println("======================= Tasks Realized By Employee ======================");
//            es.getTasksRealizedByEmployee(e.getId()).forEach(tache -> System.out.println(tache.getNom()));
//            System.out.println("======================= Projects Managed By Employee ======================");
//            es.getProjectsManagedByEmployee(e.getId()).forEach(projet -> System.out.println(projet));
//            ps.getPlannedTasksForProject(p).forEach(tache -> System.out.println(tache.getNom()));
//            ps.getCompletedTasksInProject(p);
//            ts.findPriceGreaterThen1000();
            ps.displayRealizedTasksBetweenDates(dateFormat.parse("2023-1-1"), dateFormat.parse("2023-11-10"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
