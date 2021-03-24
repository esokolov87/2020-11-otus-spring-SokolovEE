package ru.sokolovee.spring15.bookmaker;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.sokolovee.spring15.domain.Bet;
import ru.sokolovee.spring15.domain.Check;

@Service
public class BookmakerService {
    public Check set(Bet bet) throws Exception {
        System.out.println("Ставка на спорт " + bet.getName()+ " сумма " + bet.getValue());
        Thread.sleep(3000);
        System.out.println("Спорт " + bet.getName() + " завершен");
        Boolean isWin = RandomUtils.nextBoolean();
        Long value = isWin ? bet.getValue() * bet.getRate() : 0;
        System.out.println("Результат " + (isWin ? "Победа" : "Поражение") + " сумма " + value);
        return new Check(bet.getName(), isWin, value);
    }
}
