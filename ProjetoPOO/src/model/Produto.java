package model;

public class Produto {
    private String nome;
    private String codigo;
    private Categoria categoria;
    private int quantidadeEstoque;
    private int quantidadeMinima;
    private float preco;
    private String fornecedor;

    public Produto(String nome, String codigo, Categoria categoria, int quantidadeEstoque,
                   int quantidadeMinima, float preco, String fornecedor) {
        this.nome = nome;
        this.codigo = codigo;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public Categoria getCategoria() { return categoria; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public int getQuantidadeMinima() { return quantidadeMinima; }
    public float getPreco() { return preco; }
    public String getFornecedor() { return fornecedor; }

    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public void setQuantidadeMinima(int quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return String.format("Produto[%s, Código: %s, Categoria: %s, Estoque: %d, Mínimo: %d, Preço: %.2f, Fornecedor: %s]",
                nome, codigo, categoria, quantidadeEstoque, quantidadeMinima, preco, fornecedor);
    }
}
