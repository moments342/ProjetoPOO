package view;

import model.Categoria;
import model.Produto;
import model.Venda;
import service.EstoqueService;
import service.ProdutoService;
import service.VendaService;

import java.util.*;

public class MenuView {
    private ProdutoService produtoService;
    private EstoqueService estoqueService;
    private VendaService vendaService;

    public MenuView(ProdutoService produtoService, EstoqueService estoqueService, VendaService vendaService) {
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
        this.vendaService = vendaService;
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Sistema de Controle de Estoque ---");
            System.out.println("1. Menu de Produtos");
            System.out.println("2. Menu de Vendas");
            System.out.println("3. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = validarEntrada(scanner);
            scanner.nextLine(); // Limpa o buffer após a entrada

            switch (opcao) {
                case 1 -> exibirMenuProdutos(scanner);
                case 2 -> exibirMenuVendas(scanner);
                case 3 -> exibirMenuRelatorios(scanner);
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenuProdutos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu de Produtos ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Filtrar Produtos por Categoria");
            System.out.println("4. Exibir Produtos com Estoque Baixo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = validarEntrada(scanner);
            scanner.nextLine(); // Limpa o buffer após a entrada

            switch (opcao) {
                case 1:
                    adicionarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    filtrarProdutosPorCategoria(scanner);
                    break;
                case 4:
                    exibirProdutosComEstoqueBaixo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenuVendas(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu de Vendas ---");
            System.out.println("1. Registrar Venda");
            System.out.println("2. Listar Histórico de Vendas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = validarEntrada(scanner);
            scanner.nextLine(); // Limpa o buffer após a entrada

            switch (opcao) {
                case 1:
                    registrarVenda(scanner);
                    break;
                case 2:
                    listarHistoricoVendas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenuRelatorios(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu de Relatórios ---");
            System.out.println("1. Relatório de Estoque");
            System.out.println("2. Relatório de Estoque por Categoria");
            System.out.println("3. Relatório de Vendas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = validarEntrada(scanner);
            scanner.nextLine(); // Limpa o buffer após a entrada

            switch (opcao) {
                case 1:
                    gerarRelatorioEstoque();
                    break;
                case 2:
                    gerarRelatorioEstoquePorCategoria(scanner);
                    break;
                case 3:
                    gerarRelatorioVendas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarProduto(Scanner scanner) {
        System.out.println("Digite o nome do produto:");
        String nome = scanner.nextLine();

        System.out.println("Digite o código:");
        String codigo = scanner.nextLine();

        System.out.println("Selecione a categoria:");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d. %s%n", cat.ordinal(), cat.name());
        }
        int categoriaIndex = validarEntrada(scanner);
        scanner.nextLine(); // Limpa o buffer
        Categoria categoria = Categoria.values()[categoriaIndex];

        System.out.println("Digite a quantidade:");
        int quantidade = validarEntrada(scanner);
        scanner.nextLine();

        System.out.println("Digite a quantidade mínima:");
        int quantidadeMinima = validarEntrada(scanner);
        scanner.nextLine();

        System.out.println("Digite o preço:");
        float preco = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Digite o fornecedor:");
        String fornecedor = scanner.nextLine();

        Produto produto = new Produto(nome, codigo, categoria, quantidade, quantidadeMinima, preco, fornecedor);
        if (produtoService.adicionarProduto(produto)) {
            System.out.println("Produto adicionado com sucesso!");
        }
    }

    private void listarProdutos() {
        System.out.println("\nLista de Produtos:");
        produtoService.listarProdutos().forEach(System.out::println);
    }

    private void filtrarProdutosPorCategoria(Scanner scanner) {
        System.out.println("Selecione a categoria (ou 0 para voltar):");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d. %s%n", cat.ordinal() + 1, cat.name());
        }

        int categoriaIndex;
        while (true) {
            categoriaIndex = validarEntrada(scanner);
            if (categoriaIndex == 0) return;
            if (categoriaIndex > 0 && categoriaIndex <= Categoria.values().length) break;

            System.out.println("Categoria inválida. Digite novamente ou 0 para voltar.");
        }

        Categoria categoria = Categoria.values()[categoriaIndex - 1];
        List<Produto> produtos = estoqueService.filtrarPorCategoria(
                new ArrayList<>(produtoService.listarProdutos()), categoria);
        System.out.println("\nProdutos na categoria " + categoria + ":");
        produtos.forEach(System.out::println);
    }

    private void exibirProdutosComEstoqueBaixo() {
        System.out.println("\nProdutos com Estoque Baixo:");
        List<Produto> produtos = estoqueService.filtrarPorQuantidadeMinima(
                new ArrayList<>(produtoService.listarProdutos()));
        produtos.forEach(System.out::println);
    }

    private void registrarVenda(Scanner scanner) {
        System.out.println("\nDigite o nome do cliente:");
        String cliente = scanner.nextLine();

        System.out.println("Digite o nome do vendedor:");
        String vendedor = scanner.nextLine();

        Map<Produto, Integer> produtosVendidos = new HashMap<>();
        while (true) {
            System.out.println("Digite o código do produto (ou '0' para finalizar):");
            String codigo = scanner.nextLine();
            if (codigo.equals("0")) break;

            Produto produto = produtoService.buscarProduto(codigo);
            if (produto == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.println("Digite a quantidade vendida:");
            int quantidade = validarEntrada(scanner);
            scanner.nextLine();

            try {
                estoqueService.atualizarEstoque(produto, quantidade);
                produtosVendidos.put(produto, quantidade);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!produtosVendidos.isEmpty()) {
            Venda venda = new Venda(new Date(), produtosVendidos, cliente, vendedor);
            vendaService.registrarVenda(venda);
            System.out.println("Venda registrada com sucesso!");
        } else {
            System.out.println("Nenhuma venda registrada.");
        }
    }

    private void listarHistoricoVendas() {
        System.out.println("\nHistórico de Vendas:");
        vendaService.listarHistoricoVendas().forEach(System.out::println);
    }

    private void gerarRelatorioEstoque() {
        System.out.println("\nRelatório de Estoque:");
        produtoService.listarProdutos().forEach(System.out::println);
    }

    private void gerarRelatorioEstoquePorCategoria(Scanner scanner) {
        System.out.println("Selecione a categoria:");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d. %s%n", cat.ordinal(), cat.name());
        }
        int categoriaIndex = validarEntrada(scanner);
        scanner.nextLine();
        Categoria categoria = Categoria.values()[categoriaIndex];
        List<Produto> produtos = estoqueService.filtrarPorCategoria(
                new ArrayList<>(produtoService.listarProdutos()), categoria);
        System.out.println("\nProdutos na categoria " + categoria + ":");
        produtos.forEach(System.out::println);
    }

    private void gerarRelatorioVendas() {
        System.out.println("\nRelatório de Vendas:");
        vendaService.listarHistoricoVendas().forEach(System.out::println);
    }

    private int validarEntrada(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}