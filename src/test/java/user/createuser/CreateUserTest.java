package user.createuser;

import dto.UserDTO;
import dto.UserGetResponseDTO;
import dto.UserResponseDTO;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.Specification;
import services.UserCreateApi;
import services.UserDeleteApi;
import services.UserGetApi;

public class CreateUserTest {

  private Integer id = 1;
  private String username = "PetrKolov123";
  private String firstName = "Petr";
  private String lastName = "Kolov";
  private String email = "jjj@kkk.ru";
  private String password ="123456";
  private String phone = "5555555555";
  private Integer userStatus = 201;

  @Test
  //("Создать нового пользователя со всеми заполненными полями проверить код ответа и body ответа")
  public void createUserAllColumnsTest(){
    UserCreateApi userCreateApi = new UserCreateApi();
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOk200());
    UserDTO user = UserDTO.builder()
          .id(id)
          .email(email)
          .phone(phone)
          .firstName(firstName)
          .lastName(lastName)
          .password(password)
          .username(username)
          .userStatus(userStatus)
          .build();
    UserResponseDTO userResponseDTO = userCreateApi.createUser(user).extract().body().as(UserResponseDTO.class);

    Assertions.assertEquals(200, userResponseDTO.getCode());
    Assertions.assertEquals("unknown", userResponseDTO.getType());
    Assertions.assertNotNull(userResponseDTO.getMessage());

    //проверяем, что пользователь точно создан и возвращается по имени
    UserGetApi userGetApi = new UserGetApi();

    UserGetResponseDTO getUser = userGetApi.getUseInfo("PetrKolov123")
            .extract()
            .body()
            .as(UserGetResponseDTO.class);

    Assertions.assertEquals(userStatus, getUser.getUserStatus());
    Assertions.assertEquals(username, getUser.getUsername());
    Assertions.assertEquals(id, getUser.getId());
    Assertions.assertEquals(email, getUser.getEmail());
    Assertions.assertEquals(firstName, getUser.getFirstName());
    Assertions.assertEquals(lastName, getUser.getLastName());
    Assertions.assertEquals(phone, getUser.getPhone());
    Assertions.assertEquals(password, getUser.getPassword());

    //удаляем созданного пользователя
    UserDeleteApi userDeleteApi = new UserDeleteApi();
    UserResponseDTO deleteUser = userDeleteApi.deleteUseInfo("PetrKolov123")
            .extract()
            .body()
            .as(UserResponseDTO.class);

    Assertions.assertEquals(200, deleteUser.getCode());
    Assertions.assertEquals("unknown", deleteUser.getType());
    Assertions.assertEquals("PetrKolov123", deleteUser.getMessage());

    //проверяем, что точно удален
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecError404());

    UserGetApi userAfterDeleteGetApi = new UserGetApi();
    UserResponseDTO userAfterDeleteGetResponseDTO = userAfterDeleteGetApi.getUseInfo("PetrKolov123").extract().body().as(UserResponseDTO.class);

    Assertions.assertEquals(1, userAfterDeleteGetResponseDTO.getCode());
    Assertions.assertEquals("error", userAfterDeleteGetResponseDTO.getType());
    Assertions.assertEquals("User not found", userAfterDeleteGetResponseDTO.getMessage());
  }


  @Test
  //("Создать нового пользователя с частично заполненными полями проверить код ответа и body ответа")
  public void createUserIdNameColumnsTest(){
    UserCreateApi userCreateApi = new UserCreateApi();
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOk200());
    UserDTO user = UserDTO.builder()
            .id(15)
            .username("PetrKolov")
            .build();
    UserResponseDTO userResponseDTO = userCreateApi.createUser(user).extract().body().as(UserResponseDTO.class);

    Assertions.assertEquals(200, userResponseDTO.getCode());
    Assertions.assertEquals("unknown", userResponseDTO.getType());
    Assertions.assertNotNull(userResponseDTO.getMessage());

    //проверяем, что пользователь точно создан и возвращается по имени
    UserGetApi userGetApi = new UserGetApi();

    UserGetResponseDTO getUser = userGetApi.getUseInfo("PetrKolov")
            .extract()
            .body()
            .as(UserGetResponseDTO.class);

    Assertions.assertEquals("PetrKolov", getUser.getUsername());
    Assertions.assertEquals(15, getUser.getId());

    //удаляем созданного пользователя
    UserDeleteApi userDeleteApi = new UserDeleteApi();
    UserResponseDTO deleteUser = userDeleteApi.deleteUseInfo("PetrKolov")
            .extract()
            .body()
            .as(UserResponseDTO.class);

    Assertions.assertEquals(200, deleteUser.getCode());
    Assertions.assertEquals("unknown", deleteUser.getType());
    Assertions.assertEquals("PetrKolov", deleteUser.getMessage());

    //проверяем, что точно удален
    Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecError404());

    UserGetApi userAfterDeleteGetApi = new UserGetApi();
    UserResponseDTO userAfterDeleteGetResponseDTO = userAfterDeleteGetApi.getUseInfo("PetrKolov").extract().body().as(UserResponseDTO.class);

    Assertions.assertEquals(1, userAfterDeleteGetResponseDTO.getCode());
    Assertions.assertEquals("error", userAfterDeleteGetResponseDTO.getType());
    Assertions.assertEquals("User not found", userAfterDeleteGetResponseDTO.getMessage());
  }
}
