package com.livraria;

import com.livraria.model.Livro;
import com.livraria.service.CarrinhoDeCompras;
import com.livraria.service.GerenciadorDeLivros;

public class MainApp {
    public static void main(String[] args) throws Exception {
        GerenciadorDeLivros gerenciadorLivros = new GerenciadorDeLivros();
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras(gerenciadorLivros);

        System.out.println("--- Início da Simulação ---");

        System.out.println("\n--- Ações do Administrador ---");
        System.out.println("Livros disponíveis (antes das ações do admin): ");
        gerenciadorLivros.getTodosLivros().forEach(System.out::println);

        // Simula a adição de um livro
        gerenciadorLivros.adicionarLivro(new Livro("104", "Viagem ao Centro da Terra", "Julio Verne", "Aventura", 38.00, 8));
        // Simula a atualização do preço e estoque de um livro já existente
        gerenciadorLivros.atualizarLivro("101", 40.00, 8);
        // Simula a remoção de um livro
        gerenciadorLivros.removerLivro("102");

        System.out.println("\nLivros disponíveis (depois das ações do admin): ");
        gerenciadorLivros.getTodosLivros().forEach(System.out::println);

        System.out.println("\n--- Ações do Cliente ---");
        carrinho.exibirCarrinho();

        carrinho.adicionarItem("101", 1);
        carrinho.adicionarItem("104", 2);
        carrinho.adicionarItem("103", 1);
        carrinho.adicionarItem("999", 1);   // Não existe

        carrinho.exibirCarrinho();

        carrinho.atualizarQuantidadeItem("101", 2);
        carrinho.removerItem("103");

        carrinho.exibirCarrinho();

        carrinho.finalizarCompra();
        
        carrinho.exibirCarrinho();

        System.out.println("\n--- Fim da Simulação ---");
        System.out.println("Livros disponíveis (após a compra, estoque atualizado): ");
        gerenciadorLivros.getTodosLivros().forEach(System.out::println);
    }
}
