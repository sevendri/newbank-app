package br.com.newbank.rest;

import br.com.newbank.database.entity.Conta;
import br.com.newbank.domain.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewBankController {

    @Autowired
    ContaService contaService;

    @PostMapping("/newbank/contas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity criarConta(@RequestBody Conta conta) {
        System.out.println("recebi a requisição");
        contaService.saveOrUpdate(conta);
        return ResponseEntity.ok().build();
    }
//
//    @PostMapping("/newbank/contas")
//
//    @PatchMapping("/newbank/contas/{idconta}/sacar")
//
//    @PatchMapping("/newbank/contas/{idconta}/depositar")
//
//    @PostMapping("/newbank/contas/{idconta}/transferir")
//
//    @GetMapping("/newbank/contas/{idconta}/consultar")


}
