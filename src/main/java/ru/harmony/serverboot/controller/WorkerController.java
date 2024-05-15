package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Worker;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.WorkerService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/harmony/worker")
@AllArgsConstructor
@RestController
public class WorkerController {
    private final WorkerService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Worker>> getAll(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники", service.getAll()));
    }

    @GetMapping
    // getById method
    public ResponseEntity<DataResponse<Worker>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Worker>(true, "Найден сотрудник", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Worker>(false, "Сотрудник не найден"));
        }
    }

    @GetMapping("/access")
    // getById method
    public ResponseEntity<ListResponse<Worker>> getByAccess(@RequestParam Long id) {
        if (!service.findByAccess(id).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Worker>(true, "Сотрудники с этой ролью", service.findByAccess(id)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Worker>(false, "Сотрудники с этой ролью не найдены", null));
        }
    }

    @PostMapping
    public ResponseEntity<DataResponse<Worker>> save(@RequestBody Worker worker) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Worker>(true, "Добавлен новый сотрудник", service.save(worker)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Worker worker) {
        if (service.findById(worker.getId()).isPresent()) {
            service.update(worker);
            return ResponseEntity.ok(new BaseResponse(true, "Данные сотрудника былы обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Сотрудник не найден и не обновлен"));
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Сотрудник был удален"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Сотрудник не найден и не удален"));
        }
    }
}
