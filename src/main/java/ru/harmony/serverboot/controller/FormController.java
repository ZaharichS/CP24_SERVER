package ru.harmony.serverboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.harmony.serverboot.entity.Access;
import ru.harmony.serverboot.entity.Form;
import ru.harmony.serverboot.entity.Vacancy;
import ru.harmony.serverboot.response.BaseResponse;
import ru.harmony.serverboot.response.DataResponse;
import ru.harmony.serverboot.response.ListResponse;
import ru.harmony.serverboot.service.FormService;
import ru.harmony.serverboot.service.VacancyService;

@RequestMapping("/harmony/form")
@Tag(name="Контроллер для анкет", description="Контроллер позволяющий взаимодействовать с доступными анкетами")
@AllArgsConstructor
@RestController
public class FormController {
    private final FormService service;

    // Get all Form
    @Operation(
            summary = "Получение анкет",
            description = "Позволяет вывести все анкеты в ИС"
    ) @GetMapping("/all")
    public ResponseEntity<ListResponse<Form>> getAll() {
        return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты", service.getAll()));
    }

    // Get form by formHeader and SpecName
    @Operation(
            summary = "Получение анкет по ключу",
            description = "Параметризированный поиск по  {Заголовок анкеты, название специализации}"
    ) @GetMapping("/all-headerSpec")
    public ResponseEntity<ListResponse<Form>> getByHeaderAndSpec(
            @Parameter(description = "Заголовок анкеты") @RequestParam String header,
            @Parameter(description = "Название специализации") @RequestParam String spec) {
        if (!service.findByHeaderAndSpec(header, spec).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты по заголовку и специализации { " + header + ", " + spec + "}", service.findByHeaderAndSpec(header, spec)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета по заголовку и специализации {" + header + ", " + spec + "} не найдена", null));
        }
    }

    // Get Form by AspirantName
    @Operation(
            summary = "Получение анкет по ключу",
            description = "Параметризированный поиск по {ФИО соискателя}"
    ) @GetMapping("/all-aspName")
    public ResponseEntity<ListResponse<Form>> getByAspName(
            @Parameter(description = "Фамлия") @RequestParam String surname,
            @Parameter(description = "Имя") @RequestParam String name,
            @Parameter(description = "Отчество") @RequestParam String lastname) {
        if (!service.findByAspName(surname, name, lastname).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты по ФИО {" + surname +", " + name + "," + lastname +"}", service.findByAspName(surname, name, lastname)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета по ФИО {" + surname +", " + name + "," + lastname + "} не найдена", null));
        }
    }

    // Get Form by Aspirant date of birth
    @Operation(
            summary = "Получение анкет по ключу",
            description = "Параметризированный поиск по {дата рождения соискателя}"
    ) @GetMapping("/all-aspDateBirth")
    public ResponseEntity<ListResponse<Form>> getByAspBirthDate(
            @Parameter(description = "Дата рождения") @RequestParam String date) {
        if (!service.findByAspBirth(date).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты по дате рождения {" + date +"}", service.findByAspBirth(date)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкеты по дате рождения {" + date +"}\" не найдены", null));
        }
    }

    // Get all Form by AspirantName filtered
    @Operation(
            summary = "Получение анкет по фильтру",
            description = "Позволяет вывести все анкеты в алфовитном порядке по фамилии"
    ) @GetMapping("/all-surname")
    public ResponseEntity<ListResponse<Form>> getAllBySurname() {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Анкеты", service.findByFilterSurname()));
    }

    // Save new Form
    @Operation(
            summary = "Добавление анкет",
            description = "Добавление новой анкеты в ИС"
    ) @PostMapping
    public ResponseEntity<DataResponse<Form>> save(
            @Parameter(description = "Анкета") @RequestBody Form form) {
        return ResponseEntity.ok(new DataResponse<Form>(true, "Добавлена новая анкета", service.save(form)));
    }

    // Get Form by Id
    @Operation(
            summary = "Получение анкет по ключу",
            description = "Параметризированный поиск по {уникальному идентификатору}"
    ) @GetMapping
    public ResponseEntity<DataResponse<Form>> getById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            return ResponseEntity.ok(new DataResponse<Form>(true, "Найдена анкета", service.findById(id).orElseThrow()));
        } else {
            return ResponseEntity.badRequest().body(new DataResponse<Form>(false, "Анкета не найдена"));
        }
    }

    // Get all Form by SpecName
    @Operation(
            summary = "Получение анкет по ключу",
            description = "Параметризированный поиск по {названию специализации}"
    ) @GetMapping("all-spec")
    public ResponseEntity<ListResponse<Form>> getBySpec(
            @Parameter(description = "Название специализации") @RequestParam String spec) {
        if (!service.findBySpec(spec).isEmpty()) {
            return ResponseEntity.ok(new ListResponse<Form>(true, "Найдена анкеты по категории {" + spec + "}", service.findBySpec(spec)));
        } else {
            return ResponseEntity.badRequest().body(new ListResponse<Form>(false, "Анкета по категории {" + spec + "}не найдена", null));
        }
    }

    // Delete form by AspirantName
    @Operation(
            summary = "Удаление анкеты по ключу",
            description = "Удаляет существующую анкету по {ФИО соискателя}"
    )
    @DeleteMapping("del-aspname")
    @Transactional
    public ResponseEntity<BaseResponse> deleteByAspName(
            @Parameter(description = "Имя") @RequestParam String name,
            @Parameter(description = "Фамилия") @RequestParam String surname,
            @Parameter(description = "Отчество") @RequestParam String lastname) {
        if (!service.findByAspName(surname, name, lastname).isEmpty()) {
            service.deleteByAspName(surname, name, lastname);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета по ФИО {" + surname +", " + name + "," + lastname + "} не найдена и не удалена"));
        }
    }

    // Delete Form by FormHeader and SpecName
    @Operation(
            summary = "Удаление анкету по ключу",
            description = "Удаляет существующую анкету по {Заголовок анкеты, название специализации}"
    )
    @DeleteMapping("all-headerSpec")
    @Transactional
    public ResponseEntity<BaseResponse> deleteByHeaderSpec(
            @Parameter(description = "Заголовок анкеты") @RequestParam String header,
            @Parameter(description = "Название специализации") @RequestParam String spec) {
        if (!service.findByHeaderAndSpec(header, spec).isEmpty()) {
            service.deleteByHeaderSpec(header, spec);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не удалена"));
        }
    }

    // Delete Form by Id
    @Operation(
            summary = "Удаление роли доступа по ключу",
            description = "Удаляет существующую роль доступа из ИС по {уникальному идентификатору}"
    ) @DeleteMapping
    public ResponseEntity<BaseResponse> deleteById(
            @Parameter(description = "Уникальный идентификатор") @RequestParam Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(new BaseResponse(true, "Анкета удалена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не удалена"));
        }
    }

    // Update Form
    @Operation(
            summary = "Обновление анкеты",
            description = "Изменяет существующую анкету в ИС"
    ) @PutMapping
    public ResponseEntity<BaseResponse> update(
            @Parameter(description = "Анкеты") @RequestBody Form form) {
        if (service.findById(form.getId()).isPresent()) {
            service.update(form);
            return ResponseEntity.ok(new BaseResponse(true,"Анкета была обновлена"));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Анкета не найдена и не обновлена"));
        }
    }
}
