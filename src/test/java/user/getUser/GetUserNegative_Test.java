package user.getUser;

import dto.UserResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.Specification;
import services.UserGetApi;

public class GetUserNegative_Test {
//Пытаемся получить несуществующего пользователя по имени, проверяем, что ответ 404 и в теле ответа сообщение "User not found"
  @Test
  public void getUnCreateUserName(){
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecError404());
    UserGetApi userGetApi = new UserGetApi();

    UserResponseDTO userResponseDTO = userGetApi.getUseInfo("GAGAGAGA").extract().body().as(UserResponseDTO.class);

    Assertions.assertEquals(1, userResponseDTO.getCode());
    Assertions.assertEquals("error", userResponseDTO.getType());
    Assertions.assertEquals("User not found", userResponseDTO.getMessage());
  }

}
