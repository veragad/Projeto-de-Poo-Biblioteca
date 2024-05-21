//Classe Emprestimo
package vera;
import java.io.*;  
import java.time.LocalDate;
import java.util.*;
public class Emprestimo {
    //Atributos 
    private Usuario usuario;
    private Livro livro;
    private Bibliotecario bibliotecario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;
    
    // Lista estática para armazenar os emprestimos, usuario, livros e bibliotecarios
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static ArrayList<Usuario> listaUsuarios;
    private static ArrayList<Livro> listaLivros;
    private static ArrayList<Bibliotecario> listaBibliotecarios;

    // Caminho do arquivo de texto para armazenar os emprestimos
    static final String nomeArquivo = "C:\\Users\\Adm\\OneDrive\\Área de Trabalho\\emprestimo.txt";

    public Emprestimo(Usuario usuario, Livro livro, Bibliotecario bibliotecario, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean devolvido) {
        this.usuario = usuario;
        this.livro = livro;
        this.bibliotecario = bibliotecario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    // Getters e setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public static ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public static void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        Emprestimo.emprestimos = emprestimos;
    }
    
    //menu de emprestimos
    public static void menuEmprestimos(ArrayList<Usuario> listaUsuarios, ArrayList<Livro> listaLivros, ArrayList<Bibliotecario> listaBibliotecarios, ArrayList<Emprestimo> emprestimos) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("+-------------- MENU EMPRÉSTIMOS -------------+");
            System.out.println("| Selecione uma das opções abaixo:            |");
            System.out.println("| 1. Cadastrar Empréstimo                     |");
            System.out.println("| 2. Editar Empréstimo                        |");
            System.out.println("| 3. Excluir Empréstimo                       |");
            System.out.println("| 4. Visualizar Empréstimo por Usuário        |");
            System.out.println("| 5. Visualizar Empréstimo por Bibliotecário  |");
            System.out.println("| 6. Visualizar Empréstimo por Livro          |");
            System.out.println("| 7. Listar Todos os Empréstimos              |");
            System.out.println("| 8. Devolver Livro                           |");
            System.out.println("| 9. Voltar ao Menu Principal                              |");
            System.out.println("+---------------------------------------------+");
            System.out.println();

            String entrada = scanner.nextLine();
            switch (entrada) {
                case "1":
                   cadastrarEmprestimo(listaUsuarios, listaLivros, listaBibliotecarios, emprestimos);

                    break;
                case "2":
                    editarEmprestimo();
                    break;
                case "3":
                    excluirEmprestimo();
                    break;
                case "4":
                    visualizarEmprestimoPorUsuario();
                    break;
                case "5":
                    visualizarEmprestimoPorBibliotecario();
                    break;
                case "6":
                    visualizarEmprestimoPorLivro();
                    break;
                case "7":
                    listarEmprestimos();
                    break;
                case "8":
                    devolverLivro();
                    break;
                case "9":
                    return; // Voltar
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // Método para cadastrar empréstimo
    public static boolean cadastrarEmprestimo(ArrayList<Usuario> listaUsuarios, ArrayList<Livro> listaLivros, ArrayList<Bibliotecario> listaBibliotecarios, ArrayList<Emprestimo> emprestimos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do usuário que vai realizar o empréstimo:");
        String cpf = scanner.nextLine().trim(); // Remover espaços extras

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCpf().equals(cpf)) {
                usuarioEncontrado = usuario;
                break;
            }
        }
        if (usuarioEncontrado == null) {
            System.out.println("Usuário com o CPF '" + cpf + "' não encontrado.");
            System.out.println("Usuários disponíveis:");
            for (Usuario usuario : listaUsuarios) {
                System.out.println("CPF: " + usuario.getCpf() + " - Nome: " + usuario.getNome());
            }
            return false;
        }
        
        System.out.println("Usuário encontrado:");
        System.out.println("CPF: " + usuarioEncontrado.getCpf());
        System.out.println("Nome: " + usuarioEncontrado.getNome());

        System.out.println("Digite o ISBN do livro:");
        String isbnLivro = scanner.nextLine();

        Livro livroEncontrado = null;
        for (Livro livro : listaLivros) {
            if (livro.getIsbn().equals(isbnLivro)) {
                livroEncontrado = livro;
                break;
            }
        }

        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado.");
            return false;
        }

        if (!livroEncontrado.isDisponivel()) {
            System.out.println("Livro não disponível para empréstimo.");
            return false;
        }

        System.out.println("Digite o login do bibliotecário:");
        String loginBibliotecario = scanner.nextLine();

        Bibliotecario bibliotecarioEncontrado = null;
        for (Bibliotecario bibliotecario : listaBibliotecarios) {
            if (bibliotecario.getLogin().equals(loginBibliotecario)) {
                bibliotecarioEncontrado = bibliotecario;
                break;
            }
        }

        if (bibliotecarioEncontrado == null) {
            System.out.println("Bibliotecário não encontrado.");
            return false;
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7); // Prazo de devolução de 7 dias

        Emprestimo emprestimo = new Emprestimo(usuarioEncontrado, livroEncontrado, bibliotecarioEncontrado, dataEmprestimo, dataDevolucao, false);
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso.");
        livroEncontrado.setDisponivel(false); // Marcar livro como indisponível
        return true;
    }


    // Método para editar empréstimo
    public static boolean editarEmprestimo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o cpf do usuário que fez o empréstimo:");
        String cpfUsuario = scanner.nextLine();
        System.out.println("Digite o ISBN do livro emprestado:");
        String isbnLivro = scanner.nextLine();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getNome().equals(cpfUsuario) && emprestimo.getLivro().getIsbn().equals(isbnLivro)) {
                // Aqui você pode adicionar a lógica para editar os detalhes do empréstimo, se necessário
                System.out.println("Empréstimo editado com sucesso.");
                salvarEmprestimo();
                return true;
            }
        }
        System.out.println("Empréstimo não encontrado.");
        return false;
    }
    

    // Método para excluir empréstimo
    public static void excluirEmprestimo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro:");
        String tituloLivro = scanner.nextLine();

        Emprestimo emprestimoEncontrado = null;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getTitulo().equals(tituloLivro)) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado != null) {
            emprestimos.remove(emprestimoEncontrado);
            System.out.println("Empréstimo removido com sucesso!");
        } else {
            System.out.println("Empréstimo não encontrado!");
        }
    }
 // Método para visualizar empréstimos por usuário
    public static void visualizarEmprestimoPorUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF do usuário:");
        String cpfUsuario = scanner.nextLine();

        System.out.println("Empréstimos do usuário '" + cpfUsuario + "':");
        boolean emprestimoEncontrado = false;
        for (Emprestimo emprestimo : emprestimos) {
            // Assumindo que o atributo "cpf" está diretamente acessível no objeto Usuario
            if (emprestimo.getUsuario().getCpf().equals(cpfUsuario)) {
                System.out.println(emprestimo);
                emprestimoEncontrado = true;
            }
        }
        if (!emprestimoEncontrado) {
            System.out.println("Nenhum empréstimo encontrado para o usuário com CPF '" + cpfUsuario + "'.");
        }
    }
    //Método para  visualizar emprestimo por Bibliotecario
    public static void visualizarEmprestimoPorBibliotecario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o login do bibliotecário:");
        String loginBibliotecario = scanner.nextLine();

        System.out.println("Empréstimos realizados pelo bibliotecário '" + loginBibliotecario + "':");
        boolean emprestimoEncontrado = false;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getBibliotecario().getLogin().equals(loginBibliotecario)) {
                System.out.println(emprestimo);
                emprestimoEncontrado = true;
            }
        }
        if (!emprestimoEncontrado) {
            System.out.println("Nenhum empréstimo encontrado realizado pelo bibliotecário com login '" + loginBibliotecario + "'.");
        }
    }

    public static void visualizarEmprestimoPorLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ISBN do livro:");
        String isbnLivro = scanner.nextLine();

        System.out.println("Empréstimos do livro com ISBN '" + isbnLivro + "':");
        boolean emprestimoEncontrado = false;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getIsbn().equals(isbnLivro)) {
                System.out.println(emprestimo);
                emprestimoEncontrado = true;
            }
        }
        if (!emprestimoEncontrado) {
            System.out.println("Nenhum empréstimo encontrado para o livro com ISBN '" + isbnLivro + "'.");
        }
    }

    //Método para listar emprestimos
    public static void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há empréstimos para listar.");
        } else {
            System.out.println("Listando todos os empréstimos:");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    

    //Método para devolver Emprestimo
    public static boolean devolverLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ISBN do livro a ser devolvido:");
        String isbnLivro = scanner.nextLine();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getIsbn().equals(isbnLivro) && !emprestimo.isDevolvido()) {
                emprestimo.setDevolvido(true);
                System.out.println("Livro devolvido com sucesso.");
                return true;
            }
        }

        System.out.println("Livro não encontrado ou já devolvido.");
        return false;
    }

   
    // Método para salvar empréstimos em arquivo
    public static void salvarEmprestimo() {
        try {
            File arquivo = new File(nomeArquivo);
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            System.out.println("Salvando empréstimos no arquivo: " + nomeArquivo);
            FileWriter fileWriter = new FileWriter(arquivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Emprestimo emprestimo : emprestimos) {
                printWriter.println(emprestimo.getUsuario().getCpf() + ";" +
                                     emprestimo.getLivro().getIsbn() + ";" +
                                     emprestimo.getBibliotecario().getLogin() + ";" +
                                     emprestimo.getDataEmprestimo() + ";" +
                                     emprestimo.getDataDevolucao() + ";" +
                                     emprestimo.isDevolvido());
            }
            printWriter.close();
            
            System.out.println("Empréstimos salvos com sucesso no arquivo.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }

    
    // Método para ler empréstimos do arquivo
    public static void lerEmprestimos() {
        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length >= 6) {
                    Usuario usuario = encontrarUsuarioPorCpf(dados[0]);
                    Livro livro = encontrarLivroPorISBN(dados[1]);
                    Bibliotecario bibliotecario = encontrarBibliotecarioPorLogin(dados[2]);
                    LocalDate dataEmprestimo = LocalDate.parse(dados[3]);
                    LocalDate dataDevolucao = LocalDate.parse(dados[4]);
                    boolean devolvido = Boolean.parseBoolean(dados[5]);

                    Emprestimo emprestimo = new Emprestimo(usuario, livro, bibliotecario, dataEmprestimo, dataDevolucao, devolvido);
                    emprestimos.add(emprestimo);
                } else {
                    System.out.println("Formato inválido na linha: " + linha);
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + nomeArquivo);
            e.printStackTrace();
        }
    }

    // Métodos para encontrar usuário, livro e bibliotecário
    private static Usuario encontrarUsuarioPorCpf(String cpf) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    private static Livro encontrarLivroPorISBN(String isbn) {
        for (Livro livro : listaLivros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    private static Bibliotecario encontrarBibliotecarioPorLogin(String login) {
        for (Bibliotecario bibliotecario : listaBibliotecarios) {
            if (bibliotecario.getLogin().equals(login)) {
                return bibliotecario;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario +
                ", livro=" + livro +
                ", bibliotecario=" + bibliotecario +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", devolvido=" + devolvido +
                '}';
    }
}
