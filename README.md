![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 💻 Ejecutando tareas en paralelo con hilos en Java - Desarrollo Orientado a Objetos II

---

## 👤 Autor del proyecto
- **Carrera:** Analista Programador.
- **Asignatura:** Desarrollo Orientado a Objetos II (005A).
- **Sede:** Online.
- **Profesor:** Eithel González Rojas.
- **Nombre completo:** Jaime Seguel Retamales.

---

## 📘 Descripción general del sistema
Proyecto de la Semana 4 de Desarrollo Orientado a Objetos II. Incorpora programación concurrente sobre la jerarquía de **Pedido** y las interfaces de la semana anterior (**Despachable**, **Cancelable**, **Rastreable**). La nueva clase **Repartidor** implementa `Runnable` y entrega su lista de pedidos asignada en un hilo independiente; `ExecutorService` ejecuta a los 3 repartidores en paralelo, y `Thread.sleep()` con valores aleatorios simula el tiempo de cada entrega. Antes de despachar, cada pedido tiene, mediante la clase `Random`, una probabilidad de 1/6 de ser cancelado (`Cancelable`), quedando así fuera de la entrega.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

**1.** **Clona el repositorio desde GitHub:**
[https://github.com/jamesAnimal/SpeedFast.git](https://github.com/jamesAnimal/SpeedFast.git)

**2.** **Abre el proyecto en IntelliJ IDEA.**

**3.** **Ejecuta el archivo `Main.java`** dentro del paquete `speedfast`.

---

**Repositorio GitHub:** [https://github.com/jamesAnimal/SpeedFast](https://github.com/jamesAnimal/SpeedFast)

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Desarrollo Orientado a Objetos II | Semana 4.
