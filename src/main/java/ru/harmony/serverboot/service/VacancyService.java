package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.repo.VacancyRepo;

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
