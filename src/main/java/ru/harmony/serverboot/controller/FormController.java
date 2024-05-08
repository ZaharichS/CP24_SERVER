package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        // maybe try catch block
        // for check id in request body ? or not

        service.delete(id);
        return ResponseEntity.ok(new BaseResponse(true,"Анкета удалена"));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Form form) {
        // maybe try catch block
        // for check id in request body ? or not
        // and send message if it's true

        service.update(form);
        return ResponseEntity.ok(new BaseResponse(true,"Анкета была обновлена"));
    }

}
