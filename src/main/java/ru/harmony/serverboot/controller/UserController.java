package ru.harmony.serverboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.User;
import ru.harmony.serverboot.entity.Worker;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.UserService;

import java.util.List;

@RequestMapping("harmony/user")
@Tag(name="Контроллер для учетных записей", description="Контроллер позволяющий взаимодействовать с доступными пользователями")
@AllArgsConstructor
@RestController
public class UserController {
    private final UserService service;

    // Get all User
    @Operation(
            summary = "Получение учетный записей",
            description = "Позволяет вывести всех пользоваталей в ИС"
    ) @GetMapping("/all")
    public ResponseEntity<ListResponse<User>> getAll(){
        return ResponseEntity.ok(new ListResponse<User>(true,  "Пользователи системы", service.getAll()));
    }

    // Get User by Id
    @Operation(
            summary = "Получение пользователей по ключу",
            description = "Параметризированный поиск по {уникальному идентификатору}"
    ) @GetMapping
    public ResponseEntity<DataResponse<User>> getById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<User>(true, "Найден пользователь", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<User>(false, "Пользователь не найден"));
        }
    }

    // Get User who Recruter
    @Operation(
            summary = "Получение пользователей по ключу",
            description = "Параметризированный поиск по {роли доступа}"
    ) @GetMapping("/all-userAccess")
    public ResponseEntity<ListResponse<User>> getByAccess(
            @Parameter(description = "Роль доступа") @RequestParam String name) {
        if (!service.getByAccess(name).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<User>(true, "Найденые пользователь по {" + name + "}", service.getByAccess(name)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<>(false, "Пользователи с ролью {" + name + "} не найден", null));
        }
    }

    // Get User by user data
    @Operation(
            summary = "Получение пользователей по ключу",
            description = "Параметризированный поиск по {логин, пароль}"
    ) @GetMapping("/all-userData")
    public ResponseEntity<DataResponse<User>> getByUserData(
            @Parameter(description = "Логин") @RequestParam String login,
            @Parameter(description = "Пароль") @RequestParam String password) {
            if (service.getByUserData(login, password) != null) {
            return ResponseEntity.ok(new DataResponse<>(true, "Пользователь по данным ", service.getByUserData(login, password)));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<>(false, "Пользователи с такими данными не найден", null));
        }
    }

    // Get User by user data and access
    @Operation(
            summary = "Получение пользователей по ключу и доступу к ИС",
            description = "Параметризированный поиск по {логин, пароль}"
    ) @GetMapping("/all-userDataAccess")
    public ResponseEntity<DataResponse<User>> getByUserDataAndAccess(
            @Parameter(description = "Логин") @RequestParam String login,
            @Parameter(description = "Пароль") @RequestParam String password) {
        if (service.getByUserAccess(login, password) != null) {
            return ResponseEntity.ok(new DataResponse<>(true, "Пользователь по данным и с доступом к ИС ", service.getByUserAccess(login, password)));
        } else {
            return ResponseEntity.ok(new DataResponse<>(false, "Пользователи с такими данными не найден", null));
        }
    }

    // Get admin User by user data and access
    @Operation(
            summary = "Получение пользователей по ключу и доступу к ИС",
            description = "Параметризированный поиск по {логин, пароль}"
    ) @GetMapping("/all-Admin")
    public ResponseEntity<DataResponse<User>> getByAdminAccess(
            @Parameter(description = "Логин") @RequestParam String login,
            @Parameter(description = "Пароль") @RequestParam String password) {
        if (service.getByAdminUserAccess(login, password) != null) {
            return ResponseEntity.ok(new DataResponse<>(true, "Пользователь по данным и с доступом к ИС ", service.getByAdminUserAccess(login, password)));
        } else {
            return ResponseEntity.ok(new DataResponse<>(false, "Пользователи с такими данными не найден", null));
        }
    }

    // Save new User
    @Operation(
            summary = "Добавление пользователя",
            description = "Добавление новой учетной записи в ИС"
    ) @PostMapping
    public ResponseEntity<DataResponse<User>> save(
            @Parameter(description = "Пользователь") @RequestBody User user) {
        return ResponseEntity.ok(new DataResponse<User>(true, "Добавлен новый пользователь", service.save(user)));
    }

    // Update User
    @Operation(
            summary = "Обновление пользователя",
            description = "Изменяет существующую учетную запись в ИС"
    ) @PutMapping
    public ResponseEntity<BaseResponse> update(
            @Parameter(description = "Пользователь") @RequestBody User user) {
        if (service.findById(user.getId()).isPresent()) {
            service.update(user);
            return ResponseEntity.ok(new BaseResponse(true, "Данные пользователя былы обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не найден и не обновлен"));
        }
    }

    // Delete User by Id
    @Operation(
            summary = "Удаление пользователя по ключу",
            description = "Удаляет учетную запись из ИС по {уникальному идентификатору}"
    ) @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Пользователь был удален"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не найден и не удален"));
        }
    }
}
