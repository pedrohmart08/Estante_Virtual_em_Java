package Application;

import Objects.Livro;
import java.util.List;
import java.util.ArrayList;

public class Repositorio {
	private int contadorId = 1;
	private List<Livro> bd = new ArrayList<>();
	
//	adiciona obras ao bd
	public void create(Livro l) {
		l.setId(contadorId++);
		bd.add(l);
	}
//PRECISO REFATORAR ISSO
//	Mostra todas as obras
	public void read() {
		if(bd.isEmpty()) {
			System.out.println("Não há livros registrados. ");
		}else {
			for(int i = 0; i < bd.size(); i++) {
				System.out.println(bd.get(i));
			}
		}
		
	}
//	Procura por titulo
	public void buscarPorTitulo(String t) {
	    boolean encontrado = false;
	    for (Livro livro : bd) {
	        if (livro.getTitulo().equalsIgnoreCase(t)) {
	            System.out.println(livro);
	            encontrado = true;
	        }
	    }
	    if (!encontrado) {
	        System.out.println("Não há livros registrados com esse título.");
	    }
	}
//	Procura por autor
	public void buscarPorAutor(String a) {
	    boolean encontrado = false;
	    for (Livro livro : bd) {
	        if (livro.getAutor().equalsIgnoreCase(a)) {
	            System.out.println(livro);
	            encontrado = true;
	        }
	    }
	    if (!encontrado) {
	        System.out.println("Não há livros registrados com esse autor.");
	    }
	}
//	procura por id
	public void buscarPorId(int id) {
		for(int i = 0; i < bd.size(); i++) {
			if(bd.get(i).getId() == id) {
				System.out.println(bd.get(i));
				return;
			}
		}
		System.out.println("Não há livros registrados com esse endereço. ");
	}
	public void update(int id, String a, String t, boolean l) {
		for(int i = 0; i < bd.size(); i++) {
			if(bd.get(i).getId() == id) {
				bd.get(i).setAutor(a);
				bd.get(i).setTitulo(t);
				bd.get(i).setLido(l);
				return;
			}
		}
		System.out.println("Não foi possivel atualizar. ");
	}
	/* só podemos deletar pelo id facilita no códigokk */
	public void delete(int id) {
		for(int i = 0; i < bd.size(); i++) {
			if(bd.get(i).getId() == id) {
				bd.remove(i);
				return;
			}
		}
		System.out.println("Não foi possivel remover, id não encontrado");
	}
	public void delete(String t) {
		for(int i = 0; i < bd.size(); i++) {
			if(bd.get(i).getTitulo().equalsIgnoreCase(t)) {
				bd.remove(i);
				return;
			}
		}
		System.out.println("Não foi possivel remover, titulo não encontrado");
	}
}
