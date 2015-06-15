# Instalación

	La instalación de este producto requiere que se cuente previamente con una instalación operativa de la plataforma Elgg.
	El proceso se divide en tres etapas:
		1. Instalación de los conectores de Elgg
		2. Instalación de la Base de Datos
		3. Instalación del núcleo svrKpax

# 1. INSTALACION DE LOS CONECTORES DE ELGG
     -------------------------------------

	Los cuatro conectores se encuentran en el directorio "mods-kpax"

# 1.1 Requisitos
	- Contar con un servidor web XAMP (Apache 2, MySQL 5, PHP 5)
	- Instalar la plataforma Elgg - http://elgg.org/download.php

# 1.2 Instalación
	- Copiar los cuatro plugins en el directorio "www/elgg/mod/" del servidor web 
	  (entendiendo que Elgg está instalado en "www/elgg", si no, reemplazar adecuadamente)

# 1.3 Configuración

	1.3.1 Activar los conectores, ingresando a Elgg como administrador.
		Ir a "Administration -> Plugins" y pulsar "Activate" sobre cada nuevo plugin

	1.3.2. Generar las claves para interactuar con los servicios web. 
		Siempre como administrador, ir a "Administration -> API Administration" 
		En "Your reference" escribir "kpax"
		Generar el par de claves. interesa la Clave Pública

	1.3.3 Colocar la clave pública en el archivo /www/elgg/mods/kpax/lib/kpaxSrv.php en la línea 12, variable $apiKey = 

# 2. INSTALACION DE LA BASE DE DATOS
     -------------------------------

	Los elementos para instalar la base de datos se encuentran en el directorio "k-pax-master/doc/sql"

# 2.1 Requisitos
	Tener instalado el DBMS MySQL 5

# 2.2 Instalación
	- Crear la base de datos kpax
	- Crear las tablas mediante el script "k-pax-master/doc/sql/kpax.sql"
	- Adecuar las credenciales de acceso a la base de datos con los datos del servidor propio, modificando el archivo "k-pax-master/doc/sql/srvKpax-ds.xml" (Se utilizará en el despliegue del núcleo)

# 3. INSTALACION DEL NUCLEO
     ----------------------

	EL código del núcleo se enceuntra en el directorio "k-pax-master"

# 3.1 Requisitos
	MySQL 5
	jboss 4.2.3
	PHP 5
	java 1.6
	maven 3.0.3

# 3.2 Configuración
	- Determinar dónde se despliega la aplicación, por ejemplo,si el usuario se identifica como kpaxuser: "/home/kpaxuser/server/jboss/server/default/deploy/" 
	- Configurar el archivo "k-pax-master/pom.xml" incorporando esa dirección en "<jbossdeployhome>"
	- Copiar dentro del directorio de despliegue el archivo "k-pax-master/doc/sql/srvKpax-ds.xml" modificado en el punto 2.2
	- Modificar el archivo "k-pax-master/src/main/java/uoc/edu/svrKpax/util/ConstantsKPAX.java":
		- Colocar la clave pública (punto 1.3.2) en la línea 15  ELGG_API_KEY = 
		- Adecuar el valor de URL_ELGG a la dirección del servidor Elgg, por ejemplo "http://localhost/elgg/" (¡No olvidar la barra final!) 

# 3.3 Compilar y desplegar el núcleo

	- Desde una consola de comandos, posicionarse en el directorio que contiene el archivo "pom.xml", en este caso "k-pax-master"
	- Descargar las bibliotecas necesarias ejecutando el comando: "mvn install"
	- Si se trabaja con Eclipse, ejecutar "mvn eclipse:eclipse"
	- Crear un nuevo paquete desplegable de la aplicación mediante "mvn -Denv=local clean package"

# Ejecución

	- Iniciar, si corresponde, jboss:
		- Mediante comandos: "/home/kpaxuser/server/jboss/bin/run.sh"
		- Desde Eclipse, si se lo ha configurado.
	- Con un navegador ingresar en  "http://localhost/elgg" con credenciales válidas.
 
# Incidencias

	Contactar con jbrocca@uoc.edu

# Licencia

	Universitat Oberta de catalunya


