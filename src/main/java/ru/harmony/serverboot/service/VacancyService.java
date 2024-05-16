package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.repo.VacancyRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VacancyService {
    private final VacancyRepo repo;

    public List<Vacancy> getAll() {
        return repo.findAll();
    }

    // find Vacancy by id
    public Optional<Vacancy> findById(Long id) {
        return repo.findById(id);
    }

    // find Vacancy by employee
    public List<String> getAllByEmployee() {
        return repo.findByEmployee();
    }

    // find Vacancy by workExperience
    public List<Vacancy> getAllByWorkExp(Long workExp) {
        return repo.findByWorkExperience(String.valueOf(workExp));
    }

    // find Vacancy by workExperience
    public List<Vacancy> getAllByWage(String wage) {
        return repo.findByWage(wage);
    }

    // find Vacancy by name and spec_name
    public List<Vacancy> findByNameSpecAndSpecName(String name, String spec_name) {
        return repo.findByNameAndSpec_Name(name, spec_name);
    }

    // delete Vacancy by name and spec_name
    public Integer deleteByNameAndSpecName(String name, String spec_name) {
        return repo.deleteByNameAndSpec_Name(name, spec_name);
    }

    public Vacancy save(Vacancy data) {
        return repo.save(data);
    }

    public void update(Vacancy data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
