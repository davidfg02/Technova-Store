insert into usuario (email,password, rol)
values
('admin@Technova.es','1234','ADMINISTRADOR'),
('oficina@Technova.es','0987','OFICINA'),
('cliente@Tgmail.com','45678','CLIENTE');
insert into producto (sku,nombre,descripcion,precio,stock,categoria,imagen)
values
('algo123456','Monitor Samsung Odyssey G5','Monitor curvo, 165Hz, 32 pulgadas',199.99,'30','Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/1081/10811202/1210-samsung-odyssey-ls27cg552euxen-27-led-qhd-165hz-freesync-curvo-review.jpg'),
('nada123456','Teclado NewSkil Pyro pro','Teclado mecanico, 65%, inalambrico',75.00,'100','Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/1086/10861495/1550-newskill-pyros-pro-teclado-gaming-inalambrico-mecanico-75-hotswap-blanco-foto.jpg'),
('idpro14345','Memoria Ram corsair vengace',' 2*16gb ddr5 6000MHz','9',600.99,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1076/10764411/1517-corsair-vengeance-ddr5-6000mhz-32gb-2x16gb-cl30.jpg'),
('kkre123456','MSI GeForce RTX 5070 Ti VENTUS ',' 3X OC 16GB GDDR7 Reflex 2 RTX AI DLSS4','90',1400.90,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1086/10868478/1405-msi-geforce-rtx-5070-ti-ventus-3x-oc-16gb-gddr7-dlss4.jpg'),
('huka123445','Corsair MP600 GS 1TB M.2 Gen4 PCIe x4 NVMe ',' Velocidad lectura secuencial de hasta 4.800 MB/s','78',112.22,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1063/10635678/1152-corsair-mp600-gs-1tb-m2-gen4-pcie-x4-nvme.jpg'),
('husja12345','CAMD Ryzen 7 7800X3D 4.2 GHz/5 GHz ',' 8 núcleos y 16 hilos ideales para gaming','2',410.90,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1066/10665103/1500-amd-ryzen-7-7800x3d-42-ghz-5-ghz-caracteristicas.jpg'),
('grteja1280','Logitech G G102 LightSync Ratón Gaming 8000DPI Negro ',' Precisión profesional y respuesta ultra rápida.Sensor óptico de hasta 8000 DPI','10',17.90,'Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/32/328329/1543-logitech-g-g102-lightsync-raton-gaming-8000dpi-negro.jpg'),
('idhgo84345','Sistema Operativo Microsoft Windows 11 Pro ',' Windows 11 Pro 64bit Español 1 Licencia DVD 64GB 4GB 1GHz','3',200.99,'Software','https://thumb.pccomponentes.com/w-530-530/articles/1100/11001061/1190-sistema-operativo-microsoft-windows-11-pro-64-bits-espanol-licencia-oem-permanente.jpg'),
('illo422215','Módem de red móvil D-Link F518/M 5G Wi-Fi 6 USB Type-C 8000 mAh Blanco',' Wi-Fi 6 dual band hasta 1800 Mbit/s,Diseño portátil, robusto y profesional ','0',295.87,'Redes','https://thumb.pccomponentes.com/w-530-530/articles/1093/10935747/159-modem-de-red-movil-d-link-f518-m-5g-wi-fi-6-usb-type-c-8000-mah-blanco.jpg'),
('irefo14345','Apple AirPods Pro 3 Auriculares Bluetooth con Cancelación Activa de Ruido USB-C Blancos',' Resistencia extrema IP57. Total protección frente al polvo, sudor y agua, especialmente pensados para movilidad, deportes al aire libre o entornos industriales y técnicos','9',229.99,'Perifericos','https://thumb.pccomponentes.com/w-530-530/articles/1095/10952804/1501-apple-airpods-pro-3-auriculares-bluetooth-con-cancelacion-activa-de-ruido-usb-c-blancos.jpg'),
('perro12873','Corsair NAUTILUS 240 RS Kit Refrigeración Líquida 240mm Negro','La refrigeración líquida de CPU CORSAIR NAUTILUS RS ofrece un enfriamiento eficiente y silencioso con una conectividad sencilla: no requiere controlador, se conecta directamente a la placa base. Refrigeración eficiente y silenciosa para ayudar a que su CPU alcance su máximo potencial. Se conecta directamente a la placa base, no se necesitan controladores adicionales.','9',80.24,'Componentes','https://thumb.pccomponentes.com/w-530-530/articles/1086/10863489/1788-corsair-nautilus-240-rs-kit-refrigeracion-liquida-240mm-negro.jpg');

select * from usuario;
select * from producto;