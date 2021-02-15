# Дипломный проект по курсу «Тестировщик ПО»

[План автоматизации тестирования веб-сервиса покупки тура](https://github.com/chugad/qa-diploma/blob/master/documents/Plan.md)

## Инструкция по запуску автотестов

1. Запустить контейнер командой: <br>
* ```docker-compose up -d --build``` <br>

2. Запустить приложение командой: <br>
* `java -jar ./artifacts/aqa-shop.jar` <br>

3. Запустить автоматизированное тестирование командой: <br>
* `gradlew test` <br>
