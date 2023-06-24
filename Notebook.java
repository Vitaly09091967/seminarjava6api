/**Java: знакомство и как пользоваться базовым API (семинары)
Урок 6. Хранение и обработка данных ч3: множество коллекций Set
Подумать над структурой класса Ноутбук для магазина техники - выделить 
поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий 
фильтрации и выведет ноутбуки, отвечающие фильтру.
NoteBook notebook1 = new NoteBook
NoteBook notebook2 = new NoteBook
NoteBook notebook3 = new NoteBook
NoteBook notebook4 = new NoteBook
NoteBook notebook5 = new NoteBook

Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет

Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.

Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

Класс сделать в отдельном файле

приветствие
Выбор параметра
выбор конкретнее
вывод подходящих*/


import java.util.*;

public class Notebook {
    private String model;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;

    // Конструктор класса
    public Notebook(String model, int ram, int storage, String operatingSystem, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    // Геттеры для доступа к полям класса

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    // Метод для фильтрации ноутбуков
    public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filters) {
        Set<Notebook> filteredNotebooks = new HashSet<>();

        for (Notebook notebook : notebooks) {
            boolean match = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String filterKey = entry.getKey();
                Object filterValue = entry.getValue();

                if (filterKey.equals("model") && !notebook.getModel().equalsIgnoreCase((String) filterValue)) {
                    match = false;
                    break;
                } else if (filterKey.equals("ram") && notebook.getRam() < (int) filterValue) {
                    match = false;
                    break;
                } else if (filterKey.equals("storage") && notebook.getStorage() < (int) filterValue) {
                    match = false;
                    break;
                } else if (filterKey.equals("operatingSystem") && !notebook.getOperatingSystem().equalsIgnoreCase((String) filterValue)) {
                    match = false;
                    break;
                } else if (filterKey.equals("color") && !notebook.getColor().equalsIgnoreCase((String) filterValue)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }

    public static void main(String[] args) {
        // Создание множества ноутбуков
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Model 1", 8, 500, "Windows", "Black"));
        notebooks.add(new Notebook("Model 2", 16, 1000, "MacOS", "Silver"));
        notebooks.add(new Notebook("Model 3", 8, 1000, "Windows", "White"));
        notebooks.add(new Notebook("Model 4", 16, 2000, "Linux", "Black"));
        notebooks.add(new Notebook("Model 5", 16, 500, "Windows", "Silver"));

        // Запрос к пользователю для фильтрации
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифру, соответствующую необходимому критерию фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        int filterType = scanner.nextInt();

        Map<String, Object> filters = new HashMap<>();
        switch (filterType) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int minRam = scanner.nextInt();
                filters.put("ram", minRam);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int minStorage = scanner.nextInt();
                filters.put("storage", minStorage);
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                String operatingSystem = scanner.next();
                filters.put("operatingSystem", operatingSystem);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String color = scanner.next();
                filters.put("color", color);
                break;
            default:
                System.out.println("Некорректный ввод.");
                return;
        }

        // Фильтрация и вывод подходящих ноутбуков
        Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);
        if (filteredNotebooks.isEmpty()) {
            System.out.println("Ноутбуки, удовлетворяющие условиям фильтра, не найдены.");
        } else {
            System.out.println("Подходящие ноутбуки:");
            for (Notebook notebook : filteredNotebooks) {
                System.out.println(notebook.getModel());
            }
        }
    }
}
