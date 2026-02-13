USE db_technova;
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_usuarios_listar $$
CREATE PROCEDURE sp_usuarios_listar()
BEGIN 
SELECT *
FROM usuario;
END $$
DELIMITER ;

CALL sp_usuarios_listar;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_productos_listar $$
CREATE PROCEDURE sp_productos_listar()
BEGIN
SELECT *
FROM producto;
END $$
DELIMITER ;
CALL sp_productos_listar;

DELIMITER $$
CREATE PROCEDURE sp_productos_por_categoria(IN p_categoria varchar(100))
BEGIN
SELECT producto.nombre, producto.categoria
FROM producto
WHERE producto.categoria = p_categoria;
END $$
DELIMITER ;
CALL sp_productos_por_categoria('componentes');

DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_pedidos $$
CREATE PROCEDURE sp_pedidos()
BEGIN 
SELECT * 
FROM pedido;
END $$
DELIMITER ;
CALL sp_pedidos;


DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_pedidos_filtro $$
CREATE PROCEDURE sp_pedidos_filtro(IN p_estado VARCHAR(30), IN p_fecha_ini DATETIME, IN p_fecha_fin DATETIME)
BEGIN 
SELECT * 
FROM pedido
WHERE pedido.pedido_estado = p_estado AND fecha BETWEEN p_fecha_ini AND p_fecha_fin ;
END $$
DELIMITER ;
call sp_pedidos_filtro('ENVIADO', '2026-02-12 09:39:56', '2026-02-12 09:39:56');

DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_lineapedido_listar $$
CREATE PROCEDURE sp_lineapedido_listar()
BEGIN 
SELECT * 
FROM linea_pedido;
END $$
DELIMITER ;
CALL sp_lineapedido_listar;

DELIMITER $$
DROP PROCEDURE IF EXISTS  sp_movimientoinventario_listar $$
CREATE PROCEDURE sp_movimientoinventario_listar()
BEGIN 
SELECT * 
FROM movimiento_inventario;
END $$
DELIMITER ;
CALL sp_movimientoinventario_listar;


