package ru.harmony.serverboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.AccessService;

@RequestMapping("/harmony/access")
@Tag(name="Контроллер для доступа", description="Контроллер позволяющий взаимодействовать с ролями доступа")
@AllArgsConstructor
@RestController
public class AccessController {
    private final AccessService service;

    // get all Access
    @Operation(
            summary = "Получение ролей доступа",
            description = "Позволяет вывести все роли доступа в ИС"
    ) @GetMapping("/all")
    public ResponseEntity<ListResponse<Access>> getAll(){
        return ResponseEntity.ok(new ListResponse<Access>(true,  "Роли доступа", service.getAll()));
    }

    // get Access by Id
    @Operation(
            summary = "Получить роль доступа по ключу",
            description = "Параметризированный поиск по уникальному идентификатору"
    ) @GetMapping
    public ResponseEntity<DataResponse<Access>> getById(
            @Parameter(description = "Идентификатор доступа") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Access>(true, "Найдена роль доступа", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Access>(false, "Роль доступа не найдена"));
        }
    }

    // Save new Access
    @Operation(
            summary = "Добавление доступа",
            description = "Добавление новой роли доступа в ИС"
    ) @PostMapping
    public ResponseEntity<DataResponse<Access>> save(
            @Parameter(description = "Роль доступа") @RequestBody Access access) {
        return ResponseEntity.ok(new DataResponse<Access>(true, "Добавлена новая роль доступа", service.save(access)));
    }

    // Update Access
    @Operation(
            summary = "Обновление роли доступа",
            description = "Изменяет существующую роль доступа в ИС"
    ) @PutMapping
    public ResponseEntity<BaseResponse> update(
            @Parameter(description = "Роль доступа") @RequestBody Access access) {
        if (service.findById(access.getId()).isPresent()) {
            service.update(access);
            return ResponseEntity.ok(new BaseResponse(true,"Роль доступа была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Роль не найдена и не обновлена"));
        }
    }

    // Delete Access by id
    @Operation(
            summary = "Удаление роли доступа по ключу",
            description = "Удаляет существующую роль доступа из ИС по {уникальному идентификатору}"
    ) @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true,"Роль доступа была удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Роль не найдена и не удалена"));
        }
    }
}
