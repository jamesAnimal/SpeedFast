![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 💻 Diseñando interfaces gráficas para aplicaciones en Java - Desarrollo Orientado a Objetos II

---

## 👤 Autor del proyecto
- **Carrera:** Analista Programador.
- **Asignatura:** Desarrollo Orientado a Objetos II (005A).
- **Sede:** Online.
- **Profesor:** Eithel González Rojas.
- **Nombre completo:** Jaime Seguel Retamales.

---

## 📘 Descripción general del sistema
Proyecto de la Semana 6 de Desarrollo Orientado a Objetos II. A diferencia de la semana anterior, esta vez **se construye sobre el modelo de la Semana 5** (`Pedido`, `EstadoPedido`, `ZonaDeCarga`, `Repartidor`), agregándole una interfaz gráfica de escritorio hecha con Java Swing.

`VentanaPrincipal` reúne en una sola ventana el registro de pedidos (formulario con ID, Dirección y Tipo), el listado en una tabla, y un registro de actividad en vivo que reemplaza la consola, mostrando en tiempo real lo que hacen los repartidores mientras entregan. Se optó por una sola ventana en vez de varias separadas para mejorar la experiencia de usuario. Así, el usuario registra un pedido y lo ve aparecer de inmediato en la misma tabla, sin tener que cambiar de ventana, lo que hace el flujo más directo e intuitivo.

Al presionar "Iniciar Entregas" se lanzan los 3 repartidores en paralelo (mismo mecanismo de `ExecutorService` y `ZonaDeCarga` compartida de la semana anterior), y sus mensajes de actividad se muestran en la propia ventana en vez de por consola.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

**1.** **Clona el repositorio desde GitHub:**
[https://github.com/jamesAnimal/SpeedFast.git](https://github.com/jamesAnimal/SpeedFast.git)

**2.** **Abre el proyecto en IntelliJ IDEA.**

**3.** **Ejecuta el archivo `Main.java`** dentro del paquete `speedfast.main`.

---

## 🕓 Revisar entregas de semanas anteriores
El trabajo de las semanas 1 a 4 no está en el árbol de archivos actual (se rehízo desde cero en la Semana 5). Ese código sigue disponible en el **historial de commits** del repositorio:

- `git log --oneline` para ver la lista de commits.
- Busca el commit de la semana que quieras revisar (ej. `DOOII.S4.SpeedFast.JaimeSeguel`) y ábrelo directo en GitHub, o haz `git checkout <commit>` localmente.

---

**Repositorio GitHub:** [https://github.com/jamesAnimal/SpeedFast](https://github.com/jamesAnimal/SpeedFast)

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Desarrollo Orientado a Objetos II | Semana 6.
