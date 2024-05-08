package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Form;
import ru.harmony.serverboot.repo.FormRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class FormService {
    private final FormRepo repo;

    public List<Form> getAll() {
        return repo.findAll();
    }

    // place for findById

    public Form save(Form data) {
        return repo.save(data);
    }

    public void update(Form data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
