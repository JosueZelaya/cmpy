insert into perfil (perfil_id, nombre, sis_activo,creation_time,modification_time,version) values (1,'ROLE_USER',true,current_date,current_date,1);

insert into tipo_publicacion (tipo_publicacion_id,nombre,sis_activo,creation_time,modification_time,version) values (1,'PUBLICACION_PAGADA',true,current_date,current_date,1);

insert into tipo_publicacion (tipo_publicacion_id,nombre,sis_activo,creation_time,modification_time,version) values (2,'PUBLICACION_GRATIS',true,current_date,current_date,1);

insert into categoria (nombre,descripcion,modification_time,creation_time,fk_categoria,sis_activo,version) values ('Multimedia','...',current_date,current_date,null,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,fk_categoria,sis_activo,version) values ('DVD','...',current_date,current_date,1,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,fk_categoria,sis_activo,version) values ('TV','...',current_date,current_date,1,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,fk_categoria,sis_activo,version) values ('SONY','...',current_date,current_date,3,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,fk_categoria,sis_activo,version) values ('RCA','...',current_date,current_date,3,true,1);
insert into categoria (nombre,descripcion,modification_time,creation_time,fk_categoria,sis_activo,version) values ('LG','...',current_date,current_date,3,true,1);