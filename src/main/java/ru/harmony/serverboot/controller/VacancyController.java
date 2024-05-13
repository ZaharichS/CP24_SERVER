package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Vacancy>> getAll() {
        return ResponseEntity.ok(new ListResponse<Vacancy>(true, "Вакансии", service.getAll()));
    }

    @GetMapping
    // getById method
    public ResponseEntity<DataResponse<Vacancy>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Vacancy>(true, "Найдена вакансия", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Vacancy>(false, "Вакансия не найдена"));
        }
    }

    @PostMapping
    public ResponseEntity<DataResponse<Vacancy>> save(@RequestBody Vacancy vacancy) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Vacancy>(true, "Добавлена новая вакансия", service.save(vacancy)));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Vacancy vacancy) {
        // maybe try catch block
        // for check id in request body ? or not
        // and send message if it's true
        if (service.findById(vacancy.getId()).isPresent()) {
            service.update(vacancy);
            return ResponseEntity.ok(new BaseResponse(true, "Вакансия была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Вакансия не найдена и не удалена"));
        }
    }
}
