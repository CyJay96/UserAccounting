## Тестовое задание №1 (для вакансии стажер-разработчик Java)

### **Инструкции по выполнении тестового задания:**

Готовое тестовое задание разместить в своем репозитории на GitHub.
Ссылку на готовое тестовое задание прислать рекрутеру.
Выполнить задачу на Java без использования сторонних библиотек и фреймворков. Только Java Core (версия джавы 8+).

### **Что необходимо сделать:**
Примитивное консольное приложение для создания/редактирования/просмотра пользователей и сохранения изменений в файл.

### **Что будет оцениваться:**

Чистота кода.
Следование практикам ООП и SOLID.
Возможность расширения уже написанного кода (потенциальное добавление новых валидаторов, вариантов ввода)
Наличие шаблонов проектирование и уместность их использования.

### **Приложение должно предоставлять возможность:**
Создать пользователя со следующими параметрами: имя, фамилия, email, роли, мобильные телефоны и сохранить его в файл.
Кол-во телефонов от 1 до 3-х.
Выбрать пользователю роли: USER (ур1), CUSTOMER(ур1), ADMIN (ур2), PROVIDER(ур2), SUPER_ADMIN (ур3)
Одновременно пользователь может иметь по 1 роли с каждого уровня, например: USER+ADMIN, CUSTOMER+PROVIDER, USER+PROVIDER, но не USER+CUSTOMER, ADMIN+PROVIDER.
Если у пользователя указана роль SUPER_ADMIN - другие роли выбирать запрещено.
При попытке ввести некорректное кол-во или сочетание записей выводить сообщение о том, что кол-во неверно и дать повторить попытку ввода.
Валидировать email и телефоны:
телефоны должны быть в виде 375 *****, к примеру, | 37500 1234567 |.
email в виде xxxxx@xxxxx.x, к примеру, | any@email.com |. Т.е. достаточно проверки на ‘@’ и точку.
Редактировать уже существующего пользователя.
Учесть все валидации и ошибки из предыдущего пункта.
Удалить пользователя.
Получать информацию о пользователе, его ролях и телефонах (вывод на консоль).
Получить и вывести всех пользователей.
Консольный ввод.
