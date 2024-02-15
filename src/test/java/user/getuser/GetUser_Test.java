package user.getuser;

import dto.UserDTO;
import dto.UserGetResponseDTO;
import dto.UserResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.Specification;
import services.UserCreateApi;
import services.UserDeleteApi;
import services.UserGetApi;

public class GetUser_Test {
  private Integer id = 1;
  private String username = "PetrKolov123";
  private String firstName = "Petr";
  private String lastName = "Kolov";
  private String email = "jjj@kkk.ru";
  private String password ="123456";
  private String phone = "5555555555";
  private Integer userStatus = 201;
  //получить пользователя по имени проверить ответ и боди (что он соответствует переданным при создании значениям)
  @Test
  public void getUserAllColumnsTest(){
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
    userCreateApi.createUser(user);

    UserGetApi userGetApi = new UserGetApi();

    UserGetResponseDTO getUser = userGetApi.getUseInfo("PetrKolov123")
          .extract()
          .body()
          .as(UserGetResponseDTO.class);

    Assertions.assertEquals(201, getUser.getUserStatus());
    Assertions.assertEquals("PetrKolov123", getUser.getUsername());
    Assertions.assertEquals(1, getUser.getId());
    Assertions.assertEquals("jjj@kkk.ru", getUser.getEmail());
    Assertions.assertEquals("Petr", getUser.getFirstName());
    Assertions.assertEquals("Kolov", getUser.getLastName());

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
}
