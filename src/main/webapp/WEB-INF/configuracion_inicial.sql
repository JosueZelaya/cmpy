insert into perfil (id, nombre, sis_activo,creation_time,modification_time,version) values (1,'ROLE_USER',true,current_date,current_date,1);

insert into tipo_publicacion (id,nombre,sis_activo,creation_time,modification_time,version) values (1,'PUBLICACION_PAGADA',true,current_date,current_date,1);
insert into tipo_publicacion (id,nombre,sis_activo,creation_time,modification_time,version) values (2,'PUBLICACION_GRATIS',true,current_date,current_date,1);
insert into tipo_notificacion(id,creation_time,modification_time,sis_activo,version,nombre) values (1,current_date,current_date,true,1,'COMENTARIO_RECIBIDO');
insert into tipo_notificacion(id,creation_time,modification_time,sis_activo,version,nombre) values (2,current_date,current_date,true,1,'MENSAJE_RECIBIDO');

--primer nivel productos
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Multimedia','...',current_date,current_date,null,true,1);--1
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Telefonos','...',current_date,current_date,null,true,1);--2
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Computadoras','...',current_date,current_date,null,true,1);--3
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Smartwatch','...',current_date,current_date,null,true,1);--4
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Juegos','...',current_date,current_date,null,true,1);--5
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Electrodomesticos y Hogar','...',current_date,current_date,null,true,1);--6
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Juguetes','...',current_date,current_date,null,true,1);--7
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Maquinaria','...',current_date,current_date,null,true,1);--8
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Ropa','...',current_date,current_date,null,true,1);--9
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Calzado','...',current_date,current_date,null,true,1);--10
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Papeleria','...',current_date,current_date,null,true,1);--11
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Accesorios Dama y Caballero','...',current_date,current_date,null,true,1);--12
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Inmuebles','...',current_date,current_date,null,true,1);--13
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Automotores','...',current_date,current_date,null,true,1);--14
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Electronica','...',current_date,current_date,null,true,1);--15
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Construccion','...',current_date,current_date,null,true,1);--16
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Medicamentos','...',current_date,current_date,null,true,1);--17
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Joyeria','...',current_date,current_date,null,true,1);--18
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Herramientas','...',current_date,current_date,null,true,1);--19
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Jardineria','...',current_date,current_date,null,true,1);--20
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Deportes','...',current_date,current_date,null,true,1);--21
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cosmeticos','...',current_date,current_date,null,true,1);--22
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Aparatos Medicos','...',current_date,current_date,null,true,1);--23
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Aparatos Cientificos','...',current_date,current_date,null,true,1);--24
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Quimica','...',current_date,current_date,null,true,1);--25
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,null,true,1);--26

--primer nivel servicios
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Albañileria','...',current_date,current_date,null,true,1);--27
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Carpinteria','...',current_date,current_date,null,true,1);--28
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Fontaneria','...',current_date,current_date,null,true,1);--29
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Albañileria','...',current_date,current_date,null,true,1);--30
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Radio Tecnico','...',current_date,current_date,null,true,1);--31
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Albañileria','...',current_date,current_date,null,true,1);--32
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Electricos','...',current_date,current_date,null,true,1);--33
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Ingenieria Civil','...',current_date,current_date,null,true,1);--34
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Diseño Web','...',current_date,current_date,null,true,1);--35
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Programacion','...',current_date,current_date,null,true,1);--36
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Consultoria','...',current_date,current_date,null,true,1);--37
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Auditoria','...',current_date,current_date,null,true,1);--38
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Corte y Confeccion','...',current_date,current_date,null,true,1);--39
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Mecanica Industrial','...',current_date,current_date,null,true,1);--40
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Mecanica Automotriz','...',current_date,current_date,null,true,1);--41
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Electronica Digital','...',current_date,current_date,null,true,1);--42
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Encomiendas','...',current_date,current_date,null,true,1);--43
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Freelance','...',current_date,current_date,null,true,1);--44
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Transporte','...',current_date,current_date,null,true,1);--45
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Alquileres de Equipo','...',current_date,current_date,null,true,1);--46
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Discomovil','...',current_date,current_date,null,true,1);--47
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Festejos','...',current_date,current_date,null,true,1);--48
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Maestros','...',current_date,current_date,null,true,1);--49
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Abogado y Notario','...',current_date,current_date,null,true,1);--50
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Destista','...',current_date,current_date,null,true,1);--51
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Laboratorio','...',current_date,current_date,null,true,1);--52
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Colegios','...',current_date,current_date,null,true,1);--53
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio de Universidades','...',current_date,current_date,null,true,1);--54
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Servicio Medico','...',current_date,current_date,null,true,1);--55
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Agroservicio','...',current_date,current_date,null,true,1);--56
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros Servicios','...',current_date,current_date,null,true,1);--57


--segundo nivel productos
--Multimedia
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Fotografia','...',current_date,current_date,1,true,1);--58
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Video','...',current_date,current_date,1,true,1);--59
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Audio','...',current_date,current_date,1,true,1);--60
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Peliculas','...',current_date,current_date,1,true,1);--61
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Musica','...',current_date,current_date,1,true,1);--62
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Series','...',current_date,current_date,1,true,1);--63
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Anime','...',current_date,current_date,1,true,1);--64
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Documentales','...',current_date,current_date,1,true,1);--65
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,1,true,1);--66
--Telefonos
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Android','...',current_date,current_date,2,true,1);--67
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Windows','...',current_date,current_date,2,true,1);--68
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('iOS','...',current_date,current_date,2,true,1);--69
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tactil','...',current_date,current_date,2,true,1);--70
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Qwerty','...',current_date,current_date,2,true,1);--71
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Bar Phone','...',current_date,current_date,2,true,1);--72
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Flit Phone','...',current_date,current_date,2,true,1);--73
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Phablet','...',current_date,current_date,2,true,1);--74
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Slider Phone','...',current_date,current_date,2,true,1);--75
--Computadoras
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Laptops','...',current_date,current_date,3,true,1);--76
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Netbooks','...',current_date,current_date,3,true,1);--77
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Ultrabooks','...',current_date,current_date,3,true,1);--78
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Macbooks','...',current_date,current_date,3,true,1);--79
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tablets','...',current_date,current_date,3,true,1);--80
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('PC','...',current_date,current_date,3,true,1);--81
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mac','...',current_date,current_date,3,true,1);--82
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cables','...',current_date,current_date,3,true,1);--83
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Teclados','...',current_date,current_date,3,true,1);--84
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mouse','...',current_date,current_date,3,true,1);--85
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Monitores','...',current_date,current_date,3,true,1);--87
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tarjetas','...',current_date,current_date,3,true,1);--88
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Memorias','...',current_date,current_date,3,true,1);--89
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Procesadores','...',current_date,current_date,3,true,1);--90
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Sistemas de Sonido','...',current_date,current_date,3,true,1);--91
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cases','...',current_date,current_date,3,true,1);--92
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Impresoras','...',current_date,current_date,3,true,1);--93
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Escaneres','...',current_date,current_date,3,true,1);--94
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Motherboards','...',current_date,current_date,3,true,1);--95
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Disco Duros','...',current_date,current_date,3,true,1);--96
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Audifonos','...',current_date,current_date,3,true,1);--97
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Dispositivos USB','...',current_date,current_date,3,true,1);--98
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Programas','...',current_date,current_date,3,true,1);--99
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Muebles','...',current_date,current_date,3,true,1);--100
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Respuestos','...',current_date,current_date,3,true,1);--101
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,3,true,1);--102
--Smartwatch
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Android','...',current_date,current_date,4,true,1);--103
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('iOS','...',current_date,current_date,4,true,1);--104
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tizen','...',current_date,current_date,4,true,1);--105
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,4,true,1);--106
--Juegos
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('PlayStation 4','...',current_date,current_date,5,true,1);--107
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('PlayStation 3','...',current_date,current_date,5,true,1);--108
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('PlaySation Vita','...',current_date,current_date,5,true,1);--109
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('PSP','...',current_date,current_date,5,true,1);--110
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Xbox One','...',current_date,current_date,5,true,1);--111
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Xbox 360','...',current_date,current_date,5,true,1);--112
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Wii U','...',current_date,current_date,5,true,1);--113
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Nintendo DS','...',current_date,current_date,5,true,1);--114
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Wii','...',current_date,current_date,5,true,1);--115
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,5,true,1);--116
--Electrodomesticos y Hogar
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cocinas','...',current_date,current_date,6,true,1);--117
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Muebles','...',current_date,current_date,6,true,1);--118
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Planchas','...',current_date,current_date,6,true,1);--119
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Licuadoras','...',current_date,current_date,6,true,1);--120
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Ventiladores','...',current_date,current_date,6,true,1);--121
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Aire Acondicionado','...',current_date,current_date,6,true,1);--122
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camas','...',current_date,current_date,6,true,1);--123
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mesas','...',current_date,current_date,6,true,1);--124
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Sillas','...',current_date,current_date,6,true,1);--125
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Refrijeradoras','...',current_date,current_date,6,true,1);--126
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Roperos','...',current_date,current_date,6,true,1);--127
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Comedores','...',current_date,current_date,6,true,1);--128
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Pantries','...',current_date,current_date,6,true,1);--129
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Jugueteras','...',current_date,current_date,6,true,1);--130
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Modulos','...',current_date,current_date,6,true,1);--131
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Decoracion','...',current_date,current_date,6,true,1);--132
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,6,true,1);--133
--Juguetes
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Plasticos','...',current_date,current_date,7,true,1);--134
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Radio Control','...',current_date,current_date,7,true,1);--135
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Juegos de Mesa','...',current_date,current_date,7,true,1);--136
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Peluches','...',current_date,current_date,7,true,1);--137
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,7,true,1);--138
--Ropa
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Niños','...',current_date,current_date,9,true,1);--139
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Niñas','...',current_date,current_date,9,true,1);--140
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Hombre','...',current_date,current_date,9,true,1);--141
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mujer','...',current_date,current_date,9,true,1);--142
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Telas','...',current_date,current_date,9,true,1);--143
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,9,true,1);--144
--Calzado
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Niños','...',current_date,current_date,10,true,1);--145
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Niñas','...',current_date,current_date,10,true,1);--146
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Hombre','...',current_date,current_date,10,true,1);--147
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mujer','...',current_date,current_date,10,true,1);--148
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,10,true,1);--149
--Papeleria
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Papel','...',current_date,current_date,11,true,1);--150
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cuadernos','...',current_date,current_date,11,true,1);--151
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Libros','...',current_date,current_date,11,true,1);--152
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Boligrafos','...',current_date,current_date,11,true,1);--153
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Lapices','...',current_date,current_date,11,true,1);--154
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Borradores','...',current_date,current_date,11,true,1);--155
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,11,true,1);--156
--Accesorios Dama y Caballero
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Relojes','...',current_date,current_date,12,true,1);--157
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Anillos','...',current_date,current_date,12,true,1);--158
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Pulseras','...',current_date,current_date,12,true,1);--159
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Collares','...',current_date,current_date,12,true,1);--160
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mochilas','...',current_date,current_date,12,true,1);--161
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Sombreros','...',current_date,current_date,12,true,1);--162
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Bolsos','...',current_date,current_date,12,true,1);--163
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Carteras','...',current_date,current_date,12,true,1);--164
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Gorras','...',current_date,current_date,12,true,1);--165
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Lentes','...',current_date,current_date,12,true,1);--166
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,12,true,1);--167
--Inmuebles
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Terrenos','...',current_date,current_date,13,true,1);--168
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Apartamentos','...',current_date,current_date,13,true,1);--169
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Casas','...',current_date,current_date,13,true,1);--170
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Edificios','...',current_date,current_date,13,true,1);--171
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,13,true,1);--172
--Automotores
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Autos','...',current_date,current_date,14,true,1);--173
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Motocicletas','...',current_date,current_date,14,true,1);--174
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tractores','...',current_date,current_date,14,true,1);--175
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Autobuses','...',current_date,current_date,14,true,1);--176
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camiones','...',current_date,current_date,14,true,1);--177
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Repuestos','...',current_date,current_date,14,true,1);--178
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Accesorios','...',current_date,current_date,14,true,1);--179
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,14,true,1);--180
--Deportes
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camisas','...',current_date,current_date,21,true,1);--181
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Calsonetas','...',current_date,current_date,21,true,1);--182
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Trajes','...',current_date,current_date,21,true,1);--183
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tacos','...',current_date,current_date,21,true,1);--184
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Pelotas','...',current_date,current_date,21,true,1);--185
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Accesorios','...',current_date,current_date,21,true,1);--186
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,21,true,1);--187



--tercer nivel productos
--Fotografia
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camaras Digitales','...',current_date,current_date,58,true,1);--188
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camaras Antiguas','...',current_date,current_date,58,true,1);--189
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Iluminacion','...',current_date,current_date,58,true,1);--190
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tarjetas de Memoria','...',current_date,current_date,58,true,1);--191
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Adaptadores','...',current_date,current_date,58,true,1);--192
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Lentes','...',current_date,current_date,58,true,1);--193
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cables','...',current_date,current_date,58,true,1);--194
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Marcos','...',current_date,current_date,58,true,1);--195
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Repuestos','...',current_date,current_date,58,true,1);--196
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,58,true,1);--197
--Video
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('TV','...',current_date,current_date,59,true,1);--198
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Blu-Ray','...',current_date,current_date,59,true,1);--199
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('DVD','...',current_date,current_date,59,true,1);--200
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('MP4','...',current_date,current_date,59,true,1);--201
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Videocamaras','...',current_date,current_date,59,true,1);--202
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Proyectores','...',current_date,current_date,59,true,1);--203
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Iluminacion','...',current_date,current_date,59,true,1);--204
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cintas','...',current_date,current_date,59,true,1);--205
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Tripodos','...',current_date,current_date,59,true,1);--206
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Pantallas','...',current_date,current_date,59,true,1);--207
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cables','...',current_date,current_date,59,true,1);--208
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Adaptadores','...',current_date,current_date,59,true,1);--209
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Repuestos','...',current_date,current_date,59,true,1);--210
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,59,true,1);--211
--Audio
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('MP3','...',current_date,current_date,60,true,1);--212
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Equipos de Sonido','...',current_date,current_date,60,true,1);--213
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Radios','...',current_date,current_date,60,true,1);--214
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Amplificadores','...',current_date,current_date,60,true,1);--215
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Mixers','...',current_date,current_date,60,true,1);--216
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Powers','...',current_date,current_date,60,true,1);--217
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Consolas','...',current_date,current_date,60,true,1);--218
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Bocinas','...',current_date,current_date,60,true,1);--219
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Auriculares','...',current_date,current_date,60,true,1);--220
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Cables','...',current_date,current_date,60,true,1);--221
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Adaptadores','...',current_date,current_date,60,true,1);--222
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Audifonos','...',current_date,current_date,60,true,1);--223
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Repuestos','...',current_date,current_date,60,true,1);--224
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Otros','...',current_date,current_date,60,true,1);--225

-- para insertar este registro primero debe crearse un usuario
insert into tiendaps(id, creation_time, modification_time, sis_activo, version, dominio, key, usuario_id) values (1, current_date, current_date, true, 1, 'prestashop/prestashop/','E87JLYRTFHVTHQAIYB4FKRVZ8J8KGZP9',1);
insert into tiendaps(id, creation_time, modification_time, sis_activo, version, dominio, key, usuario_id) values (2, current_date, current_date, true, 1, 'prestashop/prestashop/','E87JLYRTFHVTHQAIYB4FKRVZ8J8KGZP9',1);