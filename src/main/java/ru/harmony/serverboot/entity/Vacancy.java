package ru.harmony.serverboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

}
