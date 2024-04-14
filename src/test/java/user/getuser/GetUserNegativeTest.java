package user.getuser;

import dto.UserResponseDTO;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.Specification;
import services.UserGetApi;

public class GetUserNegativeTest {

  @Test
  //("Пытаемся получить несуществующего пользователя по имени, проверяем, что ответ 404 и в теле ответа сообщение 'User not found'")
  public void getUnCreateUserName(){
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecError404());
    UserGetApi userGetApi = new UserGetApi();

    UserResponseDTO userResponseDTO = userGetApi.getUseInfo("GAGAGAGA").extract().body().as(UserResponseDTO.class);

    Assertions.assertEquals(1, userResponseDTO.getCode());
    Assertions.assertEquals("error", userResponseDTO.getType());
    Assertions.assertEquals("User not found", userResponseDTO.getMessage());
  }

}
