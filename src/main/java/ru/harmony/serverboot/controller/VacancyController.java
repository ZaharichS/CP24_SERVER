package ru.harmony.serverboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Spec;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.SpecService;
import ru.harmony.serverboot.service.VacancyService;

@RequestMapping("/harmony/vacancy")
@Tag(name="Контроллер для вакансий", description="Контроллер позволяющий взаимодействовать с доступными вакансиями")
@AllArgsConstructor
@RestController
public class VacancyController {
    private final VacancyService service;

    // Get all Vacancy
    @Operation(
            summary = "Получение вакансий",
            description = "Позволяет вывести все вакансии в ИС"
    ) @GetMapping("/all")
    public ResponseEntity<ListResponse<Vacancy>> getAll() {
        return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии", service.getAll()));
    }

    // Get all companies
    @Operation(
            summary = "Получение компаний",
            description = "Позволяет вывести всей доступные компании в ИС"
    ) @GetMapping("/all-employee")
    public ResponseEntity<ListResponse<String>> getAllByEmployee() {
        return ResponseEntity.ok(new ListResponse<String>(true, "Все компании", service.getAllByEmployee()));
    }

    // Get all Vacancy by WorkExperience
    @Operation(
            summary = "Получение вакансий по ключу",
            description = "Позволяет вывести вакансии по {опыт работы}"
    ) @GetMapping("/all-workexp")
    public ResponseEntity<ListResponse<Vacancy>> getAllByWorkExp(
            @Parameter(description = "Опыт работы") @RequestParam Long workExp) {
        if (!service.getAllByWorkExp(workExp).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии по опыту работы {" + workExp +"}", service.getAllByWorkExp(workExp)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Vacancy>(false, "Вакансия по опыту работы {" + workExp +"} не найдены", null));
        }
    }

    // Get all Vacancy by Wage
    @Operation(
            summary = "Получение вакансий по ключу",
            description = "Позволяет вывести вакансии по {заработной плате}"
    ) @GetMapping("/all-wage")
    public ResponseEntity<ListResponse<Vacancy>> getAllByWage(
            @Parameter(description = "Заработная плата") @RequestParam String wage) {
        if (!service.getAllByWage(wage).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии по заработной плате {" + wage + "}", service.getAllByWage(wage)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Vacancy>(false, "Вакансия по заработной плате {" + wage + "} не найдены", null));
        }
    }

    // Get Vacancy by Id
    @Operation(
            summary = "Получение вакансии по ключу",
            description = "Позволяет получить вакансию по {уникальному идентификатору}"
    ) @GetMapping
    public ResponseEntity<DataResponse<Vacancy>> getById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Vacancy>(true, "Найдена вакансия", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Vacancy>(false, "Вакансия не найдена"));
        }
    }

    // Save new Vacancy
    @Operation(
            summary = "Добавление вакании",
            description = "Добавление новой вакансии в ИС"
    ) @PostMapping
    public ResponseEntity<DataResponse<Vacancy>> save(
            @Parameter(description = "Вакансия") @RequestBody Vacancy vacancy) {
        return ResponseEntity.ok(new DataResponse<Vacancy>(true, "Добавлена новая вакансия", service.save(vacancy)));
    }

    // Delete Vacancy by Id
    @Operation(
            summary = "Удаление вакансии по ключу",
            description = "Удаляет вакансию из ИС по {уникальному идентификатору}"
    ) @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }

    // Delete Vacancy by VacancyName and SpecName
    @Operation(
            summary = "Удаление вакансии по ключу",
            description = "Удаляет вакансию из ИС по {заголовку вакансии, название специализации}"
    ) @DeleteMapping("/del-bynames")
    @Transactional
    public ResponseEntity<BaseResponse> deleteByNameAndSpecName(
            @Parameter(description = "Заголовок вакансии") @RequestParam String name,
            @Parameter(description = "Название специализации") @RequestParam String specName) {
        if (!service.findByNameSpecAndSpecName(name, specName).isEmpty()) {
            service.deleteByNameAndSpecName(name, specName);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }

    // Update Vacancy
    @Operation(
            summary = "Обновление вакансии",
            description = "Изменяет существующую вакансию в ИС"
    ) @PutMapping
    public ResponseEntity<BaseResponse> update(
            @Parameter(description = "Вакансия") @RequestBody Vacancy vacancy) {
        if (service.findById(vacancy.getId()).isPresent()) {
            service.update(vacancy);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }
}
