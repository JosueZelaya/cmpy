insert into perfil (id, nombre, sis_activo,creation_time,modification_time,version) values (1,'ROLE_USER',true,current_date,current_date,1);

insert into tipo_publicacion (id,nombre,sis_activo,creation_time,modification_time,version) values (1,'PUBLICACION_PAGADA',true,current_date,current_date,1);

insert into tipo_publicacion (id,nombre,sis_activo,creation_time,modification_time,version) values (2,'PUBLICACION_GRATIS',true,current_date,current_date,1);

insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Multimedia','...',current_date,current_date,null,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('DVD','...',current_date,current_date,1,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('TV','...',current_date,current_date,1,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('SONY','...',current_date,current_date,3,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('RCA','...',current_date,current_date,3,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('LG','...',current_date,current_date,3,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('LG','...',current_date,current_date,2,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('SONY','...',current_date,current_date,2,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Fotografia','...',current_date,current_date,null,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camara Digital','...',current_date,current_date,9,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Camara de Accion','...',current_date,current_date,9,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Nikon','...',current_date,current_date,10,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Canon','...',current_date,current_date,10,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Xiaomi','...',current_date,current_date,11,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('GoPro','...',current_date,current_date,11,true,1);

insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Robotica','...',current_date,current_date,null,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Drone','...',current_date,current_date,16,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Brazo Mecanico','...',current_date,current_date,16,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('vacuum','...',current_date,current_date,16,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Phantom II','...',current_date,current_date,17,true,1);

insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Deportes','...',current_date,current_date,null,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Maquinas','...',current_date,current_date,21,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,categoria_padre_id,sis_activo,version) values ('Bancas','...',current_date,current_date,22,true,1);

-- para insertar este registro primero debe crearse un usuario
insert into tiendaps(id, creation_time, modification_time, sis_activo, version, dominio, key, usuario_id) values (1, current_date, current_date, true, 1, 'prestashop/prestashop/','E87JLYRTFHVTHQAIYB4FKRVZ8J8KGZP9',1);
insert into tiendaps(id, creation_time, modification_time, sis_activo, version, dominio, key, usuario_id) values (2, current_date, current_date, true, 1, 'prestashop/prestashop/','E87JLYRTFHVTHQAIYB4FKRVZ8J8KGZP9',1);