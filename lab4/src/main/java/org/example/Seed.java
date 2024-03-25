package org.example;
import java.util.List;
public class Seed {
    Database db;
    public Seed(Database db) {
        this.db = db;
        //getSingleMage();
        //getSingleTower();
        addTowerWithMage();
        //deleteSingleMage();
        //deleteSingleTower();
        //getSingleMage();
        //getSingleTower();
/*
        List<Mage> mages = db.getMagesWithHigherLevel(4);
        for (Mage mage : mages)
        {
            System.out.println(mage.getName());
        }

        List<Tower> towers = db.getTowersWithHigherHeight(2);
        for (Tower tower : towers)
        {
            System.out.println(tower.getName());
        }

*/
        List<Mage> mages = db.getMagesWithHigherLevelFromTower("Orthanc", 4);
        for (Mage mage : mages) {
            System.out.println(mage.getName());
        }


        //dumpDatabase();

    }
    public void addTowerWithMage()
    {
        Tower tower = new Tower("Orthanc", 100);
        Mage mage1 = new Mage("a", 3, tower);
        Mage mage2 = new Mage("b", 4, tower);
        Mage mage3 = new Mage("c", 5, tower);
        tower.addMage(mage1);
        tower.addMage(mage2);
        tower.addMage(mage3);
        db.addTower(tower);
    }
    public void getSingleMage()
    {
        Mage mage = db.getMage("Gandalf");
        System.out.println(mage.getName());
    }
    public void getSingleTower()
    {
        Tower tower = db.getTower("wieza");
        System.out.println(tower.getName());
    }
    public void deleteSingleMage()
    {
        db.deleteMage("Gandalf");
    }
    public void deleteSingleTower()
    {
        db.deleteTower("wieza");
    }
    public void dumpDatabase()
    {
        db.dumpDatabase();
    }
}

