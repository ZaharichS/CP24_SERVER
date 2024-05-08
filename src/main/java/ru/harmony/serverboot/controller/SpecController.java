package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Spec;
import ru.harmony.serverboot.entity.Worker;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.SpecService;

@RequestMapping("/harmony/spec")
@AllArgsConstructor
@RestController
public class SpecController {
    private final SpecService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Spec>> getAll() {
        return ResponseEntity.ok(new ListResponse<Spec>(true, "Специализации", service.getAll()));
    }

    // place for getById method, if it needs

    @PostMapping
    public ResponseEntity<DataResponse<Spec>> save(@RequestBody Spec spec) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Spec>(true, "Добавлена новая категория специализаций", service.save(spec)));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        // maybe try catch block
        // for check id in request body ? or not

        service.delete(id);
        return ResponseEntity.ok(new BaseResponse(true,"Категория была удален"));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Spec spec) {
        // maybe try catch block
        // for check id in request body ? or not
        // and send message if it's true

        service.update(spec);
        return ResponseEntity.ok(new BaseResponse(true,"Данные категории были обновлены"));
    }
}
