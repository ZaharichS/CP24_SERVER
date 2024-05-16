package ru.harmony.serverboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.harmony.serverboot.entity.Form;
import ru.harmony.serverboot.entity.Worker;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface WorkerRepo extends JpaRepository<Worker, Long> {
    List<Worker> findWorkerByAccess_Id(Long id);

    @Query("SELECT w FROM Worker w JOIN w.access a WHERE a.name IN ('Рекрутер', 'Администратор')")
    List<Worker> findByAccessIn();

    @Query("SELECT w FROM Worker w JOIN w.access a WHERE a.name = 'Гость' ")
    List<Worker> findByAccessOut();

    @Query("SELECT w FROM Worker w WHERE w.position='Рекрутер' ")
    List<Worker> findByRecruter();

    //@Query("SELECT w FROM Worker WHERE w.")
    List<Worker> findByWorkerName(String name);

    List<Worker> findAllByOrderByWorkerName();
}
