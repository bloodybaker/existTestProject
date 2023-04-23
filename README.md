# Тестове завдання exist.ua
Проект створений як тестове завдання з використанням такого стеку технологій:
* Java 10
* Selenium
* Junit5
* Allure report
* WebDriverManager

Для запуску тестів потрібно:
1. Склонувати проект   
     ```git clone https://github.com/bloodybaker/existTestProject.git ```
2. Відкрити через IDE (бажано Intellij IDEA)
3. При інстальованому maven можна використати команди:
    1. ```mvn clean test -Dbrowser=назва браузера``` для запуску тестів
       1. Наявні таки види: **Chrome, Edge, Opera, Safari**.  
          Приклад ```mvn clean test -Dbrowser=chrome```
       2. Якщо не вказати параметр то викличеться браузер по замовченню **Edge**
    2. Після виконаних тестів (незважаючи на результат прогону) виконати команду ```mvn allure:serve```
4. У відкритому вікні браузера можна переглядати результати запусків

- [x] Додані тести
- [x] Інтегрований репорт Allure
- [x] Розмежено фунціонал
- [ ] Додавання environment.xml для відображення в Allure репорті
- [ ] Накладання ліцензії

У випадку чогось звертатися за додатковими запитаннями:   
<a href="https://t.me/opcoder"><img src="https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white"/></a>
<a href="mailto:emersonpess011108@gmail.com?"><img src="https://img.shields.io/badge/gmail-%23DD0031.svg?&style=for-the-badge&logo=gmail&logoColor=white"/></a>


