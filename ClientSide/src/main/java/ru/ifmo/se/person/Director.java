package ru.ifmo.se.person;

import java.util.LinkedList;
import java.util.List;

public class Director {
    public static void createSimplePerson(PersonBuilder builder){
        builder.reset();
        builder.setName("Andy");
        builder.setLastname("Green");
        builder.setAge((int) (Math.random()*100));
        builder.setState(null);
        builder.setSteps_from_door((int) (Math.random()*100));
    }

    public static void createFullPerson(PersonBuilder builder){
        createSimplePerson(builder);
        builder.setAccessories(generateAccessories());
        builder.setGeneralClothes(generateGeneralClothes());
        builder.setShoes(generateShoes());
    }

    public static void createPersonCertainName(PersonBuilder builder, String name, String lastname){
        builder.reset();
        builder.setName(name);
        builder.setLastname(lastname);
        builder.setAge((int) (Math.random()*100));
        builder.setState(null);
        builder.setSteps_from_door((int) (Math.random()*100));
        builder.setAccessories(generateAccessories());
        builder.setGeneralClothes(generateGeneralClothes());
        builder.setShoes(generateShoes());
    }

    private static List<Accessories> generateAccessories(){
        List<Accessories> acc = new LinkedList<>();
        int amount = (int) (Math.random()*100);
        for(int i = 0; i < amount; i++){
            if (i%2 == 0)
                acc.add(new Hat());
            else
                acc.add(new Glasses());
        }
        return acc;
    }

    private static List<GeneralClothes> generateGeneralClothes(){
        List<GeneralClothes> clothes = new LinkedList<>();
        int amount = (int) (Math.random()*100);
        for(int i = 0; i < amount; i++){
            if (i%3 == 0)
                clothes.add(new Jacket());
            else
                if (i%3 == 1)
                    clothes.add(new Jeans());
                else
                    if (i%3 == 2)
                        clothes.add(new Shirt());
                    else
                        clothes.add(new Trousers());
        }
        return clothes;
    }

    private static List<Shoes> generateShoes(){
        List<Shoes> shoes = new LinkedList<>();
        int amount = (int) (Math.random()*100);
        for(int i = 0; i < amount; i++){
            if (i%2 == 0)
                shoes.add(new Boots());
            else
                shoes.add(new Trainers());
        }
        return shoes;
    }
}
