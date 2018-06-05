package ru.ifmo.se.person;

import ru.ifmo.se.enums.State;
import java.util.List;

public interface Builder {
    void reset();
    void setName(String name);
    void setLastname(String lastname);
    void setAge(int age);
    void setSteps_from_door(int steps);
    void setState(State state);
    void setGeneralClothes(List<GeneralClothes> generalClothes);
    void setShoes(List<Shoes> shoes);
    void setAccessories(List<Accessories> accessories);
}
