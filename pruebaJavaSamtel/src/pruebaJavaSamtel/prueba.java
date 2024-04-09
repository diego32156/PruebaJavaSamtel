package pruebaJavaSamtel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class prueba {

	class Libro {
	    private String titulo;
	    private String autor;
	    private int anoPublicacion;

	    public Libro(String titulo, String autor, int anoPublicacion) {
	        this.titulo = titulo;
	        this.autor = autor;
	        this.anoPublicacion = anoPublicacion;
	    }

	    public String getTitulo() {
	        return titulo;
	    }

	    public String getAutor() {
	        return autor;
	    }

	    public int getAnoPublicacion() {
	        return anoPublicacion;
	    }
	}

	class Usuario {
	    private String nombre;
	    private int id;

	    public Usuario(String nombre, int id) {
	        this.nombre = nombre;
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public int getId() {
	        return id;
	    }
	}

	class Biblioteca {
	    private List<Libro> librosDisponibles;
	    private Map<Libro, Usuario> librosPrestados;

	    public Biblioteca() {
	        librosDisponibles = new ArrayList<>();
	        librosPrestados = new HashMap<>();
	    }

	    public void agregarLibro(Libro libro) {
	        librosDisponibles.add(libro);
	        System.out.println("Libro agregado al catálogo.");
	    }
	

	    public void prestarLibro(Usuario usuario, Libro libro) {
	        if (librosDisponibles.contains(libro)) {
	            librosDisponibles.remove(libro);
	            librosPrestados.put(libro, usuario);
	            System.out.println("Libro prestado correctamente a " + usuario.getNombre() + ".");
	        } else {
	            System.out.println("El libro no está disponible para prestar.");
	        }
	    }

	    public void devolverLibro(Libro libro) {
	        if (librosPrestados.containsKey(libro)) {
	            librosPrestados.remove(libro);
	            librosDisponibles.add(libro);
	            System.out.println("Libro devuelto correctamente.");
	        } else {
	            System.out.println("El libro no estaba prestado.");
	        }
	    }

	    public void mostrarCatalogo() {
	        System.out.println("Catálogo de la biblioteca:");
	        for (Libro libro : librosDisponibles) {
	            System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " (" + libro.getAnoPublicacion() + ")");
	        }
	    }

	    public void mostrarLibrosPrestados() {
	        System.out.println("Libros prestados actualmente:");
	        for (Map.Entry<Libro, Usuario> entry : librosPrestados.entrySet()) {
	            Libro libro = entry.getKey();
	            Usuario usuario = entry.getValue();
	            System.out.println(libro.getTitulo() + " - Prestado a: " + usuario.getNombre());
	        }
	    }
	}
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		prueba instanciaPrueba = new prueba();
		Biblioteca biblioteca = instanciaPrueba.new Biblioteca();


        System.out.println("Bienvenido al Sistema de Gestión de Biblioteca\n");

        while (true) {
            System.out.println("1. Agregar Nuevo Libro");
            System.out.println("2. Prestar Libro");
            System.out.println("3. Devolver Libro");
            System.out.println("4. Mostrar Catálogo");
            System.out.println("5. Mostrar Libros Prestados");
            System.out.println("6. Salir");

            System.out.print("\nPor favor, seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        System.out.print("\nIngrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Ingrese el autor del libro: ");
                        String autor = scanner.nextLine();
                        System.out.print("Ingrese el año de publicación del libro: ");
                        int anoPublicacion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada
                        Libro nuevoLibro = instanciaPrueba.new Libro(titulo, autor, anoPublicacion);
                        biblioteca.agregarLibro(nuevoLibro);
                        break;
                    case 2:
                        System.out.print("\nIngrese el nombre del usuario: ");
                        String nombreUsuario = scanner.nextLine();
                        System.out.print("Ingrese el identificador del usuario: ");
                        int idUsuario = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada
                        Usuario usuario = instanciaPrueba.new Usuario(nombreUsuario, idUsuario);
                        System.out.print("Ingrese el título del libro a prestar: ");
                        String tituloLibroPrestar = scanner.nextLine();
                        Libro libroPrestar = null;
                        for (Libro libro : biblioteca.librosDisponibles) {
                            if (libro.getTitulo().equals(tituloLibroPrestar)) {
                                libroPrestar = libro;
                                break;
                            }
                        }
                        if (libroPrestar != null) {
                            biblioteca.prestarLibro(usuario, libroPrestar);
                        } else {
                            System.out.println("El libro no está disponible.");
                        }
                        break;
                    case 3:
                        System.out.print("\nIngrese el título del libro a devolver: ");
                        String tituloLibroDevolver = scanner.nextLine();
                        Libro libroDevolver = null;
                        for (Libro libro : biblioteca.librosPrestados.keySet()) {
                            if (libro.getTitulo().equals(tituloLibroDevolver)) {
                                libroDevolver = libro;
                                break;
                            }
                        }
                        if (libroDevolver != null) {
                            biblioteca.devolverLibro(libroDevolver);
                        } else {
                            System.out.println("El libro no está prestado.");
                        }
                        break;
                    case 4:
                        biblioteca.mostrarCatalogo();
                        break;
                    case 5:
                        biblioteca.mostrarLibrosPrestados();
                        break;
                    case 6:
                        System.out.println("\nSaliendo del sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nOpción inválida. Por favor, seleccione una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Ingrese un número válido para la opción.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }

	}

}
