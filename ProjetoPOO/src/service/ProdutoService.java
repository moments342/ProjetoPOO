package service;

import model.Produto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProdutoService {
    private Map<String, Produto> produtos = new HashMap<>();

    public boolean adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getCodigo())) {
            System.out.println("Erro: Produto com este código já existe!");
            return false;
        }
        produtos.put(produto.getCodigo(), produto);
        return true;
    }

    public boolean editarProduto(String codigo, Produto produtoAtualizado) {
        if (!produtos.containsKey(codigo)) {
            System.out.println("Erro: Produto não encontrado!");
            return false;
        }
        produtos.put(codigo, produtoAtualizado);
        return true;
    }

    public boolean excluirProduto(String codigo) {
        if (!produtos.containsKey(codigo)) {
            System.out.println("Erro: Produto não encontrado!");
            return false;
        }
        produtos.remove(codigo);
        return true;
    }

    public Produto buscarProduto(String codigo) {
        return produtos.get(codigo);
    }

    public Collection<Produto> listarProdutos() {
        return produtos.values();
    }
}
