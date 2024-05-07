package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.harmony.serverboot.entity.Worker;

public interface WorkerRepo extends JpaRepository<Worker, Long> {
}
