package com.kaizen.Library.domains.user;

import com.kaizen.Library.DTOS.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Status status;

    public User(UserDTO data) {
        this.name = data.name();
        this.status = data.status();
    }
}
