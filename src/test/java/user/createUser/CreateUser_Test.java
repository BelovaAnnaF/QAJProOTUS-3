package user.createUser;

import dto.UserDTO;
import dto.UserCreateResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.Specification;
import services.UserCreateApi;

public class CreateUser_Test {
//Создать нового пользователя со всеми заполненными полями проверить код ответа и body ответа
  @Test
  public void createUserAllColumnsTest(){
  UserCreateApi userCreateApi = new UserCreateApi();
  Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOk200());
  UserDTO user = UserDTO.builder()
          .id(1)
          .email("jjj@kkk.ru")
          .phone("5555555555")
          .firstName("Petr")
          .lastName("Kolov")
          .password("123456")
          .username("PetrKolov123")
          .userStatus(201)
          .build();
  UserCreateResponseDTO userResponseDTO = userCreateApi.createUser(user).extract().body().as(UserCreateResponseDTO.class);

  Assertions.assertEquals(200, userResponseDTO.getCode());
  Assertions.assertEquals("unknown", userResponseDTO.getType());
  Assertions.assertNotNull(userResponseDTO.getMessage());
}

//Создать нового пользователя с частично заполненными полями проверить код ответа и body ответа
  @Test
  public void createUserNameColumnsTest(){
    UserCreateApi userCreateApi = new UserCreateApi();
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOk200());
    UserDTO user = UserDTO.builder()
            .username("PetrKolov")
            .build();
    UserCreateResponseDTO userResponseDTO = userCreateApi.createUser(user).extract().body().as(UserCreateResponseDTO.class);

    Assertions.assertEquals(200, userResponseDTO.getCode());
    Assertions.assertEquals("unknown", userResponseDTO.getType());
    Assertions.assertNotNull(userResponseDTO.getMessage());
  }
}
