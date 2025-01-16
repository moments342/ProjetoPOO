package service;

import model.Categoria;
import model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class EstoqueService {
    public void atualizarEstoque(Produto produto, int quantidadeVendida) {
        if (produto.getQuantidadeEstoque() < quantidadeVendida) {
            throw new IllegalArgumentException("Erro: Quantidade maior que o estoque disponível.");
        }
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadeVendida);
    }

    public boolean alertaEstoqueBaixo(Produto produto) {
        return produto.getQuantidadeEstoque() < produto.getQuantidadeMinima();
    }

    public List<Produto> filtrarPorCategoria(List<Produto> produtos, Categoria categoria) {
        return produtos.stream()
                .filter(produto -> produto.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    public List<Produto> filtrarPorQuantidadeMinima(List<Produto> produtos) {
        return produtos.stream()
                .filter(this::alertaEstoqueBaixo)
                .collect(Collectors.toList());
    }
}
