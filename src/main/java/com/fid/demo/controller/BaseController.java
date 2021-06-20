package com.fid.demo.controller;

import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.entity.BaseEntity;
import com.fid.demo.service.IBaseService;
import com.fid.demo.util.GeneralResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class BaseController<T extends BaseEntity, S extends IBaseService<T>> {

    protected final S baseService;

    @GetMapping("/list")
    private ResponseEntity<GeneralResponse> findAllByCUser() {
        return ResponseEntity.ok(GeneralResponseBuilder.success(baseService.findAllByCreatedBy()));
    }

    @PostMapping("/save")
    private ResponseEntity<GeneralResponse> save(@RequestBody T entity) {
        return ResponseEntity.ok(GeneralResponseBuilder.success(baseService.save(entity)));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<GeneralResponse> delete(@PathVariable Integer id) {
        baseService.delete(id);
        return ResponseEntity.ok(GeneralResponseBuilder.success(null));
    }

}
