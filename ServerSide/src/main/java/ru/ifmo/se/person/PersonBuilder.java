package ru.ifmo.se.person;

import ru.ifmo.se.enums.State;

import java.util.List;

public class PersonBuilder implements Builder {
    private Person person;

    @Override
    public void reset() {
        person = new Person();
    }

    @Override
    public void setName(String name) {
        person.setName(name);
    }

    @Override
    public void setLastname(String lastname) {
        person.setLastname(lastname);
    }

    @Override
    public void setAge(int age) {
        person.setAge(age);
    }

    @Override
    public void setSteps_from_door(int steps) {
        person.setSteps_from_door(steps);
    }

    @Override
    public void setState(State state) {
        if (state == null)
            person.generateState();
        else
            person.setState(state);
    }

    @Override
    public void setGeneralClothes(List<GeneralClothes> generalClothes) {
        generalClothes.forEach(clothes -> person.addGeneralClothes(clothes, person.getGeneralClothes()));
    }

    @Override
    public void setShoes(List<Shoes> shoes) {
        shoes.forEach(shoe -> person.addShoes(shoe, person.getShoes()));
    }

    @Override
    public void setAccessories(List<Accessories> accessories) {
        accessories.forEach(acc -> person.addAccessories(acc, person.getAccessories()));
    }

    public Person getPerson() {
        return person;
    }
}
