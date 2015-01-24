DROP TABLE IF EXISTS departamentos_usuarios;
DROP TABLE IF EXISTS departamentos;
DROP TABLE IF EXISTS categorias_articulos;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS comentarios;
DROP TABLE IF EXISTS articulos;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS datos_usuarios;

DROP SEQUENCE IF EXISTS usuarios_id_seq;
DROP SEQUENCE IF EXISTS datos_usuarios_id_seq;
DROP SEQUENCE IF EXISTS articulo_id_seq;
DROP SEQUENCE IF EXISTS comentario_id_seq;
DROP SEQUENCE IF EXISTS categoria_id_seq;
DROP SEQUENCE IF EXISTS departamento_id_seq;


/************** TABLAS PARA EJERCICIOS ONE-TO-ONE ***************/
CREATE SEQUENCE usuarios_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE datos_usuarios_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE datos_usuarios(
   id INT DEFAULT NEXTVAL('datos_usuarios_id_seq'),
   biografia TEXT,
   fecha_registro DATE,PRIMARY KEY(id)
);

CREATE TABLE usuarios(
   id INT DEFAULT NEXTVAL('usuarios_id_seq'),   
   datos_autor_id INT,
   nombre VARCHAR(600),
   email VARCHAR(500),
   password VARCHAR(500),
   rol VARCHAR(500),
   activo BOOLEAN,
   PRIMARY KEY(id),
   FOREIGN KEY(datos_autor_id) REFERENCES datos_usuarios
);


/************** TABLAS PARA EJERCICIO ONE-TO-MANY ***************/
CREATE SEQUENCE articulo_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE comentario_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE articulos(
   id INT DEFAULT NEXTVAL('articulo_id_seq'),
   usuario_id INT, 
   titulo VARCHAR(500), 
   contenido TEXT, 
   fecha_creacion DATE, 
   PRIMARY KEY(id), 
   FOREIGN KEY(usuario_id) REFERENCES usuarios
);

CREATE TABLE comentarios(
   id INT DEFAULT NEXTVAL('comentario_id_seq'),
   articulo_id INT,
   comentario TEXT,
   comentarista VARCHAR(500),
   fecha_creacion DATE,
   PRIMARY KEY (id),
   FOREIGN KEY(articulo_id) REFERENCES articulos
);

/************** TABLAS PARA EJERCICIO MANY-TO-MANY ***************/
CREATE SEQUENCE categoria_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE categorias(
   id INT DEFAULT NEXTVAL('categoria_id_seq'),
   nombre VARCHAR(200),
   PRIMARY KEY(id)
);

CREATE TABLE categorias_articulos(
   categoria_id INT ,
   articulo_id INT,
   PRIMARY KEY(categoria_id, articulo_id),
   FOREIGN KEY(categoria_id) REFERENCES categorias,
   FOREIGN KEY(articulo_id) REFERENCES articulos
);

CREATE SEQUENCE departamento_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE departamentos(
   id INT DEFAULT NEXTVAL('departamento_id_seq') ,
   nombre VARCHAR(200),
   PRIMARY KEY(id)
);

CREATE TABLE departamentos_usuarios(
   departamento_id INT,
   usuario_id INT,
   PRIMARY KEY(departamento_id, usuario_id),
   FOREIGN KEY(departamento_id) REFERENCES departamentos,
   FOREIGN KEY(usuario_id) REFERENCES usuarios
);
