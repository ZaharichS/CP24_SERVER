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

    @GetMapping("/all")
    public ResponseEntity<ListResponse<Access>> getAll(){
        return ResponseEntity.ok(new ListResponse<Access>(true,  "Роли доступа", service.getAll()));
    }

    // place for getById method, if it needs

    @PostMapping
    public ResponseEntity<DataResponse<Access>> save(@RequestBody Access access) {
        // maybe try catch block
        return ResponseEntity.ok(new DataResponse<Access>(true, "Добавлена новая роль доступа", service.save(access)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody Access access) {
        // maybe try catch block
        // for check id in request body ? or not
        // and send message if it's true

        service.update(access);
        return ResponseEntity.ok(new BaseResponse(true,"Роль доступа была обновлена"));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        // maybe try catch block
        // for check id in request body ? or not

        service.delete(id);
        return ResponseEntity.ok(new BaseResponse(true,"Роль доступа была удалена"));
    }
}
