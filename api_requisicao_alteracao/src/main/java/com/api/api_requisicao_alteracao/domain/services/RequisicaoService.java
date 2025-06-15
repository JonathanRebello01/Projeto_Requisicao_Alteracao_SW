package com.api.api_requisicao_alteracao.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.api_requisicao_alteracao.Entity.RequisicaoAlteracao;
import com.api.api_requisicao_alteracao.Repository.RequisicaoRepository;
import com.api.api_requisicao_alteracao.domain.dto.RequisicaoDto;
import com.api.api_requisicao_alteracao.domain.dto.ResponseDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RequisicaoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseDto responseDto;

    @Autowired 
    private RequisicaoRepository requisicaoRepository;
   
    @SuppressWarnings("null")
    public RequisicaoAlteracao saveRequisicao(@Valid @RequestBody RequisicaoAlteracao requisicao){
        return requisicaoRepository.save(requisicao);
    }

    @SuppressWarnings("null")
    public List<RequisicaoDto> getAllRequisicoes() {
        List<RequisicaoAlteracao> requisicoes = requisicaoRepository.findAll();
        return requisicoes.stream()
                     .map(requisicao -> modelMapper.map(requisicao, RequisicaoDto.class))
                     .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public RequisicaoAlteracao getRequisicaoById(@PathVariable Long id) {
        return requisicaoRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public RequisicaoAlteracao updateRequisicao(Long id, RequisicaoAlteracao newRequisicao) {
        Optional<RequisicaoAlteracao> requisicaoOptional = requisicaoRepository.findById(id);
        if (requisicaoOptional.isPresent()) {
            RequisicaoAlteracao requisicaoAlteracao = requisicaoOptional.get();
            requisicaoAlteracao.setIdRequisicao(newRequisicao.getIdRequisicao());
            requisicaoAlteracao.setTitulo(newRequisicao.getTitulo());
            requisicaoAlteracao.setDescricao(newRequisicao.getDescricao());
            requisicaoAlteracao.setSolicitante(newRequisicao.getSolicitante());
            requisicaoAlteracao.setDataSolicitacao(newRequisicao.getDataSolicitacao());
            requisicaoAlteracao.setAnalistaResponsavel(newRequisicao.getAnalistaResponsavel());
            requisicaoAlteracao.setStatus(newRequisicao.getStatus());
            return requisicaoRepository.save(requisicaoAlteracao);
        } else {
            throw new RuntimeException("Requisição de alteração não encontrada com o ID: " + id);
        }
    }

    @SuppressWarnings("null")
    public ResponseDto deleteRequisicao(@PathVariable Long id) {
        if (id >= 0) {
            requisicaoRepository.deleteById(id);
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Requisição de alteração deletada com sucesso");

        return responseDto;
    }
}
