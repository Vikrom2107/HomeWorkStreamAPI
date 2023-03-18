import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // Количество несовершеннолетних
        System.out.println("Количество несовершеннолетних: ");
        long under18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(under18);
        System.out.println("************************");
        // Количество призывников
        System.out.println("Фамилии призывников: ");
        List <String> recruitFamilies = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        for (int i = 0; i < 10; i++ ) {
            System.out.println(recruitFamilies.get(i));
        }
        System.out.println("Общее количество: " + recruitFamilies.size());
        System.out.println("**************************");
        // Работоспособные с высшим образованием
        System.out.println("Потенциально работоспособные с высшим образованием: ");
        List <Person> potentialEmployees = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> {
                    if (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() < 65) {
                        return true;
                    } else if (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() < 60)
                        return true;

                    return false;
                })
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        for (int i = 0; i < 10; i++ ) {
            System.out.println(potentialEmployees.get(i));
        }
        System.out.println("Общее количество: " + potentialEmployees.size());
    }
}