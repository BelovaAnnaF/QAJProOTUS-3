package dto;

import lombok.Data;

@Data
public class UserResponseDTO {
  private Integer code;
  private String type;
  private String message;
}
