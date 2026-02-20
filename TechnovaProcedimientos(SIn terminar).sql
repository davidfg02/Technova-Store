		USE db_technova;
-- ======================== LISTAR USUARIOS ================================== --
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_usuarios_listar $$
CREATE PROCEDURE sp_usuarios_listar()
BEGIN 
SELECT *
FROM usuario;
END $$
DELIMITER ;

CALL sp_usuarios_listar;

-- ======================== LISTAR PRODUCTOS (OBLIGATORIO) ================================= --
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_productos_listar $$
CREATE PROCEDURE sp_productos_listar()
BEGIN
SELECT *
FROM producto;
END $$
DELIMITER ;
CALL sp_productos_listar;

-- ============= LISTAR PRODUCTOS POR CATEGORIAS(OBLIGATORIO) =========================== --
drop procedure if exists sp_productos_por_categoria;
DELIMITER $$
CREATE PROCEDURE sp_productos_por_categoria(IN p_categoria varchar(100))
BEGIN
SELECT *
FROM producto
WHERE producto.categoria = p_categoria;
END $$
DELIMITER ;
CALL sp_productos_por_categoria('componentes');

-- ========================= LISTAR PEDIDOS ===================================== --

DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_pedidos $$
CREATE PROCEDURE sp_pedidos()
BEGIN 
SELECT * 
FROM pedido;
END $$
DELIMITER ;
CALL sp_pedidos;

-- ================= LISTAR PEDIDOS POR FECHA Y ESTADO(OBLIGATORIO) ============================ --
DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_pedidos_filtro $$
CREATE PROCEDURE sp_pedidos_filtro(IN p_estado VARCHAR(30), IN p_fecha_ini DATETIME, IN p_fecha_fin DATETIME)
BEGIN 
SELECT * 
FROM pedido
WHERE pedido.pedido_estado = p_estado AND fecha BETWEEN p_fecha_ini AND p_fecha_fin ;
END $$
DELIMITER ;
call sp_pedidos_filtro('ENVIADO', '2026-02-12 09:39:56', '2026-02-19 09:39:56');

-- ============================= LISTAR LINEA DE PEDIDO ============================ --
DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_lineapedido_listar $$
CREATE PROCEDURE sp_lineapedido_listar()
BEGIN 
SELECT * 
FROM linea_pedido;
END $$
DELIMITER ;
CALL sp_lineapedido_listar;

-- =========================== LISTAR MOVIMIENTO DE INVENTARIO ======================= --
DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_movimientoinventario_listar $$
CREATE PROCEDURE sp_movimientoinventario_listar()
BEGIN 
SELECT * 
FROM movimiento_inventario;
END $$
DELIMITER ;
CALL sp_movimientoinventario_listar;


-- ====================== LISTAR LINEA DE PEDIDO POR USUARIOS ========================= --
DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_lineapedido_listar_usuario $$
CREATE PROCEDURE sp_lineapedido_listar_usuario(in p_email varchar(100) ,in p_password varchar(255))
BEGIN 
SELECT * 
FROM pedido JOIN usuario ON pedido.id_usuario = usuario.id_usuario
where  p_email = email and p_password = password;
END $$
DELIMITER ;
CALL sp_lineapedido_listar_usuario('javiervs@gmail.com','uyuyuyuy124.S');

-- ========================= LISTAR USUARIO ============================================== --
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_usuario $$
CREATE PROCEDURE sp_usuario(in p_email varchar(100) ,in p_password varchar(255))
BEGIN 
SELECT *
FROM usuario
where p_email= email and p_password= password;
END $$
DELIMITER ;
call sp_usuario ('javiervs@gmail.com','uyuyuyuy124.S');

-- ===========================  CREAR PEDIDOS ================================================= --

DELIMITER $$

CREATE PROCEDURE sp_crear_pedido(
    IN p_IdUsuario INT,
    IN p_total_pedido DECIMAL(10,2),
    IN p_idProducto INT,
    IN p_cantidad INT,
    IN p_PrecioUnitario DECIMAL(10,2)
)
begin 
declare 


 INSERT INTO pedido (id_usuario, total_pedido) VALUES (p_IdUsuario, p_total_pedido);
 INSERT INTO linea_pedido ( id_producto, cantidad, precio_unitario_momento)  values (p_idProducto,p_cantidad,p_PrecioUnitario);
 UPDATE producto SET stock = stock - p_cantidad WHERE id_producto = p_idProducto AND stock >= 0;
 end $$
DELIMITER ;
CALL sp_crear_pedido('1','199.99','2','2','199.99');
 


