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

    // Get all Worker
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Worker>> getAll(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники", service.getAll()));
    }

    // Get all Worker who has access to IS
    @GetMapping("/all-accessIn")
    public ResponseEntity<ListResponse<Worker>> getAllByAccessIn(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  у которых есть доступ к ИС", service.getAllByAccessIn()));
    }

    // Get all Worker who doesn't have access to IS
    @GetMapping("/all-accessOut")
    public ResponseEntity<ListResponse<Worker>> getAllByAccessOut(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  у которых нет доступа к ИС", service.getAllByAccessOut()));
    }

    // Get all Recruters
    @GetMapping("/all-recruter")
    public ResponseEntity<ListResponse<Worker>> getAllRecruter(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  рекрутеры", service.getAllRecruter()));
    }

    // Get all Worker by WorkerName
    @GetMapping("/all-workerFilter")
    public ResponseEntity<ListResponse<Worker>> getAllWorkerFiltered(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  по фильтру name", service.getAllByNameFilter()));
    }

    // Get Worker by Id
    @GetMapping
    public ResponseEntity<DataResponse<Worker>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Worker>(true, "Найден сотрудник", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Worker>(false, "Сотрудник не найден"));
        }
    }

    // Get Worker by AccessID
    @GetMapping("/access")
    public ResponseEntity<ListResponse<Worker>> getByAccess(@RequestParam Long id) {
        if (!service.findByAccess(id).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Worker>(true, "Сотрудники с этой ролью", service.findByAccess(id)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Worker>(false, "Сотрудники с этой ролью не найдены", null));
        }
    }

    // Get Worker by filtered WorkerName
    @GetMapping("/all-name")
    public ResponseEntity<ListResponse<Worker>> getByName(@RequestParam String name) {
        if (!service.findByName(name).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Worker>(true, "Сотрудник с ФИО {" + name + "}", service.findByName(name)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Worker>(false, "Сотрудник с этим ФИО {" + name + "} не найден", null));
        }
    }

    // Save new Worker
    @PostMapping
    public ResponseEntity<DataResponse<Worker>> save(@RequestBody Worker worker) {
        return ResponseEntity.ok(new DataResponse<Worker>(true, "Добавлен новый сотрудник", service.save(worker)));
    }

    // Update Worker
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Worker worker) {
        if (service.findById(worker.getId()).isPresent()) {
            service.update(worker);
            return ResponseEntity.ok(new BaseResponse(true, "Данные сотрудника былы обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Сотрудник не найден и не обновлен"));
        }
    }

    // Delete Worker by Id
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
