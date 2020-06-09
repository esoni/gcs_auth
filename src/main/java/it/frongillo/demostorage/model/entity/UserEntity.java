package it.frongillo.demostorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="users")
public class UserEntity {

    @Id
    private String username;

    @Column(nullable=false, length=50)
    private String fullName;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private long userId;
}
