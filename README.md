Проект для домашней работы Т1ДЕБЮТ.\n
AuditAspect и WeylandWatchingYou-реализация аннотации @WeylandWatchingYou
kafkaConfig-конфигурационный файл Kafka
transferObject-тело объекта, который передается и его форма (description/priority/author/timestamp) вместе с реализацией ограничений через аннотации
comController- контроллер, обрабатывающий POST-запросы
comService-обрабатывает команды в зависимости от priority-если CRITICAL, то моментально, если COMMON-отправляет в очередь.
kafkaQueue-здесь реализована очередь и мгновенное выполнение процесса, а также метрики, которые можно вызвать с помощью GET-запроса
exception-перехватчик всех ошибок.
