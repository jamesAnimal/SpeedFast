![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 💻 Conectando aplicaciones Java con bases de datos mediante JDBC - Desarrollo Orientado a Objetos II

---

## 👤 Autor del proyecto
- **Carrera:** Analista Programador.
- **Asignatura:** Desarrollo Orientado a Objetos II (005A).
- **Sede:** Online.
- **Profesor:** Eithel González Rojas.
- **Nombre completo:** Jaime Seguel Retamales.

---

## 📘 Descripción general del sistema
Proyecto de la Semana 7 de Desarrollo Orientado a Objetos II. Se construye sobre la interfaz de la Semana 6, agregándole conexión real a una base de datos MySQL.

Hasta la semana pasada, los pedidos y repartidores se guardaban solo en memoria, así que se perdían cada vez que cerrabas el programa. Ahora se guardan en una base de datos MySQL (`speedfast_db`), usando JDBC para conectar Java con la base. Cada tabla de la base tiene su propia clase DAO (`PedidoDAO`, `RepartidorDAO`, `EntregaDAO`) encargada de guardar y consultar esos datos, así el código que arma la interfaz no tiene que preocuparse de escribir SQL directamente.

Al registrar un pedido, se guarda de inmediato en la base. Al presionar "Asignar repartidores e iniciar entregas", si los 3 repartidores fijos todavía no existen en la base, se crean ahí mismo, y después se lanzan como hilos en paralelo (mismo mecanismo de `ExecutorService` de la semana anterior). Cada vez que un repartidor entrega un pedido, se actualiza el estado en la base y se guarda un registro de esa entrega, con la fecha, la hora, y a qué pedido y repartidor corresponde.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

**1.** **Clona el repositorio desde GitHub:**
[https://github.com/jamesAnimal/SpeedFast.git](https://github.com/jamesAnimal/SpeedFast.git)

**2.** **Abre el proyecto en IntelliJ IDEA.**

**3.** **Ten MySQL instalado y corriendo**, y crea la base de datos ejecutando el script de tablas (`CREATE DATABASE speedfast_db` y las tablas `repartidor`, `pedido`, `entrega`).

**4.** **Cambia la contraseña** en `src/speedfast/dao/ConexionDB.java` por la contraseña real de tu usuario `root` de MySQL.

**5.** **Ejecuta el archivo `Main.java`** dentro del paquete `speedfast.main`.

---

## 🕓 Revisar entregas de semanas anteriores
El trabajo de las semanas 1 a 4 no está en el árbol de archivos actual (se rehízo desde cero en la Semana 5). Ese código sigue disponible en el **historial de commits** del repositorio:

- `git log --oneline` para ver la lista de commits.
- Busca el commit de la semana que quieras revisar (ej. `DOOII.S6.SpeedFast.JaimeSeguel`) y ábrelo directo en GitHub, o haz `git checkout <commit>` localmente.

---

**Repositorio GitHub:** [https://github.com/jamesAnimal/SpeedFast](https://github.com/jamesAnimal/SpeedFast)

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Desarrollo Orientado a Objetos II | Semana 7.
