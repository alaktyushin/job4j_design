package ru.job4j.ood.srp;

import ru.job4j.tdd.Account;
import ru.job4j.tdd.Session;
import ru.job4j.tdd.Ticket;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);
}

/*
* Нарушение принципа SRP в методе buy() -
* необходимо вынести процесс покупки за рамки данного интерфейса.
* Р.Мартин, "Чистая архитектура": Модуль должен отвечать за одного и только одного актора.
* Метод buy(), очевидно, относится к отдельному бизнес-процессу, отличному от планирования
* расписания сеансов.
* */