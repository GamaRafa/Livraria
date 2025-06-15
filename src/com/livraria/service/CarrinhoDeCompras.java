package com.livraria.service;

import java.util.List;
import java.util.Optional;

import com.livraria.data.DadosLivraria;
import com.livraria.model.ItemCarrinho;
import com.livraria.model.Livro;

public class CarrinhoDeCompras {
  private List<ItemCarrinho> itens;
  private GerenciadorDeLivros gerenciadorDeLivros; 

  public CarrinhoDeCompras(GerenciadorDeLivros gerenciadorDeLivros) {
    this.gerenciadorDeLivros = gerenciadorDeLivros;
    this.itens = DadosLivraria.carregarCarrinho(gerenciadorDeLivros.getTodosLivros());
  }

  public List<ItemCarrinho> getItens() {
    return itens;
  }

  public void adicionarItem(String isbn, int quantidade) {
    Optional<Livro> livroOpt = gerenciadorDeLivros.buscarLivroPorIsbn(isbn);
    if (livroOpt.isPresent()) {
      Livro livro = livroOpt.get();
      if (livro.getEstoque() >= quantidade) {
        Optional<ItemCarrinho> itemExistente = itens.stream()
          .filter(item -> item.getLivro().getIsbn().equals(isbn))
          .findFirst();
        if (itemExistente.isPresent()) {
          itemExistente.get().setQuantidade(itemExistente.get().getQuantidade() + quantidade);
        } else {
          itens.add(new ItemCarrinho(livro, quantidade));
        }
        System.out.println(quantidade + "x " + livro.getTitulo() + " adicionado ao carrinho");
        DadosLivraria.salvarCarrinho(itens);
      } else {
        System.out.println("Estoque insuficiente para " + livro.getTitulo() + ". Disponível: " + livro.getEstoque());
      }
    } else {
      System.out.println("Livro com ISBN " + isbn + " não encontrado");
    }
  }

  public void removerItem(String isbn) {
    boolean removido = itens.removeIf(item -> item.getLivro().getIsbn().equals(isbn));
    if (removido) {
      System.out.println("Item com ISBN " + isbn + " removido do carrinho");
      DadosLivraria.salvarCarrinho(itens);
    } else {
      System.out.println("Item com ISBN " + isbn + " não encontrado no carrinho");
    }
  }

  public void atualizarQuantidadeItem(String isbn, int novaQuantidade) {
    if (novaQuantidade <= 0) {
      removerItem(isbn);
      return;
    }

    Optional<ItemCarrinho> itemOpt = itens.stream()
      .filter(item -> item.getLivro().getIsbn().equals(isbn))
      .findFirst();
    if (itemOpt.isPresent()) {
      ItemCarrinho item = itemOpt.get();
      if (item.getLivro().getEstoque() >= novaQuantidade) {
        item.setQuantidade(novaQuantidade);
        System.out.println("Quantidade de " + item.getLivro().getTitulo() + " atualizado para " + novaQuantidade);
        DadosLivraria.salvarCarrinho(itens);
      } else {
        System.out.println("Estoque insuficiente para " + item.getLivro().getTitulo() + ". Disponível: " + item.getLivro().getEstoque());
      }
    } else {
      System.out.println("Item com ISBN " + isbn + " não encontrado no carrinho");
    }
  }

  public double calcularTotal() {
    return itens.stream().mapToDouble(ItemCarrinho::getSubTotal).sum();
  }

  public void exibirCarrinho() {
    if (itens.isEmpty()) {
      System.out.println("Seu carrinho está vazio");
      return;
    }

    System.out.println("\n--- Seu Carrinho ---");
    itens.forEach(System.out::println);
    System.out.println("Total do carrinho: R$ " + String.format("%.2f", calcularTotal()));
    System.out.println("--------------------");
  }

  public void finalizarCompra() {
    if (itens.isEmpty()) {
      System.out.println("Não há itens para finalizar a compra");
      return;
    }

    System.out.println("\nFinalizando compra...");
    for (ItemCarrinho item : itens) {
      Livro livro = item.getLivro();
      gerenciadorDeLivros.atualizarLivro(livro.getIsbn(), livro.getPreco(), livro.getEstoque() - item.getQuantidade());
    }
    itens.clear();
    DadosLivraria.salvarCarrinho(itens);
    System.out.println("Compra finalizada com sucesso! Seu carrinho foi esvaziado");
  }
}
