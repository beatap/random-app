package pl.beata.randomdb.randomdb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.beata.randomdb.randomdb.model.RandomNumber;
import pl.beata.randomdb.randomdb.service.RandomService;

@RestController
@RequestMapping("/randomdb")
public class RandomController {

    RandomService randomService;

    public RandomController(RandomService randomService) {
        this.randomService = randomService;
    }


    @GetMapping
    public ResponseEntity<Number> getNumber() {
        return generateNumber((long)(Math.random() * RandomService.end));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Number> getNumberById(@PathVariable Long id) {
        return generateNumber(id);
    }

    @PostMapping
    public ResponseEntity<String> addNumber(@RequestBody RandomNumber randomNumber) {
        boolean isNew = randomService.saveRandom(randomNumber);

        if(isNew) {
            return new ResponseEntity<>("Success add RandomNumber", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Not create. Such a error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Number> generateNumber(Long id) {
        Number number = randomService.getNumber(id);

        if(number != null) {
            return new ResponseEntity<>(number, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);    }
}
