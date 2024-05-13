package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
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
        return ResponseEntity.ok(new ListResponse<Spec>(true, "Категории", service.getAll()));
    }

    @GetMapping
    // getById method
    public ResponseEntity<DataResponse<Spec>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Spec>(true, "Найдена категория", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Spec>(false, "Категория не найдена"));
        }
    }

    @PostMapping
    public ResponseEntity<DataResponse<Spec>> save(@RequestBody Spec spec) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Spec>(true, "Добавлена новая категория специализаций", service.save(spec)));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        // maybe try catch block
        // for check id in request body ? or not
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Категория была удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Категория не найдена и не удалена"));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Spec spec) {
        // maybe try catch block
        // for check id in request body ? or not
        // and send message if it's true
        if (service.findById(spec.getId()).isPresent()) {
            service.update(spec);
            return ResponseEntity.ok(new BaseResponse(true, "Данные категории были обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Категория не найдена и не удалена"));
        }
    }
}
