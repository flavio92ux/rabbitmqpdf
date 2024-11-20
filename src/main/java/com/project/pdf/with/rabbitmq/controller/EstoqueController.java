package com.project.pdf.with.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pdf.with.rabbitmq.dto.EstoqueDto;
import com.project.pdf.with.rabbitmq.services.RabbitmqService;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDto estoqueDto) {
        this.rabbitmqService.enviaMensagem("teste3", estoqueDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
