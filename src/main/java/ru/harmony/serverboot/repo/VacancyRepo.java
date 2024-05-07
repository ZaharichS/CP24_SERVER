package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.harmony.serverboot.entity.Vacancy;

public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
}
