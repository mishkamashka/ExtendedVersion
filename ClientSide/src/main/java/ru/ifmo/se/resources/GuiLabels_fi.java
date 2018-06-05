package ru.ifmo.se.resources;

import java.util.ListResourceBundle;

public class GuiLabels_fi extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][]{{"checkBoxN","Neutraali"}, {"checkBoxA","Suuttunut"}, {"checkBoxI","Kiinnostunut"},
                {"checkBoxB","Kyllästynyt"}, {"ageLabel","Vähimmäisikä:"}, {"distLabel","Suurin etäisyys(int):"},
                {"label","Lisätään kohta/Poista yli:"}, {"addButton","Lisää objekti"},
                {"remButton","Poista suurempia esineitä"},{"startButton","Alkaa"}, {"stopButton","Stop"},
                {"title","Lab7. Asiakas"}, {"people", "Ihmiset"}, {"menu", "Valikko"},
                {"load","Lataa kokoelma palvelimelta"},{"save","Tallenna nykyinen kokoelma palvelimelle"},
                {"clear","Tyhjennä nykyinen kokoelma"}};
    }
}
