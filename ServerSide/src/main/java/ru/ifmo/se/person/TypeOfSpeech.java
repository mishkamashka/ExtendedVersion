package ru.ifmo.se.person;

public interface TypeOfSpeech {
    String sayHello(Person a, Person b);
    String sayBye(Person a, Person b);
    String tellStory(Person speaker);
    String tellJoke(Person speaker);
    String argue(Person a, Person b);
    String discuss(Person a, Person b, String thing);
    String talk(Person a, Person b);
    String sayPhrase(Person p, String s);
}