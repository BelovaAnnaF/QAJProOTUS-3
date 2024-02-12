package services;

import dto.UserDTO;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

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
