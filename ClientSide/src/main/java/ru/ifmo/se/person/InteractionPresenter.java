package ru.ifmo.se.person;

import java.util.Set;

public class InteractionPresenter {
    Conversation conversation;

    public void randomConversation() {
        conversation = new Conversation();
        PersonBuilder personBuilder = new PersonBuilder();
        Director.createSimplePerson(personBuilder);
        int amount = (int) (Math.random() * 100);
        for (int i = 0; i < amount; i++) {
            Director.createSimplePerson(personBuilder);
            conversation.addPerson(personBuilder.getPerson());
        }
        conversation.getPeople().forEach(person -> conversation.randomAction(person));
    }

    public void conversationWithCollection(Set<Person> collec) {
        conversation = new Conversation();
        collec.forEach(person -> conversation.addPerson(person));
        collec.forEach(person -> conversation.randomAction(person));
    }
}
