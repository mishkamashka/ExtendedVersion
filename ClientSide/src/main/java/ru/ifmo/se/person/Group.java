package ru.ifmo.se.person;

import ru.ifmo.se.enums.State;
import java.util.ArrayList;

public class Group implements InteractionWithGroup{
    private ArrayList<Person> people = new ArrayList<>();

    public ArrayList<Person> getList(){
        return this.people;
    }
    public String addPerson(Person p){
        this.people.add(p);
        return p.toString() + " enters." + p.description();
        }
    public String removePerson(Person p){
        this.people.remove(p);
        return p.toString() + " leaves.";
    }
    public boolean isAnyone(){
        return !this.people.isEmpty();
    }

    public boolean isNotBored(){
        boolean f = false;
        int i = 0;
        while (i<this.people.size() && !f){
            Person a = this.people.get(i);
            f = a.getState() != State.BORED;
        }
        return f;
    }
    public String setStateForEach(double chance){
        StringBuilder string = new StringBuilder();
        for (Person g: this.people){
            double chanceC = chance + Math.random();
            if (chanceC < 0.5) {
                g.setState(State.BORED);
                string.append(g.toString()).append(" is bored.");
            }
            else
                if (chanceC > 0.5 && chanceC < 1) {
                    g.setState(State.ANGRY);
                    string.append(g.toString()).append(" is angry.");
                }
                else
                    if (chanceC > 1 && chanceC < 1.5) {
                        g.setState(State.INTERESTED);
                        string.append(g.toString()).append(" is interested.");
                    }
                    else
                        string.append(g.toString()).append(" doesn't care.");
        }
        return string.toString();
    }
}