package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    SelenideElement userName = $("#userName-value"),
            link = $("[href='/profile?book=9781449325862']"),
            booksList = $(".rt-tbody");

    public String name = "111",
            nameBook = "Git Pocket Guide";


    public ProfilePage checkUserName(String value){
        userName.shouldHave(text(value));
        return this;
    }

    public ProfilePage checkVisibleLinkBook() {
        link.shouldBe(visible);
        return this;
    }

    public void checkBookListHaveBook(String value) {
        booksList.shouldBe(visible).shouldHave(text(value));
    }

    public ProfilePage bookMissing(String value) {
        booksList.shouldNotHave(text(value));
        return this;
    }

    public void linkForBookMissing() {
        link.shouldHave(hidden);
    }
}
