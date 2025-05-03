# 🏬 ms-warehouse

Микросервис `ms-warehouse` отвечает за приём и обработку сообщений из Kafka, содержащих данные о грузах (cargo), а также за управление складами и заказами. Реализован на Spring Boot, взаимодействует с PostgreSQL, использует Swagger UI и Kafka UI.

---

## 🚀 Запуск сервиса

Перед запуском убедитесь, что установлены **Docker** и **Gradle**.

### 🔨 Сборка приложения

```bash
.\gradlew clean build -x test
```

### 🐳 Запуск через Docker Compose

```bash
docker-compose up --build
```

### 🔁 Отключение сервиса

```bash
docker-compose down -v
```

---

## 📚 Swagger UI

Для тестирования и просмотра API:

👉 [Swagger UI](http://localhost:8091/swagger-ui/index.html)

---

## ⚙️ Используемые технологии

- Java 17
- Spring Boot 3
- Spring Kafka
- PostgreSQL
- Liquibase
- Docker / Docker Compose
- Swagger
- Kafka UI

---

## 👨‍💻 Автор

GitHub: [wine-tribe](https://github.com/wine-tribe)
