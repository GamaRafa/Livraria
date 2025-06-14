package com.livraria.model;

public class Livro {
  private String isbn;
  private String titulo;
  private String autor;
  private String categoria;
  private double preco;
  private int estoque;

  public Livro(String isbn, String titulo, String autor, String categoria, double preco, int estoque) {
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.categoria = categoria;
    this.preco = preco;
    this.estoque = estoque;
  }

  // Getters
  public String getIsbn() {
    return isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getAutor() {
    return autor;
  }

  public String getCategoria() {
    return categoria;
  }

  public double getPreco() {
    return preco;
  }

  public int getEstoque() {
    return estoque;
  }

  // Setters
  public void setPreco(double preco) {
    this.preco = preco;
  }

  public void setEstoque(int estoque) {
    this.estoque = estoque;
  }

  // Methods
  @Override
  public String toString() {
    return String.format(
      "ISBN: %s, Título: %s, Autor: %s, Categoria : %s, Preço: R$ %.2f, Estoque: %d",
      isbn, titulo, autor, categoria, preco, estoque
    );
  }
}
