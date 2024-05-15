package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Form>> getAll() {
        return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты", service.getAll()));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Form>> save(@RequestBody Form form) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Form>(true, "Добавлена новая анкета", service.save(form)));
    }

    @GetMapping
    // getById method
    public ResponseEntity<DataResponse<Form>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Form>(true, "Найдена анкета", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Form>(false, "Анкета не найдена"));
        }
    }

    @GetMapping("all-spec")
    // getById method
    public ResponseEntity<ListResponse<Form>> getBySpec(@RequestParam String spec) {
        if (!service.findBySpec(spec).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Найдена анкеты по категории", service.findBySpec(spec)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета не найдена", null));
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не удалена"));
        }
    }

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
