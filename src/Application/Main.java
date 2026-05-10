package Application;

import java.util.Scanner;

import Objects.Livro;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Repositorio r = new Repositorio();
		System.out.println("\n=======Bem-vindo ao meu CRUD aqui vamos gerenciar seus livros.=======\n");
		while(true) {
			System.out.println("=======Digite a opção desejada=======\n");
			System.out.println("=======1 - Adicionar Livro |2 - Remover livro |3 - Atualizar Obra| 4 - Mostrar Livros| 5 - Fechar programa");
			
			int opcao = sc.nextInt();
			sc.nextLine();
			if(opcao == 1) {
				boolean l = false;
				System.out.println("Insira o titulo da obra: ");
				String t = sc.nextLine();
				System.out.println("Insira o autor: ");
				String a = sc.nextLine();
				System.out.println("Você leu o livro: (S/N)");
				String opcaoLido = sc.nextLine();
				if(opcaoLido.equalsIgnoreCase("s")) {
					l = true;
				}else if(opcaoLido.equalsIgnoreCase("n")) {
					
				}else {
					System.out.println("Opção inválida, consideraremos que o livro não foi lido você poderá modifcar posteriormente");
				}
				Livro novoLivro = new Livro(t, a, l);
				r.create(novoLivro);
			}else if(opcao == 2) {
				System.out.println("Deseja deletar por id ou por titulo: (id/titulo)");
				System.out.println("(obs:Prefira sempre excluir pelo id.)");
				String opcaoDelete = sc.nextLine();
				if(opcaoDelete.equalsIgnoreCase("id")) {
					System.out.println("Insira o id: ");
					int id = sc.nextInt();
					r.delete(id);
				}else if(opcaoDelete.equalsIgnoreCase("titulo")) {
					System.out.println("Insira o titulo: ");
					String titulo = sc.nextLine();
					r.delete(titulo);
				}else {
					System.out.println("Opção inválida. ");
				}
			}else if (opcao == 3) {
				boolean lido = false;
				System.out.println("Insira o id da obra que vai atualizar: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("Insira os novo dados: ");
				System.out.println("Autor: ");
				String autor = sc.nextLine();
				System.out.println("Titulo: ");
				String titulo = sc.nextLine();
				System.out.println("Já foi lido: (s/n)");
				String l = sc.nextLine();
				if(l.equalsIgnoreCase("s")) {
					lido = true;
				}else if(l.equalsIgnoreCase("n")) {
					
				}else {
					System.out.println("Opção invalida");
				}
				r.update(id, autor, titulo, lido);
			}else if(opcao == 4) {
				System.out.println("1 - Mostrar todos livro |2 - Buscar por titulo |3 - Buscar por Autor |4 - Buscar por Id");
				int opcaoBusca = sc.nextInt();
				sc.nextLine();
				if(opcaoBusca == 1) {
					r.read();
				}else if (opcaoBusca == 2) {
					System.out.println("Insira o titulo: ");
					String titulo = sc.nextLine();
					r.buscarPorTitulo(titulo);
				}else if (opcaoBusca == 3) {
					System.out.println("Insira o nome do autor: ");
					String autor = sc.nextLine();
					r.buscarPorAutor(autor);
				}else if (opcaoBusca == 4) {
					System.out.println("Insira o id do livro: ");
					int id = sc.nextInt();
					r.buscarPorId(id);
				}else {
					System.out.println("Opção invalida. ");
				}	
				
			}else if(opcao == 5) {
				System.out.println("Fechando programa... ");
				break;
			}
			else {
				System.out.println("Opção invalida. ");
			}
			
		}
	}

}
