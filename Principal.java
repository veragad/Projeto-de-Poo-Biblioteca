//Classe Principal
package vera;
import java.util.ArrayList;    
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        ArrayList<Livro> listaLivros = new ArrayList<>();
        ArrayList<Bibliotecario> listaBibliotecarios = new ArrayList<>();
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        String nomeArquivo = "C:\\Users\\Adm\\OneDrive\\Área de Trabalho\\livro.txt";

        
        Scanner scanner = new Scanner(System.in);
        
        //menu principal
        while (true) {
            System.out.println("+-------------- BIBLIOTECA ---------------+");
            System.out.println("| Escolha uma opção:                      |");
            System.out.println("| 1. Menu Livros                          |");
            System.out.println("| 2. Menu Usuários                        |");
            System.out.println("| 3. Menu Bibliotecários                  |");
            System.out.println("| 4. Menu Empréstimos                     |");
            System.out.println("| 5. Sair                                 |");
            System.out.println("+-----------------------------------------+");

            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    Livro.menuLivros();
                    Livro.lerLivros();
                    Livro.salvarLivro(nomeArquivo);
            
                    break;
                case "2":
                    Usuario.menuUsuarios();
                    Usuario.lerUsuarios();
                    Usuario.salvarUsuarios();
                    break;
                case "3":
                    Bibliotecario.menuBibliotecarios();
                    Bibliotecario.lerBibliotecarios();
                    Bibliotecario.salvarBibliotecarios();
                    break;
                case "4":
                    Emprestimo.menuEmprestimos(listaUsuarios, listaLivros, listaBibliotecarios, emprestimos);
                    Emprestimo.lerEmprestimos();
                    Emprestimo.salvarEmprestimo();
                    break;
                case "5":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
         
        }
        
    }
	
}
