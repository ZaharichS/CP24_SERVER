package ru.harmony.serverboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="vacancy")
@Schema(description = "Вакансия")
public class Vacancy {
    @Schema(name = "Уникальный идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Заголовок вакансии")
    private String name;
    @Schema(name = "Компания")
    private String fromEmployer;
    @Schema(name = "Заботная плата")
    private String wage;
    @Schema(name = "Требуемый опыт работы")
    private String workExperience;
    @Schema(name = "Количество сотрудников")
    private String headcount;


    @Schema(name = "Специализация")
    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Spec spec;

    @Schema(name = "Анкеты")
    @JsonIgnore
    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL)
    private List<Form> form;
}
