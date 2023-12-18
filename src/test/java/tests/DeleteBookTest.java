package tests;

import api.Autorization;
import api.Book;
import extentions.WithLogin;
import models.AddBook;
import models.LoginResponse;
import org.junit.jupiter.api.Test;
import page.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static tests.TestData.isbn;
import static tests.TestData.loginRequest;

public class DeleteBookTest extends TestBase{
    Autorization autorization = new Autorization();
    Book book = new Book();
    ProfilePage profile = new ProfilePage();
    @Test
    @WithLogin
    void deleteBook(){
        LoginResponse loginResponse = autorization.login(loginRequest);


        step("Очистка корзины профиля", () ->
                book.deleteAllBooks(loginResponse));

        step("Добавление книги в профиль", () ->
                book.addBook(loginResponse, new AddBook(), isbn));

        step("Книга отображается в профиле", () -> {
            open("/profile");
            profile.checkUserName(profile.name)
                    .checkVisibleLinkBook()
                    .checkBookListHaveBook(profile.nameBook);
        });

        step("Удаление книги из профиля", () ->
            book.deleteBook(loginResponse, isbn)
        );

        step("Книга не отображается в профиле", () -> {
            open("/profile");
            profile.checkUserName(profile.name)
                    .bookMissing(profile.nameBook)
                    .linkForBookMissing();
        });
    }
}
