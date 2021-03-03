package it.academy.rest;

import it.academy.model.Count;
import it.academy.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
public class VisitorCountServiceMy {

//    @Autowired
//    private CountService countService;
//
//    @GetMapping("/visitor_count")
//    public ResponseEntity<Integer> readCount(){
//        Integer read = countService.read();
//        return new ResponseEntity<>(read, HttpStatus.OK);
//    }
//
//    @PutMapping ("/visitor_count")
//    public ResponseEntity<Integer> updateVisitorCount(){
//        Integer read = countService.update();
//        return new ResponseEntity<>(read, HttpStatus.OK);
//    }
}
