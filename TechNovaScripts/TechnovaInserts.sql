INSERT INTO usuario (email, password, rol) VALUES 
('anagarcia@technova.es', '12345a!!FDDV', 'Oficina'),
('saragonzalez@technova.es', 'asdjfj21!CC', 'Oficina'),
('alvaromartin@technova.es', 'vfjjdll?Vj1', 'Administrador'),
('shaghyasghari@technova.es', 'bccvcc98!D7', 'Administrador'),
('davidfraile@technova.es', 'mdkdjsjk3?R742', 'Administrador'),
('rayantorres@technova.es', 'lfjgjbjd1!F3', 'Administrador'),
('javiervs@gmail.com', 'uyuyuyuy124.S', 'Cliente'),
('lorenzop@gmail.com', 'iuinkuhn987!A', 'Cliente'),
('danie23@gmail.com', 'lnhhbhhA!34', 'Cliente'),
('antoniosf@gmail.com', 'lnhsdkAhA!34', 'Cliente'),
('maria837a@gmail.com', 'mcsjfwDS!55', 'Cliente'),
('anitaflores69@gmail.com', 'dvjjdje%D2', 'Cliente');


INSERT INTO producto  (sku, nombre, descripcion, precio, stock, categoria, imagen) VALUES 
('alo123456','Monitor Samsung Odyssey G5','Monitor curvo, 165Hz, 32 pulgadas',199.99,30,'Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/1081/10811202/1210-samsung-odyssey-ls27cg552euxen-27-led-qhd-165hz-freesync-curvo-review.jpg'),

('nada123456','Teclado NewSkil Pyro pro','Teclado mecanico, 65%, inalambrico',75.00,100,'Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/1086/10861495/1550-newskill-pyros-pro-teclado-gaming-inalambrico-mecanico-75-hotswap-blanco-foto.jpg'),

('idpro14345','Memoria Ram Corsair Vengeance','2x16GB DDR5 6000MHz',600.99,9,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1076/10764411/1517-corsair-vengeance-ddr5-6000mhz-32gb-2x16gb-cl30.jpg'),

('kkre123456','MSI GeForce RTX 5070 Ti VENTUS','3X OC 16GB GDDR7 Reflex 2 RTX AI DLSS4',1400.90,90,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1086/10868478/1405-msi-geforce-rtx-5070-ti-ventus-3x-oc-16gb-gddr7-dlss4.jpg'),

('huka123445','Corsair MP600 GS 1TB M.2 Gen4','Velocidad lectura secuencial hasta 4800 MB/s',112.22,78,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1063/10635678/1152-corsair-mp600-gs-1tb-m2-gen4-pcie-x4-nvme.jpg'),

('husja12345','AMD Ryzen 7 7800X3D','8 núcleos y 16 hilos ideales para gaming',410.90,2,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1066/10665103/1500-amd-ryzen-7-7800x3d-42-ghz-5-ghz-caracteristicas.jpg'),

('grteja1280','Logitech G G102 LightSync','Ratón Gaming 8000 DPI',17.90,10,'Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/32/328329/1543-logitech-g-g102-lightsync-raton-gaming-8000dpi-negro.jpg'),

('idhgo84345','Windows 11 Pro','Licencia OEM 64 bits Español',200.99,3,'Software','https://thumb.pccomponentes.com/w-530-530/articles/1100/11001061/1190-sistema-operativo-microsoft-windows-11-pro-64-bits-espanol-licencia-oem-permanente.jpg'),

('illo422215','Módem D-Link F518/M 5G','Wi-Fi 6 dual band hasta 1800 Mbps',295.87,0,'Redes','https://thumb.pccomponentes.com/w-530-530/articles/1093/10935747/159-modem-de-red-movil-d-link-f518-m-5g-wi-fi-6-usb-type-c-8000-mah-blanco.jpg'),

('irefo14345','Apple AirPods Pro 3','Cancelación activa de ruido USB-C',229.99,9,'Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/1095/10952804/1501-apple-airpods-pro-3-auriculares-bluetooth-con-cancelacion-activa-de-ruido-usb-c-blancos.jpg'),

('pesfo12873','Corsair NAUTILUS 240 RS','Refrigeración líquida 240mm',80.24,9,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1086/10863489/1788-corsair-nautilus-240-rs-kit-refrigeracion-liquida-240mm-negro.jpg');


INSERT INTO pedido (id_usuario, total_pedido, pedido_estado) VALUES
(7, 217.89, 'ENVIADO'),   -- javiervs@gmail.com
(8, 475.99, 'CONFIRMADO'),   -- lorenzop@gmail.com
(9, 229.99, 'ENTREGADO'),   -- danie23@gmail.com
(11, 98.04, 'PREPARADO');   -- maria837a@gmail.com

INSERT INTO linea_pedido (id_pedido, id_producto, cantidad, precio_unitario_momento) VALUES
-- Pedido 1 pertenece al cliente: javiervs@gmail.com--
(1, 7, 1, 17.90),
(1, 2, 1, 75.00),
(1, 1, 1, 199.99),

-- Pedido 2 pertenece al cliente:lorenzop@gmail.com--
(2, 10, 1, 229.99),
(2, 2, 1, 75.00),
(2, 7, 1, 17.90),

-- Pedido 3 pertenece al cliente: danie23@gmail.com--
(3, 10, 1, 229.99),

-- Pedido 4 pertenece al cliente: maria837a@gmail.com --
(4, 7, 2, 17.90),
(4, 2, 1, 75.00);

INSERT INTO movimiento_inventario (id_producto, tipo_movimiento, cantidad, motivo) VALUES
(7, 'Entrada', 20, 'alta de stock');

SELECT *
FROM movimiento_inventario;


