package ru.ifmo.se.resources;

import java.util.ListResourceBundle;

public class GuiLabels_ru_RU extends ListResourceBundle{
    protected Object[][] getContents() {
        return new Object[][]{{"checkBoxN", "Нейтральный"}, {"checkBoxA", "Злой"}, {"checkBoxI", "Заинтересованный"},
                {"checkBoxB", "Скучающий"}, {"ageLabel", "Минимальный возраст:"}, {"distLabel", "Максимальное рассстояние(int):"},
                {"label", "Добавить объект/Удалить объекты, которые больше чем:"}, {"addButton", "Добавить объект"},
                {"remButton", "Удалить объекты"}, {"startButton", "Старт"}, {"stopButton", "Стоп"}, {"title", "Лаб7. Клиент"},
                {"people", "Люди"},{"menu", "Меню"}, {"load","Загрузить коллекцию с сервера"},
                {"save","Сохранить коллекцию на сервере"}, {"clear","Очистить текущую коллекцию"}};
    }
}
