CREATE TABLE propiedad (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    direccion TEXT NOT NULL,
    precio REAL NOT NULL,
    propietario TEXT NOT NULL,
    estado TEXT CHECK(estado IN ('Disponible', 'Vendido')) NOT NULL
);
INSERT INTO propiedad (direccion, precio, propietario, estado)
VALUES 
('Paseo de la Castellana, Madrid', 250000, 'Carlos García', 'Disponible'),
('Rambla de Catalunya, Barcelona', 300000, 'María Fernández', 'Vendido'),
('Plaza del Ayuntamiento, Valencia', 220000, 'Javier Martínez', 'Disponible');

SELECT * FROM propiedad;

