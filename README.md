# Ariasport - Aplicación web para concesionario general de motocicletas

## Funcionalidades Destacadas

Ariasport ofrece una serie de características principales para entusiastas/propietarios de motocicletas:

### Configurador de Motocicletas

¡Personaliza tu moto ideal en minutos! Con nuestro configurador de motocicletas, puedes añadir accesorios, visualizar el presupuesto y obtener una vista previa de tu moto completamente personalizada.

### Recomendador de Motocicletas

Nuestro recomendador está diseñado en base a la elección de filtros para sugerirte las motocicletas que mejor se adaptan a tus necesidades y preferencias.

### Comparador de Motocicletas

¿Difícil elección entre varias motos? Con nuestra herramienta de comparación, podrás ver fácilmente las diferencias clave entre diferentes modelos de motocicletas y tomar decisiones informadas.

### Área de Cliente: Gestión de Servicios y Mantenimiento

Mantén tu moto en óptimas condiciones con Ariasport. Programa citas para la revisión de tu motocicleta y lleva un registro de mantenimiento para garantizar que tu moto esté siempre lista para la carretera.

Además de las funcionalidades destacadas, existen varias funcionalidades más que pueden observarse en la aplicación.

## Project setup

### Configuración de base de datos
Vamos a utilizar la imagen mysql de docker. Primeramente instalamos [Docker desktop](https://www.docker.com/products/docker-desktop/) y a continuación ejecutamos en la línea de comandos:

```
docker run -d -p 3306:3306 --name ariasport-db -e MYSQL_ROOT_PASSWORD=secret mysql 
```
A partir de este momento, se descarga automáticamente e instala la imagen oficial de mysql. Después arranca el contenedor, define secret como clave de `root` y asocia MySQL al puerto de la máquina anfitrión 3306.
```
docker exec ariasport-db mysql -psecret -e "create database ariasport; use ariasport; create user 'ariasport' identified by 'secret'; grant all privileges on ariasport.* to 'ariasport'@'%'"
```
Este comando ejecuta la utilidad de administración mysql dentro del contenedor, 
crea la base de datos ariasport, un usuario con el mismo nombre y clave secret 
y finalmente le otorga los permisos necesarios para trabajar con la base 
de datos.

Para el testing, crear una nueva base de datos ariasport_test y dar permisos al usuario creado anteriormente. 
```
docker exec ariasport-db mysql -psecret -e "create database ariasport_test; use ariasport_test; grant all privileges on ariasport_test.* to 'ariasport'@'%'"
```


### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
