package api;

import io.qameta.allure.Step;
import models.LoginRequest;
import models.LoginResponse;

import static io.restassured.RestAssured.given;
import static specs.TestSpecs.requestSpec;
import static specs.TestSpecs.responseSpec;

public class Autorization {
    @Step("Authorization")
    public static LoginResponse login (LoginRequest loginRequest){
        return given(requestSpec)
                .body(loginRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponse.class);
    }

//    public Response getAuth(){
//        LoginRequestModel loginRequest = new LoginRequestModel();
//        loginRequest.setUserName("111");
//        loginRequest.setPassword("QWErty1!");
//        return given(requestSpec)
//                .body(loginRequest)
//                .when()
//                .post("/Account/v1/Login")
//                .then()
//                .statusCode(200)
//                .extract().response();
//    }
}
