package pl.beata.randomapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/randomadd")
public class AddNumberRandomController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<Number> addNumber() {
        Integer number = 0;

        try {
            number = getNumberFromDB().intValue() +  getNumberFromRest().intValue();
            return new ResponseEntity<>(number, HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(number, HttpStatus.NO_CONTENT);
    }

    private Number getNumberFromDB() {
        return restTemplate.getForObject(
                "http://localhost:8081/randomdb",
                Number.class);
    }

    private Number getNumberFromRest() {
        return restTemplate.getForObject(
                "http://localhost:8082/randomclient/100",
                Number.class);
    }
}
