package br.ecommerce.pedro.fase1javaspring;

import br.ecommerce.pedro.fase1javaspring.entidades.Produto;
import br.ecommerce.pedro.fase1javaspring.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Fase1JavaSpringApplication implements CommandLineRunner {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(Fase1JavaSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner teclado = new Scanner(System.in);

        while (true) { // O LOOP DO MENU
            System.out.println("╔════════════════════════════╗");
            System.out.println("║       MENU DE OPÇÕES       ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ 1. Criar produto           ║");
            System.out.println("║ 2. Listar os produtos      ║");
            System.out.println("║ 3. Excluir produto         ║");
            System.out.println("║ 4. Sair                    ║");
            System.out.println("╚════════════════════════════╝");;
            System.out.println("╭───────────────────────────╮");
            System.out.println("│ Digite a opção desejada:  │");
            System.out.println("╰───────────────────────────╯");

            int opcao = -1;

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("╭──────────────────────────────────────────╮");
                System.out.println("│ A opção digitada não é um número válido! │");
                System.out.println("╰──────────────────────────────────────────╯");
                continue;
            }

            if (opcao == 1) { // adicionar um produto

                System.out.println("╭──────────────────────────────────────────╮");
                System.out.println("│ Cadastro de Produto ! │");
                System.out.println("╰──────────────────────────────────────────╯");

                System.out.println(" \n Nome: ");
                String nome = teclado.nextLine();
                System.out.println("Descricao: ");
                String descricao = teclado.nextLine();
                System.out.println("Codigo de barra: ");
                String codigoDeBarra = teclado.nextLine();

                Produto produtoCadastrado = new Produto();
                produtoCadastrado.setNome(nome);
                produtoCadastrado.setDescricao(descricao);
                produtoCadastrado.setCodigoDeBarra(codigoDeBarra);

                produtoRepositorio.save(produtoCadastrado);

            } else if (opcao == 2) { // listar os contatos

                for (Produto produtoCadastrado : produtoRepositorio.findAll()) {
                    System.out.println(produtoCadastrado.getId() + " | " + produtoCadastrado.getNome() + " | " +
                            produtoCadastrado.getDescricao() + " | " + produtoCadastrado.getCodigoDeBarra());
                }

            } else if (opcao == 3) {

                // Listando todos os meus produtos primeiro
                for (Produto produtoCadastrado : produtoRepositorio.findAll()) {
                    System.out.println(produtoCadastrado.getId() + " | " + produtoCadastrado.getNome() + "|" +
                            produtoCadastrado.getDescricao() + " | " + produtoCadastrado.getCodigoDeBarra());
                }

                // Acessa o produto para excluir
                System.out.println("Digite o codigo");
                long id = Integer.parseInt(teclado.nextLine());
                Produto produtoCadastrado = produtoRepositorio.findById(id).get();

                // Excluindo produto
                produtoRepositorio.delete(produtoCadastrado);
                System.out.println("Produto excluido");

            } else if (opcao == 4) { // sair
                break; // o BREAK sai do laço
            } else {
                System.out.println("╭──────────────────────────────────────────╮");
                System.out.println("│ Opção inválida !                         │");
                System.out.println("╰──────────────────────────────────────────╯");
            }
        } // FIM DO LOOP DO MENU

        System.out.println("Obrigado por utilizar a aplicação !");
        teclado.close();
    }
}
