package ru.ifmo.se.person;

import java.util.ArrayList;

public interface InteractionWithGroup {
    ArrayList<Person> getList();

    String addPerson(Person p);

    String removePerson(Person p);

    boolean isAnyone();

    boolean isNotBored();

    String setStateForEach(double chance);

}
