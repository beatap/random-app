package pl.beata.randomrestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.text.ParseException;

@RestController
@RequestMapping("/randomclient")
public class RandomNumberController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(path = "/{max}")
    public ResponseEntity<Number> getNumber(@PathVariable Integer max) {
        Number number = generateNumber(max);

        if(number != null) {
            return new ResponseEntity<>(number, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Number generateNumber(Integer max) {
        Number number = 0;
        String result = restTemplate.getForObject(
                "https://www.random.org/integers/?num=1&min=1&max={max}&col=1&base=10&format=plain&rnd=new",
                String.class,
                max);

        try {
            number = NumberFormat.getInstance().parse(result);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return number;

    }
}
