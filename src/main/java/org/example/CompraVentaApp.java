package org.example;

import java.sql.*;
import java.util.Scanner;

public class CompraVentaApp {
    public static void main(String[] args) {
        comprobarConexionBD();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Ver propiedades");
            System.out.println("2. Añadir propiedad");
            System.out.println("3. Filtrar propiedades por estado");
            System.out.println("4. Marcar propiedad como vendida");
            System.out.println("5. Eliminar propiedad");
            System.out.println("6. Editar propiedad");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarPropiedades();
                    break;
                case 2:
                    anadirPropiedad(scanner);
                    break;
                case 3:
                    filtrarPropiedades(scanner);
                    break;
                case 4:
                    marcarComoVendida(scanner);
                    break;
                case 5:
                    eliminarPropiedad(scanner);
                    break;
                case 6:
                    editarPropiedad(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void comprobarConexionBD() {
        try (Connection conn = ConexionBD.conectar()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void mostrarPropiedades() {
        String sql = "SELECT * FROM propiedad";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Propiedad p = new Propiedad(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getDouble("precio"),
                        rs.getString("propietario"),
                        rs.getString("estado")
                );
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener propiedades: " + e.getMessage());
        }
    }

    public static void anadirPropiedad(Scanner scanner) {
        System.out.print("Dirección de la propiedad: ");
        String direccion = scanner.nextLine();

        double precio;
        while (true) {
            System.out.print("Precio de la propiedad: ");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                if (precio > 0) break;
                else System.out.println("El precio debe ser mayor que cero.");
            } else {
                System.out.println("Por favor, ingrese un precio válido.");
                scanner.next();
            }
        }
        scanner.nextLine();

        System.out.print("Propietario de la propiedad: ");
        String propietario = scanner.nextLine();

        String estado;
        while (true) {
            System.out.print("Estado de la propiedad (Disponible/Vendido): ");
            estado = scanner.nextLine();
            if (estado.equalsIgnoreCase("Disponible") || estado.equalsIgnoreCase("Vendido")) {
                break;
            } else {
                System.out.println("Estado no válido. Debe ser 'Disponible' o 'Vendido'.");
            }
        }

        String sql = "INSERT INTO propiedad (direccion, precio, propietario, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, direccion);
            stmt.setDouble(2, precio);
            stmt.setString(3, propietario);
            stmt.setString(4, estado);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Propiedad añadida correctamente.");
            } else {
                System.out.println("Error al añadir la propiedad.");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar la propiedad: " + e.getMessage());
        }
    }

    public static void filtrarPropiedades(Scanner scanner) {
        System.out.print("Filtrar por estado (Disponible/Vendido): ");
        String estado = scanner.nextLine();
        String sql = "SELECT * FROM propiedad WHERE estado = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Propiedad p = new Propiedad(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getDouble("precio"),
                        rs.getString("propietario"),
                        rs.getString("estado")
                );
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println("Error al filtrar propiedades: " + e.getMessage());
        }
    }

    public static void marcarComoVendida(Scanner scanner) {
        System.out.print("Introduce el ID de la propiedad a marcar como vendida: ");
        int id = scanner.nextInt();
        String sql = "UPDATE propiedad SET estado = 'Vendido' WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Propiedad marcada como vendida.");
            } else {
                System.out.println("No se encontró la propiedad con el ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la propiedad: " + e.getMessage());
        }
    }

    public static void eliminarPropiedad(Scanner scanner) {
        System.out.print("Introduce el ID de la propiedad a eliminar: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM propiedad WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Propiedad eliminada correctamente.");
            } else {
                System.out.println("No se encontró la propiedad con el ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la propiedad: " + e.getMessage());
        }
    }

    public static void editarPropiedad(Scanner scanner) {
        System.out.print("Introduce el ID de la propiedad a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introduce la nueva dirección: ");
        String direccion = scanner.nextLine();

        double precio;
        while (true) {
            System.out.print("Introduce el nuevo precio: ");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                if (precio > 0) break;
                else System.out.println("El precio debe ser mayor que cero.");
            } else {
                System.out.println("Por favor, ingrese un precio válido.");
                scanner.next();
            }
        }
        scanner.nextLine();

        System.out.print("Introduce el nuevo propietario: ");
        String propietario = scanner.nextLine();

        String estado;
        while (true) {
            System.out.print("Introduce el nuevo estado (Disponible/Vendido): ");
            estado = scanner.nextLine();
            if (estado.equalsIgnoreCase("Disponible") || estado.equalsIgnoreCase("Vendido")) {
                break;
            } else {
                System.out.println("Estado no válido. Debe ser 'Disponible' o 'Vendido'.");
            }
        }

        String sql = "UPDATE propiedad SET direccion = ?, precio = ?, propietario = ?, estado = ? WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, direccion);
            stmt.setDouble(2, precio);
            stmt.setString(3, propietario);
            stmt.setString(4, estado);
            stmt.setInt(5, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Propiedad actualizada correctamente.");
            } else {
                System.out.println("No se encontró la propiedad con el ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la propiedad: " + e.getMessage());
        }
    }
}
