package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.harmony.serverboot.entity.Spec;

public interface SpecRepo extends JpaRepository<Spec, Long> {
}
