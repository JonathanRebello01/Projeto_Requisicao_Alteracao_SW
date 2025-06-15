package com.api.api_requisicao_alteracao.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "requisicao_alteracao")
public class RequisicaoAlteracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisicao")
    private Long idRequisicao;

    @NotBlank(message = "Título é obrigatório")
    @Length(max = 100, message = "Título com no máximo 100 caracteres")
    @Column(name = "titulo")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "Solicitante é obrigatório")
    @Length(max = 50, message = "Solicitante com no máximo 50 caracteres")
    @Column(name = "solicitante")
    private String solicitante;

    @Column(name = "dataSolicitacao")
    private String dataSolicitacao; 

    @Length(max = 50, message = "Analista com no máximo 50 caracteres")
    @Column(name = "analistaResponsavel")
    private String analistaResponsavel;

    @NotBlank(message = "Status é obrigatório")
    @Length(max = 30, message = "Status com no máximo 30 caracteres")
    @Column(name = "status")
    private String status;
}