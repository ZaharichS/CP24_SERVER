package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.harmony.serverboot.entity.Form;
import ru.harmony.serverboot.entity.Vacancy;

import java.util.List;

public interface FormRepo extends JpaRepository<Form, Long> {
//    @Query("SELECT f FROM Form f JOIN f.vacancy v JOIN v.spec s WHERE s.name = :spec")
//    List<Form> findBySpec(@Param("spec") String spec);
    List<Form> findByVacancySpecName(String spec);

    List<Form> findAllByOrderByAspName();

    List<Form> findByAspName(String aspName);
    Integer deleteByAspName(String aspName);

    List<Form> findByFormHeaderAndVacancySpecName(String name, String spec);
    Integer deleteByFormHeaderAndVacancySpecName(String name, String spec);

    List<Form> findByAspBirthDate(String aspBirth);
}
