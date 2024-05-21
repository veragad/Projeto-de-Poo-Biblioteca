//Classe Livro
package vera;
import java.io.*;     
import java.util.*;
import java.util.Collections;
public class Livro {
    // Atributos privados da classe Livro
    private String titulo;
    private String isbn;
    private String autor;
    private int anoPublicacao;
    private boolean disponivel;
    
    // Lista estática para armazenar os livros
    private static ArrayList<Livro> listaLivros = new ArrayList<>();
    
    // Caminho do arquivo de texto para armazenar os livros
    static final String nomeArquivo = "C:\\Users\\Adm\\OneDrive\\Área de Trabalho\\livro.txt";
   //Construtor da classe Livro
    public Livro(String titulo, String isbn, String autor, int anoPublicacao) {
        this.titulo = titulo;
    	this.isbn = isbn;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    // Getters e setters
    public String getTitulo() {
  		return titulo;
  	}

  	public void setTitulo(String titulo) {
  		this.titulo = titulo;
  	}

  	public String getIsbn() {
  		return isbn;
  	}
    public void setIsbn(String isbn) {
    	this.isbn = isbn;
    }
  	public String getAutor() {
  		return autor;
  	}

  	public void setAutor(String autor) {
  		this.autor = autor;
  	}

  	public int getAnoPublicacao() {
  		return anoPublicacao;
  	}

  	public void setAnoPublicacao(int anoPublicacao) {
  		this.anoPublicacao = anoPublicacao;
  	}

  	public boolean isDisponivel() {
  		return disponivel;
  	}

  	public void setDisponivel(boolean disponivel) {
  		this.disponivel = disponivel;
  	}

  	 public static ArrayList<Livro> getListaLivros() {
         return listaLivros;
     }

  	public static void setLivros(ArrayList<Livro> listaLivros) {
  		Livro.listaLivros = listaLivros;
  	}

  
    //menu de livros
    public static void menuLivros() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("+-------------- MENU LIVROS -------------+");
            System.out.println("| Selecione uma das opções abaixo:      |");
            System.out.println("| 1. Cadastrar Livro                    |");
            System.out.println("| 2. Editar Livro                       |");
            System.out.println("| 3. Excluir Livro                      |");
            System.out.println("| 4. Visualizar Livro                   |");
            System.out.println("| 5. Listar Livros                      |");
            System.out.println("| 6. Voltar ao Menu Principal           |");
            System.out.println("+---------------------------------------+");
            System.out.println();

            String entrada = scanner.nextLine();

            switch (entrada) {
                case "1":
                    cadastrarLivro();
                    break;
                case "2":
                    editarLivro();
                    break;
                case "3":
                    excluirLivro();
                    break;
                case "4":
                    visualizarLivro();
                    break;
                case "5":
                    listarLivros();
                    break;
                case "6":
                    return; // Voltar
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

   
    //Método para cadastrar livro
    public static boolean cadastrarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();
        
        System.out.println("Digite o ISBN do livro:");
        String isbn = scanner.nextLine();
        
     // Verificar se o livro já está cadastrado
        for (Livro livro : Livro.listaLivros) {
            if (livro.getIsbn().equals(isbn)) { // Usando equals() para comparar strings
                System.out.println("Já tem livro cadastrado com o mesmo ISBN.");
                return false;
            }
        }

        
        System.out.println("Digite o autor do livro:");
        String autor = scanner.nextLine();
        System.out.println("Digite o ano de publicação do livro:");
        int anoPublicacao;
        while (true) {
            try {
                anoPublicacao = Integer.parseInt(scanner.nextLine()); 
                break; // Sai do loop se a conversão for bem-sucedida
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido para o novo ano de publicação:");
            }
        }
        
        boolean sucesso = listaLivros.add(new Livro(titulo, isbn, autor, anoPublicacao));
        if (sucesso) {
            Livro.salvarLivro(nomeArquivo);
            System.out.println("Livro cadastrado com sucesso.");
        }
        return sucesso;
    }

    // Método de editar
    public static boolean editarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro que deseja editar:");
        String titulo = scanner.nextLine();
        
        for (Livro livro : Livro.listaLivros) {
            if (livro.getTitulo().equals(titulo)) {
                System.out.println("Digite o novo título do livro:");
                String novoTitulo = scanner.nextLine(); // Corrigido para armazenar como String
                System.out.println("Digite o novo ISBN do livro:");
                String novoIsbn = scanner.nextLine();
                System.out.println("Digite o novo autor do livro:");
                String novoAutor = scanner.nextLine();
                System.out.println("Digite o novo ano de publicação do livro:");
                int novoAnoPublicacao;
                while (true) {
                    try {
                        novoAnoPublicacao = Integer.parseInt(scanner.nextLine());
                        break; // Sai do loop se a conversão for bem-sucedida
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, digite um número válido para o novo ano de publicação:");
                    }
                }

                livro.setTitulo(novoTitulo);
                livro.setIsbn(novoIsbn);
                livro.setAutor(novoAutor);
                livro.setAnoPublicacao(novoAnoPublicacao);
                Livro.salvarLivro(nomeArquivo);
                System.out.println("Livro editado com sucesso.");
                return true;
            }
        }
        System.out.println("Livro não encontrado.");
        return false;
    }
	 
	 // Método de excluir
 	 public static boolean excluirLivro() {
 	     Scanner scanner = new Scanner(System.in);
 	     System.out.println("Digite o título do livro que deseja excluir:");
 	     String titulo = scanner.nextLine();
 	     
 	     for (Livro livro : Livro.listaLivros) {
 	         if (livro.getTitulo().equals(titulo)) {
 	             System.out.println("Tem certeza que deseja excluir o livro \"" + livro.getTitulo() + "\"? (s/n)");
 	             String confirmacao = scanner.nextLine().toLowerCase(); // Converte para minúsculas para simplificar a comparação
 	             
 	             if (confirmacao.equals("s")) {
 	                 Livro.listaLivros.remove(livro);
 	                 System.out.println("Livro \"" + livro.getTitulo() + "\" excluído com sucesso.");
 	                 Livro.salvarLivro(nomeArquivo);
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
 	     System.out.println("Livro não encontrado.");
 	     return false;
 	 }  


  	// Método para visualizar livro com opções
  	public static void visualizarLivro() {
  	    Scanner scanner = new Scanner(System.in);
  	    while (true) {
  	        System.out.println("+-----------------------------------+");
  	        System.out.println("| Como você deseja visualizar o livro? |");
  	        System.out.println("| 1. Por título                      |");
  	        System.out.println("| 2. Por ISBN                        |");
  	        System.out.println("| 3. Por autor                       |");
  	        System.out.println("| 4. Por ano de publicação           |");
  	        System.out.println("| 5. Por disponibilidade            |");
  	        System.out.println("| 6. Voltar                          |");
  	        System.out.println("+-----------------------------------+");
  	        System.out.println();

  	        String entrada = scanner.nextLine();

  	        switch (entrada) {
  	            case "1":
  	                visualizarLivroPorTitulo();
  	                break;
  	            case "2":
  	                visualizarLivroPorISBN();
  	                break;
  	            case "3":
  	                visualizarLivroPorAutor();
  	                break;
  	            case "4":
  	                visualizarLivroPorAnoPublicacao();
  	                break;
  	            case "5":
  	                visualizarLivroPorDisponibilidade();
  	                break;
  	            case "6":
  	                return; // Voltar
  	            default:
  	                System.out.println("Opção inválida!");
  	        }
  	    }
  	}


 	// Método para visualizar livro por título
 	public static void visualizarLivroPorTitulo() {
 	    Scanner scanner = new Scanner(System.in);
 	    System.out.println("Digite o título do livro que deseja visualizar:");
 	    String titulo = scanner.nextLine();
 	    
 	    for (Livro livro : Livro.getListaLivros()) {
 	        if (livro.getTitulo().equalsIgnoreCase(titulo)) {
 	            System.out.println("Livro encontrado:");
 	            System.out.println(livro);
 	            return;
 	        }
 	    }
 	    System.out.println("Livro não encontrado.");
 	}

 	// Método para visualizar livro por ISBN
 	public static void visualizarLivroPorISBN() {
 	    Scanner scanner = new Scanner(System.in);
 	    System.out.println("Digite o ISBN do livro que deseja visualizar:");
 	    String isbn = scanner.nextLine();
 	    
 	    for (Livro livro : Livro.getListaLivros()) {
 	        if (livro.getIsbn().equalsIgnoreCase(isbn)) {
 	            System.out.println("Livro encontrado:");
 	            System.out.println(livro);
 	            return;
 	        }
 	    }
 	    System.out.println("Livro não encontrado.");
 	}

 	// Método para visualizar livro por autor
 	public static void visualizarLivroPorAutor() {
 	    Scanner scanner = new Scanner(System.in);
 	    System.out.println("Digite o autor do livro que deseja visualizar:");
 	    String autor = scanner.nextLine();
 	    
 	    for (Livro livro : Livro.getListaLivros()) {
 	        if (livro.getAutor().equalsIgnoreCase(autor)) {
 	            System.out.println("Livro encontrado:");
 	            System.out.println(livro);
 	            return;
 	        }
 	    }
 	    System.out.println("Livro não encontrado.");
 	}

 	// Método para visualizar livro por ano de publicação
 	public static void visualizarLivroPorAnoPublicacao() {
 	    Scanner scanner = new Scanner(System.in);
 	    System.out.println("Digite o ano de publicação do livro que deseja visualizar:");
 	    int anoPublicacao = scanner.nextInt();
 	    scanner.nextLine(); // Consumir a quebra de linha pendente
 	    
 	    for (Livro livro : Livro.getListaLivros()) {
 	        if (livro.getAnoPublicacao() == anoPublicacao) {
 	            System.out.println("Livro encontrado:");
 	            System.out.println(livro);
 	            return;
 	        }
 	    }
 	    System.out.println("Livro não encontrado.");
 	}

    // Método para visualizar livro por disponibilidade
 	public static void visualizarLivroPorDisponibilidade() {
 	    Scanner scanner = new Scanner(System.in);
 	    System.out.println("Digite a disponibilidade do livro que deseja visualizar (true ou false):");
 	    boolean disponibilidade = scanner.nextBoolean();
 	    scanner.nextLine(); // Consumir a quebra de linha pendente

 	    boolean livroEncontrado = false;
 	    for (Livro livro : Livro.getListaLivros()) {
 	        if (livro.isDisponivel() == disponibilidade) {
 	            System.out.println("Livro encontrado:");
 	            System.out.println(livro);
 	            livroEncontrado = true;
 	        }
 	    }
 	    if (!livroEncontrado) {
 	        if (disponibilidade) {
 	            System.out.println("Nenhum livro disponível encontrado.");
 	        } else {
 	            System.out.println("Nenhum livro indisponível encontrado.");
 	        }
 	    }
 	}


	  
   // Método de listar livros
 	public static void listarLivros() {
 	    if (Livro.listaLivros.isEmpty()) {
 	        System.out.println("Não há livros cadastrados para serem listados.");
 	    } else {
 	        System.out.println("Listando Livros:");
 	        int contador = 1;
 	        for (Livro livro : Livro.listaLivros) {
 	            System.out.println(contador + " - " + livro.toString());
 	            contador++;
 	        }
 	    }
 	}


    
	// Método de salvar em arquivo
    public static void salvarLivro(String nomeArquivo){
        try {
            System.out.println("Salvando livros no arquivo: " + nomeArquivo);
            FileWriter fileWriter = new FileWriter(nomeArquivo); 
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Livro livro : listaLivros) { 
                printWriter.println(livro.getTitulo() + ";" + livro.getIsbn() + ";" + livro.getAutor() + ";" 
                + livro.getAnoPublicacao()); 
            }
            printWriter.close(); 
            System.out.println("Livros salvos com sucesso no arquivo.");

        } catch (IOException e) { 
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo: " + nomeArquivo + "\n");
            e.printStackTrace();
        }
    }
    

    // Método de ler arquivo
    public static void lerLivros(){
        try {
            FileReader fileReader = new FileReader(nomeArquivo); 
            BufferedReader bufferedReader = new BufferedReader(fileReader); 

            String linha;
            while ((linha = bufferedReader.readLine()) != null) { 
                String[] dados = linha.split(";"); 
                // Verifica se há dados suficientes na linha
                if (dados.length >= 4) {
                    Livro livro = new Livro(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                    listaLivros.add(livro); 
                } else {
                    System.out.println("Formato inválido na linha: " + linha);
                }
            }
            bufferedReader.close(); // Fecha o buffer de leitura

            // Chamada para salvar o arquivo após ler os livros
            Livro.salvarLivro(nomeArquivo);

        } catch (IOException e) { // Trata exceções de entrada e saída
            System.out.println("Ocorreu um erro ao ler o arquivo: " + nomeArquivo + "\n");
            e.printStackTrace();
        } catch (NumberFormatException e) { // Trata exceção de conversão de número
            System.out.println("Erro ao converter número na leitura do arquivo: " + nomeArquivo + "\n");
            e.printStackTrace();
        }
    }

   
    @Override
    public String toString() {
        String disponibilidade = disponivel ? "Disponível" : "Indisponível";
        return "Livro{" +
                "ISBN='" + isbn + '\'' +
                ", Título=" + titulo.toString() + 
                ", Autor='" + autor + '\'' +
                ", Ano de Publicação=" + anoPublicacao +
                ", Disponibilidade=" + disponibilidade +
                '}';
    }

}

