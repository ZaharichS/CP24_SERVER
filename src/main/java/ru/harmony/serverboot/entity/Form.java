package ru.harmony.serverboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private String spec;
    private String aspName;
    private String aspSkills;
    private String workExperience;
    private String education;
    private String workBefore;
    private String aspBirthDate;

    // place for relationship "vacancy"
    @NotNull
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
