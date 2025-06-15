package com.api.api_requisicao_alteracao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.api_requisicao_alteracao.Entity.RequisicaoAlteracao;

public interface RequisicaoRepository extends JpaRepository<RequisicaoAlteracao, Long>{
    
}
