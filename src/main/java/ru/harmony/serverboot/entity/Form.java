package ru.harmony.serverboot.entity;

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
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formHeader;
    private String aspSpec;

    @Pattern(regexp = "[А-Я][а-я]{1,20}")
    private String aspName;

    private String aspSkills;
    private String workExperience;
    private String education;
    private String workBefore;

    @Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(\\d{4})$")
    private String aspBirthDate;

    // place for relationship "vacancy"
    @NotNull
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
