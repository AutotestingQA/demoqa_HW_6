package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static pages.RegistrationPage.getRandomGender;
import static pages.RegistrationPage.getRandomInt;

public class PageObjectsTestsRandomData extends TestBase {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = getRandomGender();
    String phone = String.valueOf(getRandomInt(1000000000, 1999999999));
    String streetAddress = faker.address().streetAddress();


    RegistrationPage registrationPage = new RegistrationPage();

    //Позитивный тест
    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .setDateOfBirth("30", "July", "2008")
                .setSubjectsInput("Math")
                .setHobbiesInput("Sports")
                .setUploadPicture("img/1.png")
                .setCurrentAddress(streetAddress)
                .setStateCity("NCR", "Delhi")
                .clickSubmitButton()
                .checkModal()
                .checkResultFields("Student Name", firstName + " " + lastName)
                .checkResultFields("Student Email", email)
                .checkResultFields("Mobile", phone);
    }

    //Тест заполнение обязательных полей
    @Test
    void minimalDataTest() {
        registrationPage.openPage()
        .setFirstName(firstName)
        .setLastName(lastName)
        .setGender(gender)
        .setUserNumber(phone)
        .setDateOfBirth("01", "July", "1990")
        .clickSubmitButton()
        .checkModal()
        .checkResultFields("Student Name", firstName + " " + lastName)
        .checkResultFields("Mobile", phone);
    }

    //Негативный тест
    @Test
    void negativeTest() {
        registrationPage.openPage()
                .clickSubmitButton()
                .checkModalNegative();
    }
}