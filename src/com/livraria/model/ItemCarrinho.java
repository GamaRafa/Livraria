package com.livraria.model;

public class ItemCarrinho {
  private Livro livro;
  private int quantidade;

  public ItemCarrinho(Livro livro, int quantidade) {
    this.livro = livro;
    this.quantidade = quantidade;
  }

  // Getters
  public Livro getLivro() {
    return livro;
  }

  public int getQuantidade() {
    return quantidade;
  }

  // Setters
  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  // Methods
  public double getSubTotal() {
    return livro.getPreco() * quantidade;
  }

  @Override
  public String toString() {
    return String.format(
      "%d x %s (R$ %.2f)", 
      quantidade, livro.getTitulo(), getSubTotal()
      );
  }
}
