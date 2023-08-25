import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://";
        String user = "teste";
        String password = "1234";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            ClasseXDAO xDAO = new ClasseXDAO(connection);
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("\nMenu:");
                    System.out.println("1) Listar todos os registros");
                    System.out.println("2) Inserir um registro novo");
                    System.out.println("3) Excluir um registro");
                    System.out.println("4) Atualizar um registro");
                    System.out.println("5) Sair");
                    System.out.print("Escolha uma opção: ");

                    int opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer de entrada

                    switch (opcao) {
                        case 1:
                            List<ClasseX> registros = xDAO.listarTodos();
                            for (ClasseX x : registros) {
                                System.out.println(x);
                            }
                            break;
                        case 2:
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer de entrada
                            System.out.print("Email: ");
                            String email = scanner.nextLine();
                            ClasseX novoRegistro = new ClasseX(0, nome, idade, email);
                            xDAO.inserir(novoRegistro);
                            System.out.println("Registro inserido com sucesso.");
                            break;
                        case 3:
                            System.out.print("ID do registro a ser excluído: ");
                            int idExcluir = scanner.nextInt();
                            xDAO.excluir(idExcluir);
                            System.out.println("Registro excluído com sucesso.");
                            break;
                        case 4:
                            System.out.print("ID do registro a ser atualizado: ");
                            int idAtualizar = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer de entrada
                            System.out.print("Novo Nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.print("Nova Idade: ");
                            int novaIdade = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer de entrada
                            System.out.print("Novo Email: ");
                            String novoEmail = scanner.nextLine();
                            ClasseX registroAtualizado = new ClasseX(idAtualizar, novoNome, novaIdade, novoEmail);
                            xDAO.atualizar(registroAtualizado);
                            System.out.println("Registro atualizado com sucesso.");
                            break;
                        case 5:
                            System.out.println("Saindo...");
                            System.exit(0);
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
