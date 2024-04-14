package services;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserDeleteApi {
  private static final String DELETE_USER_PATH = "/user/{username}";
  public ValidatableResponse deleteUseInfo(String username) {

    return given()
            .log().all()
            .pathParam("username", username)
            .when()
            .delete(DELETE_USER_PATH)
            .then().log().all();
  }
}
