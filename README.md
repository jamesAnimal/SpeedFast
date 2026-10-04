![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 💻 Implementando el patrón DAO y el CRUD completo con Swing - Desarrollo Orientado a Objetos II

---

## 👤 Autor del proyecto
- **Carrera:** Analista Programador.
- **Asignatura:** Desarrollo Orientado a Objetos II (005A).
- **Sede:** Online.
- **Profesor:** Eithel González Rojas.
- **Nombre completo:** Jaime Seguel Retamales.

---

## 📘 Descripción general del sistema
Proyecto de la Semana 8 de Desarrollo Orientado a Objetos II. Se construye sobre la conexión a base de datos de la Semana 7, completando el ciclo CRUD (crear, leer, actualizar, eliminar) para las 3 entidades del sistema: pedidos, repartidores y entregas.

La arquitectura se reorganizó en capas: cada entidad tiene su DAO como interfaz (`PedidoDAO`, `RepartidorDAO`, `EntregaDAO`) con su implementación aparte en el paquete `dao.impl`, y un `SpeedFastControlador` único que conecta la interfaz gráfica con los DAO, validando los datos antes de guardar o actualizar. La vista nunca llama a la base de datos directamente.

La interfaz cambió de formularios fijos a ventanas emergentes (popups): desde `VentanaPrincipal` solo se ven las 3 tablas (Pedidos, Repartidores, Entregas) con sus botones de Registrar/Editar/Eliminar, y cada acción abre un popup aparte con el formulario correspondiente. Al registrar una Entrega, los combobox de Pedido y Repartidor se cargan desde la base de datos mostrando "id - descripción" pero guardando internamente el id.

La simulación de hilos de semanas anteriores se adaptó esta semana para mantenerla activa: el botón "Iniciar Entregas" busca los pedidos en estado PENDIENTE que ya tengan una entrega registrada, y los anima en paralelo (PENDIENTE → EN_REPARTO → ENTREGADO) mostrando el avance en el panel de Actividad.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

**1.** **Clona el repositorio desde GitHub:**
[https://github.com/jamesAnimal/SpeedFast.git](https://github.com/jamesAnimal/SpeedFast.git)

**2.** **Abre el proyecto en IntelliJ IDEA.**

**3.** **Ten MySQL instalado y corriendo**, y crea la base de datos ejecutando el script `db/speedfast_db.sql` (crea las tablas `pedidos`, `repartidores` y `entregas`).

**4.** **Cambia la contraseña** en `src/speedfast/util/ConexionDB.java` por la contraseña real de tu usuario `root` de MySQL.

**5.** **Ejecuta el archivo `Main.java`** dentro del paquete `speedfast.main`.

---

## 🕓 Revisar entregas de semanas anteriores
El trabajo de las semanas 1 a 4 no está en el árbol de archivos actual (se rehízo desde cero en la Semana 5). Ese código sigue disponible en el **historial de commits** del repositorio:

- `git log --oneline` para ver la lista de commits.
- Busca el commit de la semana que quieras revisar (ej. `DOOII.S7.SpeedFast.JaimeSeguel`) y ábrelo directo en GitHub, o haz `git checkout <commit>` localmente.

---

**Repositorio GitHub:** [https://github.com/jamesAnimal/SpeedFast](https://github.com/jamesAnimal/SpeedFast)

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Desarrollo Orientado a Objetos II | Semana 8.
