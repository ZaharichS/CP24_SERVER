package ru.harmony.serverboot.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Spec;
import ru.harmony.serverboot.repo.SpecRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpecService {
    private final SpecRepo repo;

    public List<Spec> getAll() {
        return repo.findAll();
    }

    // find specialization by id
    public Optional<Spec> findById(Long id) {
        return repo.findById(id);
    }

    public Spec save(Spec data) {
        return repo.save(data);
    }

    public void update(Spec data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
