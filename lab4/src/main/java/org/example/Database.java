package org.example;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class Database {
    SessionFactory factory;
    public Database()
    {
        try
        {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addMage(Mage mage)
    {
        Transaction transaction = null;
        try (Session session = factory.openSession())
        {
            transaction = session.beginTransaction();
            session.save(mage);
            transaction.commit();
            System.out.println("Added mage: " + mage.getName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void addTower(Tower tower)
    {
        Transaction transaction = null;
        try (Session session = factory.openSession())
        {
            transaction = session.beginTransaction();
            session.save(tower);
            transaction.commit();
            System.out.println("Added tower: " + tower.getName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Mage getMage(String name)
    {
        try (Session session = factory.openSession())
        {
            return session.get(Mage.class, name);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Tower getTower(String name)
    {
        try (Session session = factory.openSession())
        {
            return session.get(Tower.class, name);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteMage(String name)
    {
        Transaction transaction = null;
        try (Session session = factory.openSession())
        {
            transaction = session.beginTransaction();
            Mage mage = session.get(Mage.class, name);
            session.delete(mage);
            transaction.commit();
            System.out.println("Deleted mage: " + name);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTower(String name)
    {
        Transaction transaction = null;
    }

    public void dumpDatabase()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Mage> mages = session.createQuery("FROM org.example.Mage", Mage.class).getResultList();
        List<Tower> towers = session.createQuery("FROM org.example.Tower", Tower.class).getResultList();
        session.getTransaction().commit();
        session.close();
        System.out.println("Mages:");
        for (Mage mage : mages)
        {
            System.out.println(mage.getName() + " " + mage.getLevel());
        }
        System.out.println("Towers:");
        for (Tower tower : towers) {
            System.out.println(tower.getName() + " " + tower.getHeight());
        }
    }

    public List<Mage> getMagesWithHigherLevel(int level)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Mage> mages = session.createQuery("FROM org.example.Mage WHERE level > " + level, Mage.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return mages;
    }

    public List<Tower> getTowersWithHigherHeight(int height)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Tower> towers = session.createQuery("FROM org.example.Tower WHERE height > " + height, Tower.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return towers;
    }

    public List<Mage> getMagesWithHigherLevelFromTower(String towerName, int level)
    {
        try (Session session = factory.openSession())
        {
            session.beginTransaction();
            Query<Mage> query = session.createQuery("FROM org.example.Mage WHERE tower.name = :towerName AND level > :level", Mage.class);
            query.setParameter("towerName", towerName);
            query.setParameter("level", level);
            session.getTransaction().commit();
            return query.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
