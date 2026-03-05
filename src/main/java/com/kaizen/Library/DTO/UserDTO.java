package com.kaizen.Library.DTO;

import com.kaizen.Library.domains.user.Status;

public record UserDTO(String name, Status status) {
}
