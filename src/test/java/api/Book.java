package api;

import models.AddBook;
import models.DeleteBook;
import models.LoginResponse;
import tests.TestData;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.TestSpecs.requestSpec;
import static specs.TestSpecs.responseSpec;

public class Book {
    TestData data = new TestData();

    public void deleteAllBooks(LoginResponse loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }

    public void addBook(LoginResponse loginResponse, AddBook addBookRequest, String isbn1) {
        AddBook.IsbnModel isbn = new AddBook.IsbnModel(isbn1);
        List<AddBook.IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbn);

        addBookRequest.setUserId(loginResponse.getUserId());
        addBookRequest.setCollectionOfIsbns(isbnList);

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(addBookRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }

    public void deleteBook(LoginResponse loginResponse, String isbn) {
        DeleteBook deleteBook = new DeleteBook();
        deleteBook.setUserId(loginResponse.getUserId());
        deleteBook.setIsbn(data.isbn);

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }
}
