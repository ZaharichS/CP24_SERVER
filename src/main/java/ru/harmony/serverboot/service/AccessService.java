package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.repo.AccessRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class AccessService {
    private final AccessRepo repo;

    public List<Access> getAll() {
        return repo.findAll();
    }
}
