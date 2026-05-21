package Application;

import Objects.Livro;
import java.util.List;
import java.util.ArrayList;

public class Repositorio {
	private int contadorId = 1;
	private List<Livro> bd = new ArrayList<>();
	

	public void create(Livro l) {
		l.setId(contadorId++);
		bd.add(l);
	}
	
	public String read() {
		if(bd.isEmpty()) {
			return "Não há livros registrados. \n";
		}else {
			for(Livro l: bd) {
				return l.toString();
			}
		}
		return "";
	}

	public String buscarPorTitulo(String t) {
	    for (Livro l : bd) {
	        if (l.getTitulo().equalsIgnoreCase(t)) {
	            return l.toString();     
	        }
	    }
	    return "Livro não encontrado.\n";
	}

	public String buscarPorAutor(String a) {
	    for (Livro l : bd) {
	        if (l.getAutor().equalsIgnoreCase(a)) {
	            return l.toString();
	        }
	    }
	    return "Não há livros registrados com esse autor.\n";
	}
	
	public String buscarPorId(int id) {
		for(Livro l: bd) {
			if(l.getId() == id) {
				return l.toString();
			}
		}
		return "Não há livros registrados com esse autor.\n";
	}
	public void update(int id, String a, String t, boolean lido) {
		for(Livro l: bd) {
			if(l.getId() == id) {
				l.setAutor(a);
				l.setTitulo(t);
				l.setLido(lido);
				return;
			}
		}
	}
	public void delete(int id) {
		for(Livro l : bd) {
			if(l.getId() == id) {
				bd.remove(l);
				return;
			}
		}
	}
	public void delete(String t) {
		for(Livro l: bd) {
			if(l.getTitulo().equalsIgnoreCase(t)) {
				bd.remove(l);
				return;
			}
		}
	}
}
