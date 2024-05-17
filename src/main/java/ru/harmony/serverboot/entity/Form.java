package ru.harmony.serverboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="form")
@Schema(description = "Анкета")
public class Form {
    @Schema(name = "Уникальный идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Заголовок анкета")
    private String formHeader;
    @Schema(name = "Специальность")
    private String aspSpec;

    @Schema(name = "Имя")
    private String name;
    @Schema(name = "Фамилия")
    private String surName;
    @Schema(name = "Отчество")
    private String lastName;

    @Schema(name = "Навыки")
    private String aspSkills;
    @Schema(name = "Опыт работы")
    private String workExperience;
    @Schema(name = "Образование")
    private String education;
    @Schema(name = "Прошлое место работы")
    private String workBefore;

    @Schema(name = "Дата рождения")
    @Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(\\d{4})$")
    private String aspBirthDate;

    @Schema(name = "Вакансия для соискателя")
    // place for relationship "vacancy"
    @NotNull
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
