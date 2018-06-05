package ru.ifmo.se;

public class Main {
    public static void main(String[] args) {
        new Thread(Server.getInstance()).start();
        MainPanel mainPanel = new MainPanel();
    }
}
