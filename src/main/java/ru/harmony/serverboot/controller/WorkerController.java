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

@RequestMapping("/harmony/worker")
@AllArgsConstructor
@RestController
public class WorkerController {
    private final WorkerService service;

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Worker>> getAll(){
        return ResponseEntity.ok(new ListResponse<Worker>(true,  "Сотрудники", service.getAll()));
    }

    // place for getById method, if it needs

    @PostMapping
    public ResponseEntity<DataResponse<Worker>> save(@RequestBody Worker worker) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Worker>(true, "Добавлен новый сотрудник", service.save(worker)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Worker worker) {
        // maybe try catch block
        // for check id in request body ? or not
        // and send message if it's true

        service.update(worker);
        return ResponseEntity.ok(new BaseResponse(true,"Данные сотрудника былы обновлены"));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        // maybe try catch block
        // for check id in request body ? or not

        service.delete(id);
        return ResponseEntity.ok(new BaseResponse(true,"Сотрудник был удален"));
    }
}
