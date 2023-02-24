# TestTask
Тестовое задание для Райффайзен банка.
## Оглавление
 1. [Описание проекта](#1)
 2. [TODO лист](#2)
 3. [Используемые технологии](#3)
 4. [Демонстрация работы](#4)
 5. [Примечание](#5)
 
<a name="1"><h2>Описание проекта</h2></a>
Реализовать приложение для автоматизации учёта машин у автодилера. Автодилер должен иметь возможность:

  - учесть приход и отпуск машин;
  - узнать общее количество машин определенного цвета и лошадиных сил в данный момент времени.

Внешний интерфейс приложения представлен в виде HTTP API (REST, если хочется).

[Полное описание задания](https://docs.google.com/document/d/1cBMC7xx2fRbxjFqF1uyRYIxPXWX1FvSyHmTRGPWysA0/edit#)

<a name="2"><h2>TODO лист</h2></a>
 - [x] Написано на Java;
 - [x] Standalone;
 - [x] Headless;
 - [x] Приложение основано на Spring(Boot) Framework;
 - [x] Для версионирования схемы базы данных используется Flyway;
 - [x] База данных поднимается рядом с приложением в докер-контейнере.

<a name="3"><h2>Используемые технологии</h2></a>
 - Intellij Idea Ultimate;
 - Postgresql;
 - Apache Tomcat;
 - Docker;
 - Spring;
 - Maven;
 - Flyway.

<a name="4"><h2>Демонстрация работы</h2></a>
[Видео](https://www.youtube.com/watch?v=rYuo6ue12jU)
<a name="5"><h2>Примечание</h2></a>
Для сборки проекта в Intellij Idea нужно указать в качестве конфигурации Apache Tomcat.

Схема БД
```SQL
TABLE cars
(
    id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    color varchar(20),
    horsepower int,
    quantity int
);
```
В задании в разделе `Примеры запросов` во втором примере запросе
```HTTP
/api/cars?color=black&operation=lessThan?horsepower=200
```
ошибка после `lessThan` должен стоять `&`, а не `?`.




