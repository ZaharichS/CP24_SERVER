package ru.harmony.serverboot.controller;

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
@AllArgsConstructor
@RestController
public class VacancyController {
    private final VacancyService service;

    // Get all Vacancy
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Vacancy>> getAll() {
        return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии", service.getAll()));
    }

    // Get all companies
    @GetMapping("/all-employee")
    public ResponseEntity<ListResponse<String>> getAllByEmployee() {
        return ResponseEntity.ok(new ListResponse<String>(true, "Все компании", service.getAllByEmployee()));
    }

    // Get all Vacancy by WorkExperience
    @GetMapping("/all-workexp")
    public ResponseEntity<ListResponse<Vacancy>> getAllByWorkExp(@RequestParam Long workExp) {
        if (!service.getAllByWorkExp(workExp).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии по опыту работы {" + workExp +"}", service.getAllByWorkExp(workExp)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Vacancy>(false, "Вакансия по опыту работы {" + workExp +"} не найдены", null));
        }
    }

    // Get all Vacancy by Wage
    @GetMapping("/all-wage")
    public ResponseEntity<ListResponse<Vacancy>> getAllByWage(@RequestParam String wage) {
        if (!service.getAllByWage(wage).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии по заработной плате {" + wage + "}", service.getAllByWage(wage)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Vacancy>(false, "Вакансия по заработной плате {" + wage + "} не найдены", null));
        }
    }

    // Get Vacancy by Id
    @GetMapping
    public ResponseEntity<DataResponse<Vacancy>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Vacancy>(true, "Найдена вакансия", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Vacancy>(false, "Вакансия не найдена"));
        }
    }

    // Save new Vacancy
    @PostMapping
    public ResponseEntity<DataResponse<Vacancy>> save(@RequestBody Vacancy vacancy) {
        return ResponseEntity.ok(new DataResponse<Vacancy>(true, "Добавлена новая вакансия", service.save(vacancy)));
    }

    // Delete Vacancy by Id
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }

    // Delete Vacancy by VacancyName and SpecName
    @DeleteMapping("/del-bynames")
    @Transactional
    public ResponseEntity<BaseResponse> deleteByNameAndSpecName(@RequestParam String name, String specName) {
        if (!service.findByNameSpecAndSpecName(name, specName).isEmpty()) {
            service.deleteByNameAndSpecName(name, specName);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }

    // Update Vacancy
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Vacancy vacancy) {
        if (service.findById(vacancy.getId()).isPresent()) {
            service.update(vacancy);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }
}
