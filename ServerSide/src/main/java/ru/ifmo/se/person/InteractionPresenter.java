package ru.ifmo.se.person;

import java.util.Set;

public class InteractionPresenter {
    private Conversation conversation;
    private StringBuilder stringBuilder;

    public String randomConversation() {
        conversation = new Conversation();
        stringBuilder = new StringBuilder();
        PersonBuilder personBuilder = new PersonBuilder();
        Director.createSimplePerson(personBuilder);
        int amount = (int) (Math.random() * 100);
        for (int i = 0; i < amount; i++) {
            Director.createSimplePerson(personBuilder);
            stringBuilder.append(conversation.addPerson(personBuilder.getPerson())).append("\n");
        }
        conversation.getPeople().forEach(person -> stringBuilder.append(conversation.randomAction(person)).append("\n"));
        return stringBuilder.toString();
    }

    public String conversationWithCollection(Set<Person> collec) {
        conversation = new Conversation();
        stringBuilder = new StringBuilder();
        collec.forEach(person -> stringBuilder.append(conversation.addPerson(person)).append("\n"));
        collec.forEach(person -> stringBuilder.append(conversation.randomAction(person)).append("\n"));
        return stringBuilder.toString();
    }
}
