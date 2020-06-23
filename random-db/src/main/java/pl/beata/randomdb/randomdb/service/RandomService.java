package pl.beata.randomdb.randomdb.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.beata.randomdb.randomdb.model.RandomNumber;
import pl.beata.randomdb.randomdb.repository.RandomRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class RandomService {

    private RandomRepository randomRepository;
    public static final int start = 1;
    public static final int end = 30;

    public RandomService(RandomRepository randomRepository) {
        this.randomRepository = randomRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        IntStream.rangeClosed(start, end)
                .forEach(i -> randomRepository.save(new RandomNumber(Long.valueOf(i), Math.random() * 100)));
    }

    public boolean saveRandom(RandomNumber randomNumber) {
        RandomNumber number = Optional.ofNullable(randomRepository.save(randomNumber)).orElseGet(null);

        if(number != null) {
            return true;
        }
        return false;
    }

    public Number getNumber(Long id) {
        return Optional.ofNullable(randomRepository.getOne(id).getNumber()).orElseGet(() -> Integer.MIN_VALUE );
    }
}
