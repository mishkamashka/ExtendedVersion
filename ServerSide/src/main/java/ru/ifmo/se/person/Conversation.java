package ru.ifmo.se.person; //TODO: сделать вывод тексотв при интеракшенах не саутом, а через возврат строк.

import ru.ifmo.se.enums.State;

import java.util.ArrayList;

public class Conversation implements TypeOfSpeech {
    private InteractionWithGroup people = new Group();

    public String sayHello(Person a, Person b) {
        return (Message.saying(a) + " Hello, " + b.toString() + "!");
    }

    public String sayBye(Person a, Person b) {
        return (Message.saying(a) + " Bye, " + b.toString() + "!");
    }

    public String tellStory(Person speaker) {
        String string = "";
        if (this.people.isNotBored())
            string = string + Message.saying(speaker) + "I'm gonna tell you a story.\n" + Message.tellingStory(speaker) + "\n";
        return string + this.people.setStateForEach(Math.random());
    }

    public String tellJoke(Person speaker) {
        return Message.saying(speaker) + "I'm gonna tell you a joke.\n" + Message.tellingJoke(speaker) + "\n" +
                this.people.setStateForEach(Math.random() + 0.1);
    }

    public String argue(Person a, Person b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (a.getState() == State.ANGRY && b.getState() == State.ANGRY) {
            stringBuilder.append(Message.arguing(a, b));
        }
        if (a.getState() != State.ANGRY && b.getState() == State.ANGRY)
            stringBuilder.append(Message.saying(b)).append(" Let's argue.\n").append(
                    Message.saying(a)).append(" I don't wanna argue.");
        if (b.getState() != State.ANGRY && a.getState() == State.ANGRY)
            stringBuilder.append(Message.saying(a)).append(" Let's argue.\n").append(
                    Message.saying(b)).append(" I don't wanna argue.");
        a.setState(State.NEUTRAL);
        b.setState(State.NEUTRAL);
        return stringBuilder.toString();
    }

    public String discuss(Person a, Person b, String thing) {
        return Message.discussing(a, b) + thing + ".\n" + this.people.setStateForEach(Math.random());
    }

    public String talk(Person a, Person b) {
        return Message.talking(a, b) + "\n" + this.people.setStateForEach(Math.random());
    }

    public String sayPhrase(Person p, String s) {
        return (Message.saying(p) + s);
    }

    public String addPerson(Person p) {
        StringBuilder stringBuilder = new StringBuilder();
        if (p == null)
            return "";
        stringBuilder.append(this.people.addPerson(p));
        p.setState(State.NEUTRAL);
        ArrayList<Person> group = this.people.getList();
        if (!(group.size() - 1 == 0)) {
            for (Person g : group) {
                if (g.hashCode() != p.hashCode())
                    stringBuilder.append(this.sayHello(g, p));
            }
        }
        return stringBuilder.toString();
    }

    public String removePerson(Person p) {
        StringBuilder stringBuilder = new StringBuilder();
        if (p == null)
            return "";
        stringBuilder.append(this.people.removePerson(p));
        ArrayList<Person> group = this.people.getList();
        if (!group.isEmpty()) {
            for (Person g : group) {
                stringBuilder.append(this.sayBye(g, p));
            }
        }
        return stringBuilder.toString();
    }

    public String showParticipants() {
        StringBuilder stringBuilder = new StringBuilder("At the moment taking part in conversation:\n");
        ArrayList<Person> a = this.people.getList();
        a.forEach(person -> stringBuilder.append(person.toString()));
        return stringBuilder.toString();
    }

    public long countPeopleWithState(State state) {
        return (this.people.getList().stream().filter((person) -> person.getState().equals(state)).count());
    }

    public String randomAction(Person p){
        if (p == null)
            return "";
        switch ((int) (Math.random()*10)) {
            case 0:
            case 1:
                return tellStory(p);
            case 2:
            case 3:
                return sayPhrase(p, "Hey guys, wanna some beer?");
            case 4:
            case 5:
                return tellJoke(p);
            case 6:
            case 7:
                return argue(p, getPeople().get(0));
            case 8:
            case 9:
                return discuss(p, getPeople().get(getPeople().size() - 1), "something");
            default:
                return talk(p, getPeople().get(getPeople().size() - 1));
        }
    }

    public ArrayList<Person> getPeople() {
        return people.getList();
    }
}
