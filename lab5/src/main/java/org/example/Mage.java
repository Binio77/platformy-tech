package org.example;

public class Mage {
    private String name;
    private int level;

    public Mage(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel()
    {
        return level;
    }

    @Override
    public String toString()
    {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof Mage))
        {
            return false;
        }
        Mage mage = (Mage) obj;
        return name.equals(mage.name) && level == mage.level;
    }

}



