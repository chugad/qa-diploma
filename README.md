# Дипломный проект по курсу «Тестировщик ПО»

[План автоматизации тестирования веб-сервиса покупки тура](https://github.com/chugad/qa-diploma/blob/master/documents/Plan.md)

## Инструкция по запуску автотестов

1. Запустить контейнер командой: <br>
* ```docker-compose up -d --build``` <br>

2.1 Запустить приложение для работы с MySQL командой: <br>
* `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar` <br>

либо

2.2 Запустить приложение для работы с PostgreSQL командой: <br>
* `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar` <br>


3. Запустить автоматизированное тестирование командой: <br>
* `gradlew test` <br>
