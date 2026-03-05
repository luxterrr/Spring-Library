package com.kaizen.Library.DTOS;

import com.kaizen.Library.domains.user.Status;

public record UserDTO(String name, Status status) {
}
