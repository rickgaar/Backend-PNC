# Integrantes del proyecto
Marco Gabriel Arana Renderos 00037822

Ricardo Andrés García Barahona 00148922

Josemaría Miguel Muñoz Romero 00120422

Paola Alexandra Pérez Pérez 00117222 

Rodrigo Orlando Zepeda Ramos 00175520 

# Descripción del proyecto
El presente proyecto es una REST API elaborada con Spring Boot. En esta, se describen metodos que se usan en un aula virtual, tales como registrar usuarios, un inicio de sesion, un CRUD completo para las materias y los exámenes. 

# Instrucciones de instalación
Para poder hacer uso de esta aplicación, se necesita primero un proyecto de Java con el Framework de Springboot, ademas de agregar las dependencias necesarias descritas en el archivo pom.xml para su correcto funcionamiento.

# Instrucciones de uso
Clonar repositorio<br />
Abrir proyecto en IntelliJ<br />
Correr proyecto<br />
Abrir Insominia<br />
Importar colección a Insomnia<br />
Registrar un usuario con la ruta de register y actualizarlo a un usuario administrador con el siguiente script:<br />
SCRPIT: UPDATE usuario SET role_id = 2 WHERE username = {username};<br />
Probar ruta de login para obtener token<br />
Probar todas las demás rutas
