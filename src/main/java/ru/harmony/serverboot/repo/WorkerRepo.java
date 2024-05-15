package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.harmony.serverboot.entity.Worker;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface WorkerRepo extends JpaRepository<Worker, Long> {
    List<Worker> findWorkerByAccess_Id(Long id);
}
