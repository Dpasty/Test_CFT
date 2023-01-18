# Инструкция по запуску
* Версия Java - 11
* Использовалась система сборки Maven версии 4.0.0
* Использовались дополнительные библиотеки
  * JUnit - для тестирования кода
  ```xml
  <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <scope>test</scope>
  </dependency>
  ```
  * Commons IO - для рекурсивной очистки папки с временными файлами
  ```xml
  <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.11.0</version>
  </dependency>
  ```
Для запуска программы необходима установленная система сборки Maven.
В папке проекта нужно ввести команду `mvn clean package`, после чего в папке target сформируется необходимый jar пакет, который будет называться TestTaskCFT-1.0-SNAPSHOT-jar-with-dependencies.jar. Запускаем его с помощью команды `java -jar TestTaskCFT-1.0-SNAPSHOT-jar-with-dependencies.jar`, не забывая указать необходимые аргументы.
Тесты можно прогнать командой `mvn clean test`.
