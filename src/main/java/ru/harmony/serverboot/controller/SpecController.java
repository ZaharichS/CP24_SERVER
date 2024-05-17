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

    // Get all Spec
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Spec>> getAll() {
        return ResponseEntity.ok(new ListResponse<Spec>(true, "Категории", service.getAll()));
    }

    // Get Spec by Id
    @GetMapping
    public ResponseEntity<DataResponse<Spec>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Spec>(true, "Найдена категория", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Spec>(false, "Категория не найдена"));
        }
    }

    // Save new Spec
    @PostMapping
    public ResponseEntity<DataResponse<Spec>> save(@RequestBody Spec spec) {
        return ResponseEntity.ok(new DataResponse<Spec>(true, "Добавлена новая категория", service.save(spec)));
    }

    // Delete Spec by Id
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Категория была удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Категория не найдена и не удалена"));
        }
    }

    // Update Spec
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Spec spec) {
        if (service.findById(spec.getId()).isPresent()) {
            service.update(spec);
            return ResponseEntity.ok(new BaseResponse(true, "Данные категории были обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Категория не найдена и не удалена"));
        }
    }
}
