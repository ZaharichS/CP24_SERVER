package ru.harmony.serverboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Worker;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.WorkerService;

@RequestMapping("/harmony/worker")
@Tag(name="Контроллер для сотрудников", description="Контроллер позволяющий взаимодействовать с сотрудниками")
@AllArgsConstructor
@RestController
public class WorkerController {
    private final WorkerService service;

    // Get all Worker
    @Operation(
            summary = "Получение сотрудников",
            description = "Позволяет вывести всех сотрудников в ИС"
    ) @GetMapping("/all")
    public ResponseEntity<ListResponse<Worker>> getAll(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники", service.getAll()));
    }

    // Get all Worker who has access to IS
    @Operation(
            summary = "Получение сотрудников по фильтру",
            description = "Позволяет вывести всех сотрудников которые имеют доступ к ИС"
    ) @GetMapping("/all-accessIn")
    public ResponseEntity<ListResponse<Worker>> getAllByAccessIn(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  у которых есть доступ к ИС", service.getAllByAccessIn()));
    }

    // Get all Worker who doesn't have access to IS
    @Operation(
            summary = "Получение сотрудников по фильтру",
            description = "Позволяет вывести всех сотрудников которые не имеют доступ к ИС"
    ) @GetMapping("/all-accessOut")
    public ResponseEntity<ListResponse<Worker>> getAllByAccessOut(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  у которых нет доступа к ИС", service.getAllByAccessOut()));
    }

    // Get all Recruters
    @Operation(
            summary = "Получение сотрудников по фильтру",
            description = "Позволяет вывести всех сотрудников с должностью рекрутер"
    ) @GetMapping("/all-recruter")
    public ResponseEntity<ListResponse<Worker>> getAllRecruter(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  рекрутеры", service.getAllRecruter()));
    }

    // Get all Worker by surname
    @Operation(
            summary = "Получение сотрудников по фильтру",
            description = "Позволяет вывести всех сотрудников в алфавитном порядке по фамилии"
    ) @GetMapping("/all-workerFilter")
    public ResponseEntity<ListResponse<Worker>> getAllWorkerFiltered(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники  по фильтру фамилия", service.getAllBySurnameFilter()));
    }

    // Get Worker by Id
    @Operation(
            summary = "Получение сотрудника по ключу",
            description = "Позволяет получить сотрудника по {уникальному идентификатору}"
    ) @GetMapping
    public ResponseEntity<DataResponse<Worker>> getById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Worker>(true, "Найден сотрудник", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Worker>(false, "Сотрудник не найден"));
        }
    }

    // Get Worker by AccessID
    @Operation(
            summary = "Получение сотрудника по ключу",
            description = "Позволяет получить сотрудника по {уникальному идентификатору доступа}"
    ) @GetMapping("/access")
    public ResponseEntity<ListResponse<Worker>> getByAccess(
            @Parameter(description = "Уникальный идентификатор доступа") @RequestParam Long id) {
        if (!service.findByAccess(id).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Worker>(true, "Сотрудники с этой ролью", service.findByAccess(id)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Worker>(false, "Сотрудники с этой ролью не найдены", null));
        }
    }

    // Get Worker by filtered name
    @Operation(
            summary = "Получение сотрудника по ключу",
            description = "Позволяет получить сотрудника по {имя}"
    ) @GetMapping("/all-name")
    public ResponseEntity<ListResponse<Worker>> getByName(
            @Parameter(description = "Имя сотрдуника") @RequestParam String name) {
        if (!service.findByName(name).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Worker>(true, "Сотрудник с именем {" + name + "}", service.findByName(name)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Worker>(false, "Сотрудник с этим именем {" + name + "} не найден", null));
        }
    }

    // Save new Worker
    @Operation(
            summary = "Добавление сотрудника",
            description = "Добавление нового сотрудника в ИС"
    ) @PostMapping
    public ResponseEntity<DataResponse<Worker>> save(
            @Parameter(description = "Сотрудник") @RequestBody Worker worker) {
        return ResponseEntity.ok(new DataResponse<Worker>(true, "Добавлен новый сотрудник", service.save(worker)));
    }

    // Update Worker
    @Operation(
            summary = "Обновление сотрудника",
            description = "Изменяет существующего сотрудника в ИС"
    ) @PutMapping
    public ResponseEntity<BaseResponse> update(
            @Parameter(description = "Сотрудник") @RequestBody Worker worker) {
        if (service.findById(worker.getId()).isPresent()) {
            service.update(worker);
            return ResponseEntity.ok(new BaseResponse(true, "Данные сотрудника былы обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Сотрудник не найден и не обновлен"));
        }
    }

    // Delete Worker by Id
    @Operation(
            summary = "Удаление сотрудника по ключу",
            description = "Удаляет сотрудника из ИС по {уникальному идентификатору}"
    ) @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Сотрудник был удален"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Сотрудник не найден и не удален"));
        }
    }
}
