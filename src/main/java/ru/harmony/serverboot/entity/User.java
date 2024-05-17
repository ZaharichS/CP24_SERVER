package ru.harmony.serverboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="user")
@Schema(description = "Пользователь")
public class User {
    @Schema(name = "Уникальный идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Логин")
    private String login;
    @Schema(name = "Пароль")
    private String password;

    @Schema(name = "Пользователь принадлежит")
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
