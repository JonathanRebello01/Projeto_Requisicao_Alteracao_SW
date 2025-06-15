package com.api.api_requisicao_alteracao.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoDto {
    private Long idRequisicao;
    private String titulo;
    private String descricao;
    private String solicitante;
    private String dataSolicitacao;
    private String analistaResponsavel;
    private String status;
}