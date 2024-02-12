package user.getUser;

import dto.UserDTO;
import dto.UserGetResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.Specification;
import services.UserCreateApi;
import services.UserGetApi;

public class GetUser_Test {
//получить пользователя по имени проверить ответ и боди (что он соответствует переданным при создании значениям)
@Test
public void getUserAllColumnsTest(){
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
  userCreateApi.createUser(user).statusCode(HttpStatus.SC_OK);

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
  }
}
