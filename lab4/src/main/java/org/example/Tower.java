package org.example;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Tower
{
    @Id
    private String name;

    private int height;

    Tower()
    {
    }
    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Mage> mages;

    public Tower(String name, int height)
    {
        this.name = name;
        this.height = height;
        mages = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public int getHeight()
    {
        return height;
    }

    public void addMage(Mage mage)
    {
        mages.add(mage);
    }

    public void to_string()
    {
        System.out.println("Tower: " + name + " height: " + height);
        for (Mage mage : mages)
        {
            System.out.println(mage);
        }
    }

    public List<Mage> getMages()
    {
        return mages;
    }

}
