package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Worker;
import ru.harmony.serverboot.repo.WorkerRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkerService {
    private final WorkerRepo repo;

    public List<Worker> getAll() {
        return repo.findAll();
    }

    public List<Worker> getAllByAccessIn() {
        return repo.findByAccessIn();
    }

    public List<Worker> getAllByAccessOut() {
        return repo.findByAccessOut();
    }

    public List<Worker> getAllRecruter() {
        return repo.findByRecruter();
    }

    public List<Worker> getAllBySurnameFilter() {
        return repo.findAllByOrderBySurName();
    }


    // find Worker by id
    public Optional<Worker> findById(Long id) {
        return repo.findById(id);
    }


    public Worker save(Worker data) {
        return repo.save(data);
    }

    public void update(Worker data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Worker> findByAccess(Long id) {
        return repo.findWorkerByAccess_Id(id);
    }

    public List<Worker> findByName(String name) {
        return repo.findByName(name);
    }
}
