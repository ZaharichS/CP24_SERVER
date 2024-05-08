package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Spec;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.repo.VacancyRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class VacancyService {
    private final VacancyRepo repo;

    public List<Vacancy> getAll() {
        return repo.findAll();
    }

    // place for findById

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
