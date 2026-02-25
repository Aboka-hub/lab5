package com.bcnd.laboratories5.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email", columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=60)
    private String username;

    @Column(nullable=false, length=120)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private Integer age;
}
