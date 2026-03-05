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
    private StatusUser statusUser;
    private boolean onLoan;

    //@Column(unique = true)
    private String email;

    public User(UserDTO data) {
        this.name = data.name();
        this.statusUser= StatusUser.ACTIVE;
        this.email = data.email();
    }
}
