package lab1;

import java.util.Comparator;
public class MageComparator implements Comparator<Mage>{
    @Override
    public int compare(Mage mage1, Mage mage2) {
        if(mage1.getLevel() != mage2.getLevel()) {
            return Integer.compare(mage1.getLevel(), mage2.getLevel());
        } else if (mage1.getPower() != mage2.getPower()) {
            return Double.compare(mage1.getPower(), mage2.getPower());
        }
        else {
            return mage1.getName().compareTo(mage2.getName());
        }
    }
}
