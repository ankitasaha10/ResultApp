package com.example.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ResultModels")
public class ResultController {

    @Autowired
    private  ResultService resultService;

   
   

    @PostMapping
    public ResponseEntity<String> insertResultModel(@RequestBody ResultModel ResultModel) {
         resultService.insertResultModel(ResultModel);
         String response= "Details inserted successfully";
         return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public List<ResultModel> getAllResultModels() {
        return resultService.getAllResultModels();
    }

    @GetMapping("/{name}/rank")
    public int getRankByName(@PathVariable String name) {
        return resultService.getRankByName(name);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateSATScore(@RequestParam String name, @RequestParam int satScore) {
        try {
               resultService.updateSATScore(name, satScore);
               String response= " Details updated";
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            String response= e.getMessage();
             return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteResultModelByName(@PathVariable String name) {
        try {
            resultService.deleteResultModelByName(name);
            String response= "Result is deleted";
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            String response= e.getMessage();
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }
}

