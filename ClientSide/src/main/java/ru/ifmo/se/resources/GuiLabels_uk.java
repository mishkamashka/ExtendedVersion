package ru.ifmo.se.resources;

import java.util.ListResourceBundle;

public class GuiLabels_uk extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][]{{"checkBoxN", "Нейтральний"}, {"checkBoxA", "Злий"}, {"checkBoxI", "Зацікавлений"},
                {"checkBoxB", "Скучающий"}, {"ageLabel", "Мінімальний вік:"},
                {"distLabel", "Максимальна роздільна рассстояніе (int):"}, {"label", "Додати об'єкт / Видалити об'єкти, які більше ніж:"},
                {"addButton", "Додати об'єкт"}, {"remButton", "Видалити об'єкти"}, {"startButton", "Старт"},
                {"stopButton", "Стоп"}, {"title", "Лаб7. Клієнт"}, {"menu", "Меню"},
                {"load", "Завантажити колекцію з сервера"}, {"save", "Зберегти колекцію на сервері"},
                {"clear", "Очистити поточну колекцію"}};
    }
}
