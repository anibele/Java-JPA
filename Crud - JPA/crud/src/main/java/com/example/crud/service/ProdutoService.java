package com.example.crud.service;

import com.example.crud.model.Produto;
import com.example.crud.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvar(Produto produto){
        if(produto.getNome()==null || produto.getNome().isBlank()){
            throw new RuntimeException("Preencha o nome do produto");
        }
        if(produto.getValor() <=0){
            throw new RuntimeException("Preencha o valor do produto para ser maior que zero");
        }
        if(produto.getCategoria() == null || produto.getCategoria().getId() == null){
            throw new RuntimeException("Selecione uma categoria para o produto");
        }

        produtoRepository.save(produto);
    }
}
