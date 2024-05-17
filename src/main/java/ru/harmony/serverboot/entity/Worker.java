package ru.harmony.serverboot.entity;

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
@Table(name ="worker")
@Schema(description = "Сотрудник")
public class Worker {
    @Schema(name = "Уникальный идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Имя")
    private String name;
    @Schema(name = "Фамилия")
    private String surName;
    @Schema(name = "Отчество")
    private String lastName;

    @Schema(name = "Должность")
    private String position;

    @Schema(name = "Роль доступа")
    // place for relationship "access"
    @ManyToOne
    @JoinColumn(name = "access_id")
    private Access access;
}
