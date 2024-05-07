package ru.harmony.serverboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.harmony.serverboot.entity.Access;
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
}
