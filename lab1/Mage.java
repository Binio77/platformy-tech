package lab1;

import java.util.*;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power, boolean isSorted, MageComparator comparator) {
        this.name = name;
        this.level = level;
        this.power = power;
        if(isSorted){
            if(comparator == null){
                this.apprentices = new TreeSet<>();
            }
            else{
                this.apprentices = new TreeSet<>(comparator);
            }
        }
        else{
            this.apprentices = new HashSet<>();
        }
    }

    public void addApprentice(Mage mage) {
        apprentices.add(mage);
    }

    public void removeApprentice(Mage mage) {
        apprentices.remove(mage);
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mage mage)) return false;

        return name.equals(mage.name) && level == mage.level && Double.compare(mage.power, power) == 0 && apprentices.equals(mage.apprentices);
    }

    @Override
    public final int hashCode() {
        return name.hashCode() + level + (int) power + apprentices.hashCode();
    }

    @Override
    public int compareTo(Mage mage) {
        if (!this.name.equals(mage.name)) {
            return this.name.compareTo(mage.name);
        } else if (this.level != mage.level) {
            return Integer.compare(this.level, mage.level);
        } else {
            return Double.compare(this.power, mage.power);
        }
    }

    @Override
    public String toString() {
        StringBuilder mage_string = new StringBuilder();
        mage_string.append("Mage{name='").append(name).append("'," +
                " level=").append(level).append(", power=").append(power).append('}');
        if(apprentices.isEmpty()){
            return mage_string.toString();
        }
        mage_string.append("\n");
        for(Mage apprentice : apprentices){
            mage_string.append("-").append(apprentice.toString().replace("\n", "\n-")).append("\n");
        }
        if(mage_string.charAt(mage_string.length() - 1) == '\n'){
            mage_string.deleteCharAt(mage_string.length() - 1);
        }
        return mage_string.toString();
    }


    public Map<Mage, Integer> zad5(Boolean sorting) {
        Map<Mage, Integer> map;
        Set<Mage> visited = new HashSet<Mage>();
        if (sorting) {
            map = new TreeMap<Mage, Integer>();
        } else {
            map = new HashMap<Mage, Integer>();
        }
        descentants(this, map, visited);
        return map;
    }
    private int descentants(Mage mage, Map<Mage, Integer> map, Set<Mage> visited) {
        int count = 0;
        if (visited.contains(mage)) {
            return 0;
        }

        visited.add(mage);

        for (Mage apprentice : mage.apprentices) {
            count += 1 + descentants(apprentice, map, visited);
        }

        map.put(mage, count);
        return count;
    }





}
