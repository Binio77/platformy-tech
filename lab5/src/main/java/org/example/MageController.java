package org.example;

import java.util.Optional;
public class MageController {
    private MageRepository mageRepository;

    public MageController(MageRepository mageRepository) {
        this.mageRepository = mageRepository;
    }

    public String find(String name){
        Optional<Mage> mage = mageRepository.find(name);
        if(mage.isPresent())
        {
            return mage.get().toString();
        }
        return "not found";
    }

    public String delete(String name)
    {
        try
        {
            mageRepository.delete(name);
            return "done";
        }
        catch(IllegalArgumentException e)
        {
            return "not found";
        }
    }

    public String save(String name, int level)
    {
        try
        {
            mageRepository.save(new Mage(name, level));
            return "done";
        }
        catch(IllegalArgumentException e)
        {
            return ("not found");
        }
    }
}
