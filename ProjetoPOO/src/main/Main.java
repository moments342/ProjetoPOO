package main;

import service.EstoqueService;
import service.ProdutoService;
import service.VendaService;
import view.MenuView;

public class Main {
    public static void main(String[] args) {
        // Inicializando os serviços
        ProdutoService produtoService = new ProdutoService();
        EstoqueService estoqueService = new EstoqueService();
        VendaService vendaService = new VendaService();

        // Criando a interface do menu
        MenuView menuView = new MenuView(produtoService, estoqueService, vendaService);

        // Exibindo o menu principal
        menuView.exibirMenu();
    }
}
