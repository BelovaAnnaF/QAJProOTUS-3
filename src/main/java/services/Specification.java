package services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
  private static final String BASE_URL = "https://petstore.swagger.io/v2";
  public static RequestSpecification requestSpec(){
    return new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .build();
  }

  public static ResponseSpecification responseSpecOk200(){
    return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
  }
  public static ResponseSpecification responseSpecError404(){
    return new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();
  }
  public static void installSpecification(RequestSpecification request, ResponseSpecification response){
    RestAssured.requestSpecification = request;
    RestAssured.responseSpecification = response;
  }
}
