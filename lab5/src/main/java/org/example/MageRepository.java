package org.example;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Optional;
public class MageRepository {
    private final Collection<Mage> mages = new ArrayList<>();

    public void delete(String name) throws IllegalArgumentException
    {
        for(Mage mage : mages)
        {
            if(mage.getName().equals(name))
            {
                mages.remove(mage);
                return;
            }
        }
        throw new IllegalArgumentException("Mage" + name + " not found");
    }

    public Optional<Mage> find(String name)
    {
        for(Mage mage : mages)
        {
            if(mage.getName().equals(name))
            {
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }

    public void save(Mage mage)
    {
        if(!find(mage.getName()).isEmpty())
        {
            throw new IllegalArgumentException("Mage " + mage.getName() + " already exists");
        }
        mages.add(mage);
    }

}

