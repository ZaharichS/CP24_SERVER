package ru.harmony.serverboot.controller;

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
@AllArgsConstructor
@RestController
public class UserController {
    private final UserService service;

    // Get all User
    @GetMapping("/all")
    public ResponseEntity<ListResponse<User>> getAll(){
        return ResponseEntity.ok(new ListResponse<User>(true,  "Пользователи системы", service.getAll()));
    }

    // Get User by Id
    @GetMapping
    public ResponseEntity<DataResponse<User>> getById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<User>(true, "Найден пользователь", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<User>(false, "Пользователь не найден"));
        }
    }

    // Get User who Recruter
    @GetMapping("/all-userAccess")
    public ResponseEntity<ListResponse<User>> getByAccess(@RequestParam String name) {
        if (!service.getByAccess(name).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<User>(true, "Найденые пользователь по {" + name + "}", service.getByAccess(name)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<>(false, "Пользователи с ролью {" + name + "} не найден", null));
        }
    }
    // Save new User
    @PostMapping
    public ResponseEntity<DataResponse<User>> save(@RequestBody User user) {
        return ResponseEntity.ok(new DataResponse<User>(true, "Добавлен новый пользователь", service.save(user)));
    }

    // Update User
    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody User user) {
        if (service.findById(user.getId()).isPresent()) {
            service.update(user);
            return ResponseEntity.ok(new BaseResponse(true, "Данные пользователя былы обновлены"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не найден и не обновлен"));
        }
    }

    // Delete User by Id
    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(@RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Пользователь был удален"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не найден и не удален"));
        }
    }
}
