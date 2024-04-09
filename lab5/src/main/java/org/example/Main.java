package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Mage mage1 = new Mage("Radagast", 1);
        MageRepository mageRepository = new MageRepository();
        MageController mageController = new MageController(mageRepository);
        mageController.save("Radagast", 1);
        System.out.println(mageController.save("Radagast", 1));
    }
}