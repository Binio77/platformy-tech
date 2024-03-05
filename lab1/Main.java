package lab1;

import java.util.Arrays;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean sort = false;
        boolean isSortedZad5 = false;
        MageComparator comparator = null;
        if(args.length > 0) {
            sort = args[0].equals("s");
            if(args.length > 1) {
                comparator = args[1].equals("a") ? new MageComparator() : null;
                if(args.length > 2) {
                    isSortedZad5 = args[2].equals("s");
                }
            }
        }

        Mage mage1 = new Mage("Gandalf", 20, 100,sort, comparator);
        Mage mage2 = new Mage("Saruman", 10, 50,sort, comparator);
        Mage mage3 = new Mage("Radagast", 5, 25,sort, comparator);
        Mage mage4 = new Mage("Galadriel", 30, 200,sort, comparator);
        Mage mage5 = new Mage("Sauron", 50, 500,sort, comparator);


        mage5.addApprentice(mage3);
        //mage5.addApprentice(mage4);
        //mage4.addApprentice(mage3);
        mage3.addApprentice(mage2);
        mage2.addApprentice(mage1);

        Mage[] mages = {mage1, mage2, mage3, mage4, mage5};
        /*
        for(Mage mage : mages) {
            System.out.println(mage);
        }
        */

        Map<Mage, Integer> map = mage5.zad5(isSortedZad5);
        for (Map.Entry<Mage, Integer> entry : map.entrySet()) {
            System.out.println("Mage: " + entry.getKey().getName() + ", descentants: " + entry.getValue());
        }
    }
}