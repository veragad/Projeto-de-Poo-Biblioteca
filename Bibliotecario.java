//Classe Bibliotecario
package vera;
import java.io.*;  
import java.util.*;
import java.util.Scanner;
public class Bibliotecario {
	// Atributos privados da classe Bibliotecario
	private String nome;
    private String login;
    private String senha;
    
    // Lista estática para armazenar os bibliotecarios
    private static ArrayList<Bibliotecario> listaBibliotecarios = new ArrayList<>();
    
   // Caminho do arquivo de texto para armazenar os bibliotecarios
    static final String nomeArquivo = "C:\\Users\\Adm\\OneDrive\\Área de Trabalho\\bibliotecario.txt";

    //Construtor da classe Bibliotecario
    public Bibliotecario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static ArrayList<Bibliotecario> getListaBibliotecarios() {
        return listaBibliotecarios;
    }

    public static void setBibliotecarios(ArrayList<Bibliotecario> listaBibliotecarios) {
        Bibliotecario.listaBibliotecarios = listaBibliotecarios;
    }

    //menu de bibliotecarios
    public static void menuBibliotecarios() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("+-------------- MENU BIBLIOTECÁRIOS --------------+");
            System.out.println("| Selecione uma das opções abaixo:                |");
            System.out.println("| 1. Cadastrar Bibliotecário                      |");
            System.out.println("| 2. Editar Bibliotecário                         |");
            System.out.println("| 3. Excluir Bibliotecário                        |");
            System.out.println("| 4. Visualizar Bibliotecário                     |");
            System.out.println("| 5. Listar Bibliotecários                        |");
            System.out.println("| 6. Voltar ao Menu Principal                     |");
            System.out.println("+-------------------------------------------------+");
            System.out.println();

            String entrada = scanner.nextLine();

            switch (entrada) {
                case "1":
                    cadastrarBibliotecario();
                    break;
                case "2":
                    editarBibliotecario();
                    break;
                case "3":
                    excluirBibliotecario();
                    break;
                case "4":
                    visualizarBibliotecario();
                    break;
                case "5":
                    listarBibliotecarios();
                    break;
                case "6":
                    return; // Voltar
                default:
                    System.out.println("Opção inválida!");
            }
        }        
    }

    //Método para cadastrar bibliotecario
    public static boolean cadastrarBibliotecario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do bibliotecário:");
        String nome = scanner.nextLine();

        System.out.println("Digite o login do bibliotecário:");
        String login = scanner.nextLine();

        for (Bibliotecario bibliotecario : Bibliotecario.listaBibliotecarios) {
            if (bibliotecario.getLogin().equals(login)) {
                System.out.println("Já existe um bibliotecário cadastrado com o mesmo login.");
                return false;
            }
        }

        System.out.println("Digite a senha do bibliotecário:");
        String senha = scanner.nextLine();

        boolean sucesso = listaBibliotecarios.add(new Bibliotecario(nome, login, senha));
        if (sucesso) {
            salvarBibliotecarios();
            System.out.println("Bibliotecário cadastrado com sucesso.");
        }
        return sucesso;
       
    }

    //Método para editar bibliotecario
    public static boolean editarBibliotecario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o login do bibliotecário que deseja editar:");
        String login = scanner.nextLine();

        for (Bibliotecario bibliotecario : Bibliotecario.listaBibliotecarios) {
            if (bibliotecario.getLogin().equals(login)) {
                System.out.println("Digite o novo nome do bibliotecário:");
                String novoNome = scanner.nextLine();
                System.out.println("Digite a nova senha do bibliotecário:");
                String novaSenha = scanner.nextLine();

                bibliotecario.setNome(novoNome);
                bibliotecario.setSenha(novaSenha);
                salvarBibliotecarios();
                System.out.println("Bibliotecário editado com sucesso.");
                return true;
            }
        }
        System.out.println("Bibliotecário não encontrado.");
        return false;
    }

    //Método para ecluir bibliotecario
    public static boolean excluirBibliotecario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o login do bibliotecário que deseja excluir:");
        String login = scanner.nextLine();

        for (Bibliotecario bibliotecario : Bibliotecario.listaBibliotecarios) {
            if (bibliotecario.getLogin().equals(login)) {
                System.out.println("Tem certeza que deseja excluir o bibliotecário " + bibliotecario.getNome() + "? (s/n)");
                String confirmacao = scanner.nextLine().toLowerCase();

                if (confirmacao.equals("s")) {
                    Bibliotecario.listaBibliotecarios.remove(bibliotecario);
                    System.out.println("Bibliotecário " + bibliotecario.getNome() + " excluído com sucesso.");
                    salvarBibliotecarios();
                    return true;

                } else if (confirmacao.equals("n")) {
                    System.out.println("Exclusão cancelada.");
                    return false;
                } else {
                    System.out.println("Opção inválida. Exclusão cancelada.");
                    return false;
                }
            }
        }
        System.out.println("Bibliotecário não encontrado.");
        return false;
    }
    
    //Método para visualizar bibliotecario
    public static void visualizarBibliotecario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o login do bibliotecário que deseja visualizar:");
        String login = scanner.nextLine();

        for (Bibliotecario bibliotecario : Bibliotecario.listaBibliotecarios) {
            if (bibliotecario.getLogin().equals(login)) {
                System.out.println("Bibliotecário encontrado:");
                System.out.println(bibliotecario);
                return;
            }
        }
        System.out.println("Bibliotecário não encontrado.");
    }

   //Método para listar bibliotecario
    public static void listarBibliotecarios() {
        if (Bibliotecario.listaBibliotecarios.isEmpty()) {
            System.out.println("Não há bibliotecários cadastrados para serem listados.");
        } else {
            System.out.println("Listando Bibliotecários:");
            int contador = 1;
            for (Bibliotecario bibliotecario : Bibliotecario.listaBibliotecarios) {
                System.out.println(contador + " - " + bibliotecario.toString());
                contador++;
            }
        }
    }

    //Método para salvar bibliotecario
    public static void salvarBibliotecarios() {
        try {
            System.out.println("Salvando bibliotecários no arquivo: " + nomeArquivo);
            FileWriter fileWriter = new FileWriter(nomeArquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Bibliotecario bibliotecario : Bibliotecario.listaBibliotecarios) {
                printWriter.println(bibliotecario.getNome() + ";" + bibliotecario.getLogin() + ";" + bibliotecario.getSenha());
            }
            printWriter.close();
            System.out.println("Bibliotecários salvos com sucesso no arquivo.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo: " + nomeArquivo + "\n");
            e.printStackTrace();
        }
    }

    //Método para ler bibliotecario
    public static void lerBibliotecarios() {
        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length >= 3) {
                    Bibliotecario bibliotecario = new Bibliotecario(dados[0], dados[1], dados[2]);
                    Bibliotecario.listaBibliotecarios.add(bibliotecario);
                } else {
                    System.out.println("Formato inválido na linha: " + linha);
                }
            }
            bufferedReader.close();

            salvarBibliotecarios();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + nomeArquivo + "\n");
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "Bibliotecario{" +
                "nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
    
}
