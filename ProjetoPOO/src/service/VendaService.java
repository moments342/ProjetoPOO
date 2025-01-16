package service;

import model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaService {
    private List<Venda> historicoVendas = new ArrayList<>();

    public void registrarVenda(Venda venda) {
        historicoVendas.add(venda);
    }

    public List<Venda> listarHistoricoVendas() {
        return historicoVendas;
    }
}
