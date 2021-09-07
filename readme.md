### Запуск сборки SUT
1. Разворачиваем SUT в Docker командой `docker-compose -f mysql.yml up` в терминале, если нам надо протестировать 
систему с СУБД MySQL и `docker-compose -f postgresql.yml up` если требуемая СУБД - PostgreSQL. Либо через интерфейс IDE 
в соответствующих файлах
2. Дождаться развёртки контейнеров
3. Запуск тестов `.\gradlew test`
