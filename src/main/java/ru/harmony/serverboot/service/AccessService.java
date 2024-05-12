package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.repo.AccessRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccessService {
    private final AccessRepo repo;

    public List<Access> getAll() {
        return repo.findAll();
    }

    // find access by id
    public Optional<Access> findById(Long id) {
        return repo.findById(id);
    }

    public Access save(Access data) {
        return repo.save(data);
    }

    // "update method"
    public void update(Access data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
