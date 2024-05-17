package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Form;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.FormService;
import ru.harmony.serverboot.service.VacancyService;

@RequestMapping("/harmony/form")
@AllArgsConstructor
@RestController
public class FormController {
    private final FormService service;

    // Get all Form
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Form>> getAll() {
        return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты", service.getAll()));
    }

    // Get form by formHeader and SpecName
    @GetMapping("/all-headerSpec")
    public ResponseEntity<ListResponse<Form>> getByHeaderAndSpec(@RequestParam String header, String spec) {
        if (!service.findByHeaderAndSpec(header, spec).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты по заголовку и специализации { " + header + ", " + spec + "}", service.findByHeaderAndSpec(header, spec)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета по заголовку и специализации {" + header + ", " + spec + "} не найдена", null));
        }
    }

    // Get Form by AspirantName
    @GetMapping("/all-aspName")
    public ResponseEntity<ListResponse<Form>> getByAspName(@RequestParam String surname,@RequestParam String name, @RequestParam String lastname) {
        if (!service.findByAspName(surname, name, lastname).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты по ФИО {" + surname +", " + name + "," + lastname +"}", service.findByAspName(surname, name, lastname)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета по ФИО {" + surname +", " + name + "," + lastname + "} не найдена", null));
        }
    }

    // Get Form by Aspirant date of birth
    @GetMapping("/all-aspDateBirth")
    public ResponseEntity<ListResponse<Form>> getByAspBirthDate(@RequestParam String date) {
        if (!service.findByAspBirth(date).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты по дате рождения {" + date +"}", service.findByAspBirth(date)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкеты по дате рождения {" + date +"}\" не найдены", null));
        }
    }

    // Get all Form by AspirantName filtered
    @GetMapping("/all-surname")
    public ResponseEntity<ListResponse<Form>> getAllBySurname() {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты", service.findByFilterSurname()));
    }

    // Save new Form
    @PostMapping
    public ResponseEntity<DataResponse<Form>> save(@RequestBody Form form) {
        return ResponseEntity.ok(new DataResponse<Form>(true, "Добавлена новая анкета", service.save(form)));
    }

    // Get Form by Id
    @GetMapping
    public ResponseEntity<DataResponse<Form>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Form>(true, "Найдена анкета", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Form>(false, "Анкета не найдена"));
        }
    }

    // Get all Form by SpecName
    @GetMapping("all-spec")
    public ResponseEntity<ListResponse<Form>> getBySpec(@RequestParam String spec) {
        if (!service.findBySpec(spec).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Найдена анкеты по категории {" + spec + "}", service.findBySpec(spec)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета по категории {" + spec + "}не найдена", null));
        }
    }

    // Delete form by AspirantName
    @DeleteMapping("del-aspname")
    @Transactional
    public ResponseEntity<BaseResponse> deleteByAspName(@RequestParam String name, @RequestParam String surname, @RequestParam String lastname) {
        if (!service.findByAspName(surname, name, lastname).isEmpty()) {
            service.deleteByAspName(surname, name, lastname);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета по ФИО {" + surname +", " + name + "," + lastname + "} не найдена и не удалена"));
        }
    }

    // Delete Form by FormHeader and SpecName
    @DeleteMapping("all-headerSpec")
    @Transactional
    public ResponseEntity<BaseResponse> deleteByHeaderSpec(@RequestParam String header, String spec) {
        if (!service.findByHeaderAndSpec(header, spec).isEmpty()) {
            service.deleteByHeaderSpec(header, spec);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не удалена"));
        }
    }

    // Delete Form by Id
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не удалена"));
        }
    }

    // Update Form
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Form form) {
        if (service.findById(form.getId()).isPresent()) {
            service.update(form);
            return ResponseEntity.ok(new BaseResponse(true,"Анкета была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не обновлена"));
        }
    }
}
