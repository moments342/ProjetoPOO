package model;

import java.util.Date;
import java.util.Map;

public class Venda {
    private Date data;
    private Map<Produto, Integer> produtosVendidos;
    private float valorTotal;
    private String cliente;
    private String vendedor;

    public Venda(Date data, Map<Produto, Integer> produtosVendidos, String cliente, String vendedor) {
        this.data = data;
        this.produtosVendidos = produtosVendidos;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.valorTotal = calcularValorTotal();
    }

    private float calcularValorTotal() {
        float total = 0;
        for (Map.Entry<Produto, Integer> item : produtosVendidos.entrySet()) {
            total += item.getKey().getPreco() * item.getValue();
        }
        return total;
    }

    public Date getData() { return data; }
    public Map<Produto, Integer> getProdutosVendidos() { return produtosVendidos; }
    public float getValorTotal() { return valorTotal; }
    public String getCliente() { return cliente; }
    public String getVendedor() { return vendedor; }

    @Override
    public String toString() {
        return String.format("Venda[Data: %s, Cliente: %s, Vendedor: %s, Total: %.2f, Produtos: %s]",
                data, cliente, vendedor, valorTotal, produtosVendidos.toString());
    }
}
