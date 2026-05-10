package Objects;

public class Livro {

	private int id;
	private String titulo;
	private String autor;
	private boolean lido;
	
	public Livro(String titulo, String autor, boolean lido) {
		this.titulo = titulo;
		this.autor = autor;
		this.lido = lido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isLido() {
		return lido;
	}

	public void setLido(boolean lido) {
		this.lido = lido;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", lido=" + lido + "]";
	}
}