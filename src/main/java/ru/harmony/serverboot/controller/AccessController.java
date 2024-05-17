package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.AccessService;

@RequestMapping("/harmony/access")
@AllArgsConstructor
@RestController
public class AccessController {
    private final AccessService service;

    // get all Access
    @GetMapping("/all")
    public ResponseEntity<ListResponse<Access>> getAll(){
        return ResponseEntity.ok(new ListResponse<Access>(true,  "Роли доступа", service.getAll()));
    }

    @GetMapping
    // get Access by Id
    public ResponseEntity<DataResponse<Access>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Access>(true, "Найдена роль доступа", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Access>(false, "Роль доступа не найдена"));
        }
    }

    // Save new Access
    @PostMapping
    public ResponseEntity<DataResponse<Access>> save(@RequestBody Access access) {
        return ResponseEntity.ok(new DataResponse<Access>(true, "Добавлена новая роль доступа", service.save(access)));
    }

    // Update Access
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Access access) {
        if (service.findById(access.getId()).isPresent()) {
            service.update(access);
            return ResponseEntity.ok(new BaseResponse(true,"Роль доступа была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Роль не найдена и не обновлена"));
        }
    }

    // Delete Access by id
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true,"Роль доступа была удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Роль не найдена и не удалена"));
        }
    }
}
