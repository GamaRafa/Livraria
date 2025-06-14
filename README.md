# Engenharia de Software 2

## Engenharia Reversa

Projeto acadêmico de simulação de uma livraria online utilizando Java e o padrão de arquitetura MVC (Model-View-Controller).

## 📚 Sobre o Projeto

Este sistema simula o funcionamento básico de uma livraria, incluindo:

- Gerenciamento de livros (cadastro, edição e remoção)
- Gerenciamento de carrinho de compras
- Simulação de compra por parte do cliente
- Separação clara entre Model, Service e camada de dados (arquivos `.txt`)

O projeto foi desenvolvido com fins didáticos para a disciplina de Engenharia de Software 2

## 🗂️ Estrutura de Pacotes

```
com.livraria.model // Classes de domínio como Livro e ItemCarrinho
com.livraria.service // Lógica de negócio como GerenciadorLivros e CarrinhoDeCompras
com.livraria.data // Leitura e escrita em arquivos txt
com.livraria // Classe principal (MainApp)
```

## 🗂️ Estrutura de Pastas

```
|- src/
| |- com
| | |- livraria
| | | |- data
| | | | └──


livraria/
├── livros.txt
└── src/
    └── com/
        └── livraria/
            ├── MainApp.java           # Classe principal
            ├── model/                 # Camada de modelo (entidades)
            │   ├── Livro.java
            │   └── ItemCarrinho.java
            ├── service/               # Lógica de negócio
            │   ├── CarrinhoDeCompras.java
            │   └── GerenciadorLivros.java
            └── data/                  # Acesso a arquivos
                └── DadosLivraria.java
└── data
  ├── livros.txt
  ├── carrinho_atual.txt
  ```

## 🔒 Acesso ao Projeto
Este projeto é somente leitura para colegas de classe.
Caso queira sugerir melhorias, faça um fork e abra uma Pull Request.
Contribuições diretas ao repositório original estão desabilitadas.