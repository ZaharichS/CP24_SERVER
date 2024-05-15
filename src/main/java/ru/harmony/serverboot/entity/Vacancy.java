package ru.harmony.serverboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[А-Я][а-я]{1,20}")
    private String name;
    private String fromEmployer;
    private String wage;
    private String workExperience;
    private String headcount;


    // place for relationship "spec"
    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Spec spec;

    @JsonIgnore
    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL)
    private List<Form> form;

    @Override
    public String toString() {
        return fromEmployer;
    }
}
