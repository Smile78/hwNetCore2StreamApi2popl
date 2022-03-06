package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

//        for (int i = 0; i < 10_000_000; i++) {
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> streamPers1 = persons.stream();

        System.out.println(
                "\nКоличество несовершеннолетних (т.е. людей младше 18 лет) в коллекции : " +
                        (int) streamPers1
                                .filter(x -> x.getAge() < 18)                                        // рандом примерно такойже
                                .count() +
                        " человек");


//      Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        System.out.println("\nСписок фамилий призывников от 18 до 27 :");

        Stream<Person> streamPers2 = persons.stream();

        List<String> listFmly =
                streamPers2
                        .filter(x -> x.getSex() == Sex.MAN)
                        .filter(x -> x.getAge() > 18)
                        .filter(x -> x.getAge() < 27)
                        .map(Person::getName)
                        .collect(Collectors.toList());

        listFmly.forEach(System.out::println);

//  Получить отсортированный !!
//      по фамилии список потенциально работоспособных людей с высшим образованием в выборке
//  (т.е. людей
//      с высшим образованием
//      от 18 до 60 лет для женщин
//          и до 65 лет для мужчин).

        System.out.println("\nCписок потенциально работоспособных людей:");

        Stream<Person> streamPers3 = persons.stream();

        List<String> listPipls =
                streamPers3
                        .filter(x -> x.getEducation() == Education.HIGHER)
                        .filter(x -> x.getAge() > 18)
                        .filter(x -> (x.getSex() == Sex.WOMAN) && (x.getAge()) < 60
                                  || (x.getSex() == Sex.MAN) && (x.getAge()) < 65)
                        .sorted(Comparator.comparing(Person::getFamily))
                        .map(fio -> fio.getFamily() + " " + fio.getName())
                        .collect(Collectors.toList());

        listPipls.forEach(System.out::println);

    }
}
