package dto;

import java.util.List;

public record UserResponseDTO(String username, List<String> role){
}
