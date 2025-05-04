package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithPageObjectsTests extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();

    //Позитивный тест
    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Stan")
                .setLastName("Ignatov")
                .setEmail("stan@ignatov.com")
                .setGender("Other")
                .setUserNumber("1234567890")
                .setDateOfBirth("30", "July", "2008")
                .setSubjectsInput("Math")
                .setHobbiesInput("Sports")
                .setUploadPicture("img/1.png")
                .setCurrentAddress("Some address 1")
                .setStateCity("NCR", "Delhi")
                .clickSubmitButton()
                .checkModal()
                .checkResultFields("Student Name", "Stan Ignatov")
                .checkResultFields("Student Email", "stan@ignatov.com")
                .checkResultFields("Mobile", "1234567890");
    }

    //Тест заполнение обязательных полей
    @Test
    void minimalDataTest() {
        registrationPage.openPage()
        .setFirstName("Stan")
        .setLastName("Ignatov")
        .setGender("Male")
        .setUserNumber("1234567890")
        .setDateOfBirth("01", "July", "1990")
        .clickSubmitButton()
        .checkModal()
        .checkResultFields("Student Name", "Stan Ignatov")
        .checkResultFields("Mobile", "1234567890");
    }

    //Негативный тест
    @Test
    void negativeTest() {
        registrationPage.openPage()
                .clickSubmitButton()
                .checkModalNegative();
    }
}