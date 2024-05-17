package ru.harmony.serverboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Контроллер для специализаций", description="Контроллер позволяющий взаимодействовать с доступными категориями")
@AllArgsConstructor
@RestController
public class SpecController {
    private final SpecService service;

    // Get all Spec
    @Operation(
            summary = "Получение категорий",
            description = "Позволяет вывести все категории в ИС"
    ) @GetMapping("/all")
    public ResponseEntity<ListResponse<Spec>> getAll() {
        return ResponseEntity.ok(new ListResponse<Spec>(true, "Категории", service.getAll()));
    }

    // Get Spec by Id
    @Operation(
            summary = "Получение категорий по ключу",
            description = "Параметризированный поиск по {Заголовок анкеты, название специализации}"
    ) @GetMapping
    public ResponseEntity<DataResponse<Spec>> getById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Spec>(true, "Найдена категория", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Spec>(false, "Категория не найдена"));
        }
    }

    // Save new Spec
    @Operation(
            summary = "Добавление категорий",
            description = "Добавление новой категории в ИС"
    ) @PostMapping
    public ResponseEntity<DataResponse<Spec>> save(
            @Parameter(description = "Категория") @RequestBody Spec spec) {
        return ResponseEntity.ok(new DataResponse<Spec>(true, "Добавлена новая категория", service.save(spec)));
    }

    // Delete Spec by Id
    @Operation(
            summary = "Удаление категории по ключу",
            description = "Удаляет существующую роль доступа из ИС по {уникальному идентификатору}"
    ) @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Категория была удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Категория не найдена и не удалена"));
        }
    }

    // Update Spec
    @Operation(
            summary = "Обновление категории",
            description = "Изменяет существующую категорию в ИС"
    ) @PutMapping
    public ResponseEntity<BaseResponse> update(
            @Parameter(description = "Категория") @RequestBody Spec spec) {
        if (service.findById(spec.getId()).isPresent()) {
            service.update(spec);
            return ResponseEntity.ok(new BaseResponse(true, "Данные категории были обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Категория не найдена и не удалена"));
        }
    }
}
