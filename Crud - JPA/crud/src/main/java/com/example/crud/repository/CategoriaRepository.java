package com.example.crud.repository;

import com.example.crud.model.Categoria;
import com.example.crud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
