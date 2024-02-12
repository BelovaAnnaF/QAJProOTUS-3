package services;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;

public class UserGetApi {
  private static final String GET_USER_PATH = "/user/{username}";

  public ValidatableResponse getUseInfo(String username) {

    return given()
            .log().all()
            .pathParam("username", username)
            .when()
            .get(GET_USER_PATH)
            .then().log().all();

  }
}
