package services;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserGetApi {
  private static final String GET_USER_PATH = "/user/{username}";

  public ValidatableResponse getUser(String username) {

    return given()
            .log().all()
            .pathParam("username", username)
            .when()
            .get(GET_USER_PATH)
            .then().log().all();

  }
}
