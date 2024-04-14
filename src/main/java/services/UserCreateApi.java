package services;

import static io.restassured.RestAssured.given;

import dto.UserDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserCreateApi {
  private static final String ADD_USER_PATH = "/user";
  public ValidatableResponse createUser(UserDTO userDTO) {

    return given()
            .log().all()
            .body(userDTO)
            .when()
            .post(ADD_USER_PATH)
            .then()
            .log().all();
  }

}
