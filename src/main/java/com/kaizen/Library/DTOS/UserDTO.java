package com.kaizen.Library.DTOS;

import com.kaizen.Library.domains.user.StatusUser;

public record UserDTO(String name, StatusUser statusUser, String email) {
}
