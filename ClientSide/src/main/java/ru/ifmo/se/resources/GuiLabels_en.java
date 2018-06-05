package ru.ifmo.se.resources;

import java.util.ListResourceBundle;

public class GuiLabels_en extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][]{{"checkBoxN", "Neutral"}, {"checkBoxA", "Angry"}, {"checkBoxI", "Interested"},
                {"checkBoxB", "Bored"}, {"ageLabel","Minimum age:"}, {"distLabel","Maximum distance(int):"},
                {"label","Object to add/Remove objects greater than:"}, {"addButton","Add object"},
                {"remButton","Remove greater objects"},{"startButton","Start"}, {"stopButton","Stop"},
                {"title","Lab7. ClientSide"}, {"people", "People"}, {"menu", "Menu"},
                {"load","Load collection from server"},{"save","Save current collection on server"},
                {"clear","Clear current collection"}};
    }
}