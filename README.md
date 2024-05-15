# api-libreria

Proyecto de libreria operaciones básicas de manejo de datos

## Operaciones
- AltaLibro
- ObtenerLibros
- CambioLibro
- EliminarLibro
- ObtenerCatalogos

## Instalación
Para poder poner en marcha el servicio se deben configurar las variables de ambiente siguientes:
```
- BD_SERVER
- BD_NOMBRE
- BD_USUARIO
- BD_CONTRASENA
```
Agregar configuración de CorsFilter en tomcat en archivo web.xml
```
<filter>
  <filter-name>CorsFilter</filter-name>
  <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
  <init-param>
     <param-name>cors.allowed.origins</param-name>
     <param-value>*</param-value>
   </init-param>
   <init-param>
     <param-name>cors.allowed.methods</param-name>
     <param-value>GET,HEAD,POST,PUT,DELETE,OPTIONS</param-value>
   </init-param>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```