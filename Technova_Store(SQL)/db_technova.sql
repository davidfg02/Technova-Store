DROP DATABASE IF EXISTS db_technova;
CREATE DATABASE db_technova;
USE db_technova;
-- creamos la tabla usuario --
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY, -- genera automáticamente un valor secuencial único
    email VARCHAR(100) UNIQUE NOT NULL, -- suficiente longitud para poder introducir correctamente los emails --
    password VARCHAR(255) NOT NULL, -- tinene que tener la suficiente longitud para que al momento de cifrarla por hash entre toda --
    rol ENUM('CLIENTE', 'OFICINA', 'ADMINISTRADOR') NOT NULL
);
-- Creamos la tabla producto --
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    sku VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT, -- almacena grandes volúmenes de datos de caracteres de longitud variable
    precio DECIMAL(10, 2) NOT NULL CHECK(precio >= 0), -- usamos el chek paar asegurarnos que el precio no sea negativo --
    stock INT NOT NULL CHECK (stock >= 0), -- usamos el chek para comprobar que no exista stock negativo --
    categoria ENUM('Componentes', 'Perifericos', 'Redes', 'Software') NOT NULL,
    imagen VARCHAR(255) -- debe tener sufiiente longitud paar almacenar las urls de las imagenes --
);
-- Creamos la tabla pedido --
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- almacena fecha y hora en formato 'AAAA-MM-DD HH:MM:SS', si no se introduce, devuelve la hora y fecha actual del sistema operativo que ejecute
    total_pedido DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) -- con la FK vinculamos la tabla pedido con la tabla usuario --
);
-- Creamos la tabla linea_pedido --
CREATE TABLE linea_pedido (
    id_linea_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario_momento DECIMAL (10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido), -- con la FK vinculamos la tabla linea_pedido con la tabla pedido--
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto) -- con la FK vinculamos la tabla linea_pedido con la tabla producto --
);
-- Creamos la tabla movimiento_inventario--
CREATE TABLE movimiento_inventario (
id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
id_producto INT NOT NULL,
tipo_movimiento ENUM ('Entrada','Salida') NOT NULL, -- Entrada agregar, Salida quitar al stock --
fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
cantidad INT NOT NULL CHECK(cantidad > 0), -- el check sirve parA registar siempre una cantidad positiva --
motivo VARCHAR(255),
FOREIGN KEY(id_producto) REFERENCES producto (id_producto)
);