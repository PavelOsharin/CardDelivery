package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.$;


public class CardDeliveryTest {
    private String generateDate(int addDay, String pattern) {
        return LocalDate.now().plusDays(addDay).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shouldTestСardDelivery() {
        Selenide.open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        String planDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planDate);
        $("[data-test-id='name'] input").setValue("Тестов Тест Тестович");
        $("[data-test-id='phone'] input").setValue("+79991234567");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__title").shouldBe(Condition.text("Успешно!")).shouldBe(Condition.visible);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);

    }

}