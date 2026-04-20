package com.example.crud.controller;

import com.example.crud.model.Categoria;
import com.example.crud.model.Produto;
import com.example.crud.repository.CategoriaRepository;
import com.example.crud.service.CategoriaService;
import com.example.crud.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaRepository categoriaRepository, CategoriaService categoriaService) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/formulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formCategoria";
    }

    @PostMapping("/salvar")
    public String salvarCategoria(Categoria categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/listar")
    public String listarCategoria(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "listaCategorias";
    }

    @GetMapping("/deletar/{id}")
    public String excluirCategoria(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        model.addAttribute("categoria", categoria.orElse(new Categoria()));
        return "formCategoria";
    }

}
