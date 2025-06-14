package com.livraria.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.livraria.model.ItemCarrinho;
import com.livraria.model.Livro;

public class DadosLivraria {
  private static final String ARQUIVO_LIVROS = "data/livros.txt";
  private static final String ARQUIVO_CARRINHO = "data/carrinho_atual.txt";

  public static List<Livro> carregarLivros() {
    List<Livro> livros = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_LIVROS))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] partes = linha.split(";");
        if (partes.length == 6) {
          livros.add(new Livro(
            partes[0], partes[1], partes[2], partes[3], Double.parseDouble(partes[4]), Integer.parseInt(partes[5])
          ));
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao carregar livros: " + e.getMessage());
    }
    return livros;
  }

  public static void salvarLivros(List<Livro> livros) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_LIVROS))) {
      for (Livro livro : livros) {
        bw.write(livro.getIsbn() + ";" + livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getCategoria() + ";" + livro.getPreco() + ";" + livro.getEstoque());
        bw.newLine();
      }
    } catch (IOException e) {
      System.err.println("Erro ao salvar livros: " + e.getMessage());
    }
  }

  public static List<ItemCarrinho> carregarCarrinho(List<Livro> todosLivros) {
    List<ItemCarrinho> carrinho = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CARRINHO))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] partes = linha.split(";");
        if (partes.length == 2) {
          String isbn = partes[0];
          int quantidade = Integer.parseInt(partes[1]);
          Optional<Livro> livroOpt = todosLivros.stream().filter(l -> l.getIsbn().equals(isbn)).findFirst();
          livroOpt.ifPresent(livro -> carrinho.add(new ItemCarrinho(livro, quantidade)));
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao carregar carrinho: " + e.getMessage());
    }
    return carrinho;
  }

  public static void salvarCarrinho(List<ItemCarrinho> carrinho) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CARRINHO))) {
      for (ItemCarrinho item : carrinho) {
        bw.write(item.getLivro().getIsbn() + ";" + item.getQuantidade());
        bw.newLine();
      }
    } catch (IOException e) {
      System.err.println("Erro ao salvar carrinho: " + e.getMessage());
    }
  }
}
