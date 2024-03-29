# SmartCalc

![calcScreen0.jpg](images%2FcalcScreen0.jpg)

Задача - сделать веб калькулятор со следующим функционалом:

- Вычисление должно производится после полного ввода вычисляемого выражения и нажатия на символ `=`
- Вычисление произвольных скобочных арифметических выражений в инфиксной нотации с подстановкой значения переменной _x_ в виде числа
- Построение графика функции, заданной с помощью выражения в инфиксной нотации с переменной _x_  (с координатными осями, отметкой используемого масштаба и сеткой с адаптивным шагом)
    - Не требуется предоставлять пользователю возможность менять масштаб
- Область определения и область значения функций ограничиваются по крайней мере числами от -1000000 до 1000000
    - Для построения графиков функции необходимо дополнительно указывать отображаемые область определения и область значения
- Проверяемая точность дробной части - минимум 7 знаков после запятой
- У пользователя должна быть возможность ввода до 255 символов


- Скобочные арифметические выражения в инфиксной нотации должны поддерживать следующие арифметические операции и математические функции:

   - **Арифметические операторы**:

      | Название оператора | Инфиксная нотация <br /> (Классическая) | Префиксная нотация <br /> (Польская нотация) |  Постфиксная нотация <br /> (Обратная польская нотация) |
      | ------ | ------ | ------ | ------ |
      | Скобки | (a + b) | (+ a b) | a b + |
      | Сложение | a + b | + a b | a b + |
      | Вычитание | a - b | - a b | a b - |
      | Умножение | a * b | * a b | a b * |
      | Деление | a / b | / a b | a b \ |
      | Возведение в степень | a ^ b | ^ a b | a b ^ |
      | Остаток от деления | a mod b | mod a b | a b mod |
      | Унарный плюс | +a | +a | a+ |
      | Унарный минус | -a | -a | a- |

      >Оператор умножения содержит обязательный знак `*`. Обработка выражения с опущенным знаком `*` не производится

    - **Функции**:

      | Описание функции | Функция |   
      | ---------------- | ------- |  
      | Вычисляет косинус | cos(x) |   
      | Вычисляет синус | sin(x) |  
      | Вычисляет тангенс | tan(x) |  
      | Вычисляет арккосинус | acos(x) | 
      | Вычисляет арксинус | asin(x) | 
      | Вычисляет арктангенс | atan(x) |
      | Вычисляет квадратный корень | sqrt(x) |
      | Вычисляет натуральный логарифм | ln(x) | 
      | Вычисляет десятичный логарифм | log(x) |


Предусмотреть специальный режим "кредитный калькулятор":
- Вход: общая сумма кредита, срок, процентная ставка, тип (аннуитетный, дифференцированный)
- Выход: ежемесячный платеж, переплата по кредиту, общая выплата


Предусмотреть специальный режим "калькулятор доходности вкладов":
- Вход: сумма вклада, срок размещения, процентная ставка, налоговая ставка, периодичность выплат, капитализация процентов, список пополнений, список частичных снятий
- Выход: начисленные проценты, сумма налога, сумма на вкладе к концу срока


Добавить в приложение настройки:
- Добавить считывание настроек из файла конфигурации при запуске программы
- Добавить описание редактируемых параметров в справку

Добавить в приложение логирование:
- В логах хранить историю операций
- Логи сохранять в папку logs, по одному файлу на период ротации
- Должна быть возможность настройки периода ротации логов (час/день/месяц)
- Файлы должны быть названы в соответствии со следующим шаблоном: `logs_dd-MM-yy-hh-mm-ss` (указывается время создания файла)

![calcScreen1.jpg](images%2FcalcScreen1.jpg)

График функции строится с помощью библиотеки Chart.js https://github.com/chartjs/Chart.js
