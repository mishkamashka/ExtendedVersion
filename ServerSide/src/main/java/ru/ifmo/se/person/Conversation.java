package ru.ifmo.se.person;

import ru.ifmo.se.enums.State;

import java.util.ArrayList;

public class Conversation implements TypeOfSpeech {
    private InteractionWithGroup people = new Group();

    public void sayHello(Person a, Person b) {
        System.out.println(Message.saying(a) + " Hello, " + b.toString() + "!");
    }

    public void sayBye(Person a, Person b) {
        System.out.println(Message.saying(a) + " Bye, " + b.toString() + "!");
    }

    public void tellStory(Person speaker) {
        if (this.people.isNotBored())
            System.out.println(Message.saying(speaker) + "I'm gonna tell you a story.\n" + Message.tellingStory(speaker));
        this.people.setStateForEach(Math.random());
    }

    public void tellJoke(Person speaker) {
        System.out.println(Message.saying(speaker) + "I'm gonna tell you a joke.\n" + Message.tellingJoke(speaker));
        this.people.setStateForEach(Math.random() + 0.1);
    }

    public void argue(Person a, Person b) {
        if (a.getState() == State.ANGRY && b.getState() == State.ANGRY) {
            System.out.println(Message.arguing(a, b));
        }
        if (a.getState() != State.ANGRY && b.getState() == State.ANGRY)
            System.out.println(Message.saying(b) + " Let's argue.\n"
                    + Message.saying(a) + " I don't wanna argue.");
        if (b.getState() != State.ANGRY && a.getState() == State.ANGRY)
            System.out.println(Message.saying(a) + " Let's argue.\n"
                    + Message.saying(b) + " I don't wanna argue.");
        a.setState(State.NEUTRAL);
        b.setState(State.NEUTRAL);
    }

    public void makeDo(Person a, Person b, String verb) {
        System.out.println(Message.makingDo(a, b) + verb + ".");
        if (Math.random() < 0.5)
            b.setState(State.ANGRY);
    }

    public void discuss(Person a, Person b, String thing) {
        System.out.println(Message.discussing(a, b) + thing + ".");
        this.people.setStateForEach(Math.random());
    }

    public void talk(Person a, Person b) {
        System.out.println(Message.talking(a, b));
        this.people.setStateForEach(Math.random());
    }

    public void sayPhrase(Person p, String s) {
        System.out.println(Message.saying(p) + s);
    }

    public void addPerson(Person p) {
        if (p == null)
            return;
        this.people.addPerson(p);
        p.setState(State.NEUTRAL);
        ArrayList<Person> group = this.people.getList();
        if (!(group.size() - 1 == 0)) {
            for (Person g : group) {
                if (g.hashCode() != p.hashCode())
                    this.sayHello(g, p);
            }
        }
    }

    public void removePerson(Person p) {
        if (p == null)
            return;
        this.people.removePerson(p);
        ArrayList<Person> group = this.people.getList();
        if (!group.isEmpty()) {
            for (Person g : group) {
                this.sayBye(g, p);
            }
        }
    }

    public void showParticipants() {
        ArrayList<Person> a = this.people.getList();
        System.out.println("At the moment taking part in conversation:");
        a.forEach(System.out::println);
    }

    public void countPeopleWithState(State state) {
        ArrayList<Person> a = this.people.getList();
        System.out.print("Amount of people who are " + state + ": ");
        System.out.println(a.stream().filter((person) -> person.getState().equals(state)).count());
    }

    public void randomAction(Person p){
        if (p == null)
            return;
        switch ((int) (Math.random()*10)) {
            case 0:
            case 1:
                tellStory(p);
                break;
            case 2:
            case 3:
                sayPhrase(p, "Hey guys, wanna some beer?");
            case 4:
            case 5:
                tellJoke(p);
                break;
            case 6:
            case 7:
                argue(p, getPeople().get(0));
                break;
            case 8:
            case 9:
                discuss(p, getPeople().get(getPeople().size() - 1), "something");
                break;
            default:
                talk(p, getPeople().get(getPeople().size() - 1));
        }
    }

    public ArrayList<Person> getPeople() {
        return people.getList();
    }
}
