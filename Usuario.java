package vera;
import java.io.*;  
import java.util.*;

public class Usuario {
	    // Atributos privados da classe
	    private String nome;
	    private String cpf;
	    private String senha;
	    private int limiteDeEmprestimos;
        
	    // Lista estática para armazenar os usuarios
	    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	   
	    // Caminho do arquivo de texto para armazenar os usuarios
	    static final String nomeArquivo = "C:\\Users\\Adm\\OneDrive\\Área de Trabalho\\usuario.txt";

	    //construtor
	    public Usuario(String nome, String cpf, String senha, int limiteDeEmprestimos) {
	        this.nome = nome;
	        this.cpf = cpf;
	        this.senha = senha;
	        this.limiteDeEmprestimos = limiteDeEmprestimos;
	    }

	    // Getters e setters
	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getCpf() {
	        return cpf;
	    }

	    public void setCpf(String cpf) {
	        this.cpf = cpf;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public int getLimiteDeEmprestimos() {
	        return limiteDeEmprestimos;
	    }

	    public void setLimiteDeEmprestimos(int limiteDeEmprestimos) {
	        this.limiteDeEmprestimos = limiteDeEmprestimos;
	    }

	    public static ArrayList<Usuario> getListaUsuarios() {
	        return listaUsuarios;
	    }

	    public static void setUsuarios(ArrayList<Usuario> listaUsuarios) {
	        Usuario.listaUsuarios = listaUsuarios;
	    }

	    
	   // menu do usuario
	    public static void menuUsuarios() {
	    	Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("+-------------- MENU USUÁRIOS ------------+");
	            System.out.println("| Selecione uma das opções abaixo:         |");
	            System.out.println("| 1. Cadastrar Usuário                     |");
	            System.out.println("| 2. Editar Usuário                        |");
	            System.out.println("| 3. Excluir Usuário                       |");
	            System.out.println("| 4. Visualizar Usuário                    |");
	            System.out.println("| 5. Listar Usuários                       |");
	            System.out.println("| 6. Voltar ao Menu Principal              |");
	            System.out.println("+-----------------------------------------+");
	            System.out.println();

	            String entrada = scanner.nextLine();

	            switch (entrada) {
	                case "1":
	                    cadastrarUsuario();
	                    break;
	                case "2":
	                    editarUsuario();
	                    break;
	                case "3":
	                    excluirUsuario();
	                    break;
	                case "4":
	                    visualizarUsuario();
	                    break;
	                case "5":
	                    listarUsuarios();
	                    break;
	                case "6":
	                    return; // Voltar
	                default:
	                    System.out.println("Opção inválida!");
	            }
	         
	        }
	        
	    }

	 
	    //Método pra cadastrar usuario
	    public static boolean cadastrarUsuario() {
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("Digite o nome do usuário:");
	            String nome = scanner.nextLine();

	            System.out.println("Digite o CPF do usuário:");
	            String cpf = scanner.nextLine();

	            boolean cpfExistente = false;
	            for (Usuario usuario : Usuario.listaUsuarios) {
	                if (usuario.getCpf().equals(cpf)) {
	                    System.out.println("Já existe um usuário cadastrado com o mesmo CPF.");
	                    cpfExistente = true;
	                    break;
	                }
	            }

	            if (!cpfExistente) {
	                System.out.println("Digite a senha do usuário:");
	                String senha = scanner.nextLine();
	                System.out.println("Digite o limite de empréstimos do usuário:");
	                int limiteDeEmprestimos = scanner.nextInt();
	                scanner.nextLine(); // Consumir a quebra de linha pendente

	                boolean sucesso = listaUsuarios.add(new Usuario(nome, cpf, senha, limiteDeEmprestimos));
	                if (sucesso) {
	                    salvarUsuarios();
	                    System.out.println("Usuário cadastrado com sucesso.");
	                }
	                return sucesso;
	            }
	        }
	    }

	    //Método para editar usuario
	    public static boolean editarUsuario() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Digite o CPF do usuário que deseja editar:");
	        String cpf = scanner.nextLine();

	        for (Usuario usuario : Usuario.listaUsuarios) {
	            if (usuario.getCpf().equals(cpf)) {
	                System.out.println("Digite o novo nome do usuário:");
	                String novoNome = scanner.nextLine();
	                System.out.println("Digite a nova senha do usuário:");
	                String novaSenha = scanner.nextLine();
	                System.out.println("Digite o novo limite de empréstimos do usuário:");
	                int novoLimiteDeEmprestimos = scanner.nextInt();
	                scanner.nextLine(); // Consumir a quebra de linha pendente

	                usuario.setNome(novoNome);
	                usuario.setSenha(novaSenha);
	                usuario.setLimiteDeEmprestimos(novoLimiteDeEmprestimos);
	                salvarUsuarios();
	                System.out.println("Usuário editado com sucesso.");
	                return true;
	            }
	        }
	        System.out.println("Usuário não encontrado.");
	        return false;
	    }

	    //Metodo para excluir usuario
	    public static boolean excluirUsuario() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Digite o CPF do usuário que deseja excluir:");
	        String cpf = scanner.nextLine();

	        for (Usuario usuario : Usuario.listaUsuarios) {
	            if (usuario.getCpf().equals(cpf)) {
	                System.out.println("Tem certeza que deseja excluir o usuário " + usuario.getNome() + "? (s/n)");
	                String confirmacao = scanner.nextLine().toLowerCase();

	                if (confirmacao.equals("s")) {
	                    Usuario.listaUsuarios.remove(usuario);
	                    System.out.println("Usuário " + usuario.getNome() + " excluído com sucesso.");
	                    salvarUsuarios();
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
	        System.out.println("Usuário não encontrado.");
	        return false;
	    }

	   //Metodo para visualizar usuario
	    public static void visualizarUsuario() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Digite o CPF do usuário que deseja visualizar:");
	        String cpf = scanner.nextLine();

	        for (Usuario usuario : Usuario.listaUsuarios) {
	            if (usuario.getCpf().equals(cpf)) {
	                System.out.println("Usuário encontrado:");
	                System.out.println(usuario);
	                return;
	            }
	        }
	        System.out.println("Usuário não encontrado.");
	    }

	   //Metodo para listar usuarios
	    public static void listarUsuarios() {
	        if (Usuario.listaUsuarios.isEmpty()) {
	            System.out.println("Não há usuários cadastrados para serem listados.");
	        } else {
	            System.out.println("Listando Usuários:");
	            int contador = 1;
	            for (Usuario usuario : Usuario.listaUsuarios) {
	                System.out.println(contador + " - " + usuario.toString());
	                contador++;
	            }
	        }
	    }

	    
	  //Metodo para salvar usuario
	    public static void salvarUsuarios() {
	        try {
	            System.out.println("Salvando usuários no arquivo: " + nomeArquivo);
	            FileWriter fileWriter = new FileWriter(nomeArquivo);
	            PrintWriter printWriter = new PrintWriter(fileWriter);

	            for (Usuario usuario : Usuario.listaUsuarios) {
	                printWriter.println(usuario.getNome() + ";" + usuario.getCpf() + ";" + usuario.getSenha() + ";"
	                        + usuario.getLimiteDeEmprestimos());
	            }
	            printWriter.close();
	            System.out.println("Usuários salvos com sucesso no arquivo.");

	        } catch (IOException e) {
	            System.out.println("Ocorreu um erro ao salvar os dados no arquivo: " + nomeArquivo + "\n");
	            e.printStackTrace();
	        }
	    }

	    //Metodo para ler usuarios
	    public static void lerUsuarios() {
	        try {
	            FileReader fileReader = new FileReader(nomeArquivo);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            String linha;
	            while ((linha = bufferedReader.readLine()) != null) {
	                String[] dados = linha.split(";");

	                if (dados.length >= 4) {
	                    Usuario usuario = new Usuario(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
	                    Usuario.listaUsuarios.add(usuario);
	                } else {
	                    System.out.println("Formato inválido na linha: " + linha);
	                }
	            }
	            bufferedReader.close();

	            salvarUsuarios();

	        } catch (IOException e) {
	            System.out.println("Ocorreu um erro ao ler o arquivo: " + nomeArquivo + "\n");
	            e.printStackTrace();
	        } catch (NumberFormatException e) {
	            System.out.println("Erro ao converter número na leitura do arquivo: " + nomeArquivo + "\n");
	            e.printStackTrace();
	        }
	    }

	 
	    @Override
	    public String toString() {
	        return "Usuario{" +
	                "nome='" + nome + '\'' +
	                ", cpf='" + cpf + '\'' +
	                ", senha='" + senha + '\'' +
	                ", limiteDeEmprestimos=" + limiteDeEmprestimos +
	                '}';
	    }
}
