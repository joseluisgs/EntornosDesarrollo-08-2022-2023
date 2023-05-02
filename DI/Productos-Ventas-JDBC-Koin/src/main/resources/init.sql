-- Fichero para inicializar la base de datos, tablas y valores iniciales
-- Borramos la base de datos si existe
DROP TABLE IF EXISTS Lineas_Ventas;
DROP TABLE IF EXISTS Ventas;
DROP TABLE IF EXISTS Productos;

-- Tabla de Productos
CREATE TABLE IF NOT EXISTS Productos
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre     TEXT    NOT NULL,
    precio     REAL    NOT NULL,
    cantidad   INTEGER NOT NULL,
    created_at TEXT    NOT NULL,
    updated_at TEXT    NOT NULL,
    disponible INTEGER NOT NULL
);

-- insertamos datos iniciales
INSERT INTO Productos (id, nombre, precio, cantidad, created_at, updated_at, disponible)
VALUES (1, 'Producto 1', 10.0, 10, '2023-04-04T17:40:36', '2023-04-03T17:40:36',
        1);
INSERT INTO Productos (id, nombre, precio, cantidad, created_at, updated_at, disponible)
VALUES (2, 'Producto 2', 20.0, 20, '2023-04-04T17:40:36', '2023-04-03T17:40:36',
        0);
INSERT INTO Productos (id, nombre, precio, cantidad, created_at, updated_at, disponible)
VALUES (3, 'Producto 3', 10.0, 10, '2023-04-04T17:40:36', '2023-04-03T17:40:36',
        1);

-- Tabla de ventas
CREATE TABLE IF NOT EXISTS Ventas
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id      INTEGER NOT NULL,
    created_at   TEXT    NOT NULL,
    updated_at   TEXT    NOT NULL,
    total_precio REAL    NOT NULL,
    total_items  INTEGER NOT NULL
);

-- Tabla de Lineas de Ventas.
CREATE TABLE IF NOT EXISTS Lineas_Ventas
(
    venta_id        INTEGER NOT NULL REFERENCES Ventas (id) ON DELETE CASCADE,
    linea_id        INTEGER NOT NULL,
    producto_id     INTEGER NOT NULL REFERENCES Productos (id),
    cantidad        INTEGER NOT NULL,
    producto_precio REAL    NOT NULL,
    total_precio    REAL    NOT NULL,
    PRIMARY KEY (venta_id, linea_id)
);

-- insertamos datos iniciales
INSERT INTO Ventas (id, user_id, created_at, updated_at, total_precio, total_items)
VALUES (1, 1, '2023-04-04T17:40:36', '2023-04-04T17:40:36', 50.0, 3);

INSERT INTO Lineas_Ventas (venta_id, linea_id, producto_id, cantidad, producto_precio, total_precio)
VALUES (1, 1, 2, 2, 20.0, 40.0);
INSERT INTO Lineas_Ventas (venta_id, linea_id, producto_id, cantidad, producto_precio, total_precio)
VALUES (1, 2, 3, 1, 10.0, 10.0);
