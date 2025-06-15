package com.livraria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.livraria.data.DadosLivraria;
import com.livraria.model.Livro;

public class GerenciadorDeLivros {
  private List<Livro> livros;

  public GerenciadorDeLivros() {
    this.livros = DadosLivraria.carregarLivros();
  }

  public List<Livro> getTodosLivros() {
    return livros;
  }

  public Optional<Livro> buscarLivroPorIsbn(String isbn) {
    return livros.stream()
      .filter(l -> l.getIsbn().equals(isbn))
      .findFirst();
  }

  public List<Livro> buscarLivrosPorTitulo(String termo) {
    return livros.stream()
      .filter(l -> l.getTitulo().toLowerCase().contains(termo.toLowerCase()))
      .collect(Collectors.toList());
  }

  public void adicionarLivro(Livro novoLivro) {
    if (!buscarLivroPorIsbn(novoLivro.getIsbn()).isPresent()) {
      livros.add(novoLivro);
      DadosLivraria.salvarLivros(livros);
      System.out.println("Livro adicionado: " + novoLivro.getTitulo());
    } else {
      System.out.println("Erro: Livro com ISBN " + novoLivro.getIsbn() + " já existe");
    }
  }

  public boolean atualizarLivro(String isbn, double novoPreco, int novoEstoque) {
    Optional<Livro> livroOpt = buscarLivroPorIsbn(isbn);
    if (livroOpt.isPresent()) {
      Livro livro = livroOpt.get();
      livro.setPreco(novoPreco);
      livro.setEstoque(novoEstoque);
      DadosLivraria.salvarLivros(livros);
      System.out.println("Livro " + livro.getTitulo() + " atualizado");
      return true;
    } else {
      System.out.println("Erro: Livro com ISBN " + isbn + " não encontrado para atualização");
      return false;
    }
  }

  public boolean removerLivro(String isbn) {
    boolean removido = livros.removeIf(l -> l.getIsbn().equals(isbn));
    if (removido) {
      DadosLivraria.salvarLivros(livros);
      System.out.println("Livro com ISBN " + isbn + " removido");
    } else {
      System.out.println("Erro: Livro com ISBN " + isbn + "não encontrado para remoção");
    }
    return removido;
  }
}
