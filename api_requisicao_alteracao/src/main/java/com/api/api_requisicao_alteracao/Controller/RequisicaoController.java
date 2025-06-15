package com.api.api_requisicao_alteracao.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_requisicao_alteracao.Entity.RequisicaoAlteracao;
import com.api.api_requisicao_alteracao.Repository.RequisicaoRepository;
import com.api.api_requisicao_alteracao.domain.dto.RequisicaoDto;
import com.api.api_requisicao_alteracao.domain.dto.ResponseDto;
import com.api.api_requisicao_alteracao.domain.services.RequisicaoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/requisicao_alteracao/")
public class RequisicaoController {
    @Autowired
    RequisicaoRepository requisicaoRepository;
    @Autowired
    RequisicaoService requisicaoService;


    @PostMapping(value = "inserir")
    @ResponseStatus(HttpStatus.CREATED)
    public RequisicaoAlteracao savRequisicaoAlteracao(@Valid @RequestBody RequisicaoAlteracao requisicao) {
        return requisicaoService.saveRequisicao(requisicao);
    }

    @GetMapping(value = "listar")
    public List<RequisicaoDto> getAllRequisicoes() {
        return requisicaoService.getAllRequisicoes();
    }

    @GetMapping("/obter/{id}")
    public RequisicaoAlteracao getRequisicaoAlteracaoById(@PathVariable Long id) {
        return requisicaoService.getRequisicaoById(id);
    }

    @PutMapping(value = "alterar")
    public RequisicaoAlteracao updateRequisicaoAlteracao(@RequestParam Long id, @RequestBody RequisicaoAlteracao newRequisicao) {
        return requisicaoService.updateRequisicao(id, newRequisicao);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseDto deleteRequisicao(@PathVariable Long id) {
        return requisicaoService.deleteRequisicao(id);
    }
}