package dto;

import lombok.Data;

@Data
public class UserCreateResponseDTO {
  private Integer code;
  private String type;
  private String message;
}
