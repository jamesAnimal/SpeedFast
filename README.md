![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 💻 Sincronizando procesos en sistemas concurrentes - Desarrollo Orientado a Objetos II

---

## 👤 Autor del proyecto
- **Carrera:** Analista Programador.
- **Asignatura:** Desarrollo Orientado a Objetos II (005A).
- **Sede:** Online.
- **Profesor:** Eithel González Rojas.
- **Nombre completo:** Jaime Seguel Retamales.

---

## 📘 Descripción general del sistema
Proyecto de la Semana 5 de Desarrollo Orientado a Objetos II, desarrollado **desde cero**. Simula una zona de carga compartida por la que pasan varios repartidores al mismo tiempo, controlando el acceso concurrente para evitar que dos repartidores retiren el mismo pedido.

La clase ZonaDeCarga guarda los pedidos pendientes en una cola, con los métodos agregarPedido() y retirarPedido() con synchronized para que el retiro sea seguro entre hilos. Cada Repartidor implementa Runnable, retira pedidos de la zona compartida y los entrega uno a uno, actualizando su estado PENDIENTE → EN_REPARTO → ENTREGADO (enum EstadoPedido) y simulando el tiempo de entrega con Thread.sleep(Random). Main agrega 6 pedidos a la zona de carga y lanza 3 repartidores en paralelo con ExecutorService.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

**1.** **Clona el repositorio desde GitHub:**
[https://github.com/jamesAnimal/SpeedFast.git](https://github.com/jamesAnimal/SpeedFast.git)

**2.** **Abre el proyecto en IntelliJ IDEA.**

**3.** **Ejecuta el archivo `Main.java`** dentro del paquete `speedfast`.

---

## 🕓 Revisar entregas de semanas anteriores
El trabajo de las semanas 1 a 4 no está en el árbol de archivos actual — este proyecto se rehízo desde cero para la Semana 5. Ese código sigue disponible en el **historial de commits** del repositorio:

- `git log --oneline` para ver la lista de commits.
- Busca el commit de la semana que quieras revisar (ej. `DOOII.S4.SpeedFast.JaimeSeguel`) y ábrelo directo en GitHub, o haz `git checkout <commit>` localmente.

---

**Repositorio GitHub:** [https://github.com/jamesAnimal/SpeedFast](https://github.com/jamesAnimal/SpeedFast)

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Desarrollo Orientado a Objetos II | Semana 5.
