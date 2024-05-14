public class Account {

    private final String name;

    public Account(String name) {
        this.name = name;
    }

//    На банковской карте можно напечатать данные владельца.
//    Чтобы это сделать, принтеру передают строку с именем и фамилией — например, «Тимоти Шаламе».
//    Её получится напечатать, если соблюдены требования:
//    в строке не меньше 3 и не больше 19 символов,
//    в строке есть только один пробел,
//    пробел стоит не в начале и не в конце строки.
//    Твоя программа должна проверять, можно ли напечатать строку на карте.
//    Например, «Тимоти Шаламе» — можно, а «ТимотейШевроле» — нет.

    public boolean checkNameToEmboss() {
        /*
             Этот метод должен проверять, что сохранённая через конструктор строка соответствует требованиям.
             Если строка удовлетворяет условиям, метод возвращает true, иначе — false.
         */
        return checkLength() && hasOneSpace() && hasNotSpacesInStartAndEnd();
    }

    private boolean checkLength() {
        return name.length() >= 3 && name.length() <= 19;
    }

    private boolean hasOneSpace() {
        final var regex = "[a-zA-Z]* [a-zA-Z]*";
        return name.matches(regex);
    }

    private boolean hasNotSpacesInStartAndEnd() {
        return name.trim().equals(name);
    }

}