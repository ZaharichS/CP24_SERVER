package ru.harmony.serverboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.harmony.serverboot.entity.Form;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.repo.FormRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormService {
    private final FormRepo repo;

    public List<Form> getAll() {
        return repo.findAll();
    }

    // find Form by id
    public Optional<Form> findById(Long id) {
        return repo.findById(id);
    }

    // find Form by spec
    public List<Form> findBySpec(String spec) {
        return repo.findByVacancySpecName(spec);
    }

    // find  Form by aspname
    public List<Form> findByAspName(String surname, String name, String lastname) {
        return repo.findBySurNameAndNameAndLastName(surname, name, lastname);
    }

    // find  Form by form header and spec
    public List<Form> findByHeaderAndSpec(String header, String spec) {
        return repo.findByFormHeaderAndVacancySpecName(header, spec);
    }


    // find  Form by AspName but filter
    public List<Form> findByFilterSurname() {
        return repo.findAllByOrderBySurName();
    }

    // find  Form by AspBirthDate
    public List<Form> findByAspBirth(String birth) {
        return repo.findByAspBirthDate(birth);
    }

    public Form save(Form data) {
        return repo.save(data);
    }

    public void update(Form data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // delete Form by aspname
    public Integer deleteByAspName(String name, String surname, String lastname) {
        return repo.deleteBySurNameAndNameAndLastName(name, surname, lastname);
    }

    // delete Form by form header and spec
    public Integer deleteByHeaderSpec(String header, String spec) {
        return repo.deleteByFormHeaderAndVacancySpecName(header, spec);
    }
}
