package com.example.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

   int totalMarks=100;


    public void insertResultModel(ResultModel ResultModel) {
        calculatePassStatus(ResultModel);
         resultRepository.save(ResultModel);
    }

    public List<ResultModel> getAllResultModels() {
        return resultRepository.findAll();
    }

    public int getRankByName(String name) {
        ResultModel resultModel = resultRepository.findByName(name);
        if (resultModel == null) {
            throw new IllegalArgumentException("SAT Result not found for name: " + name);
        }

        List<ResultModel> allResults = resultRepository.findAll();

        int rank = 1;
        for (ResultModel result : allResults) {
            if (resultModel.getSatScore() < result.getSatScore()) {
                rank++;
            }
        }

        return rank;
    }


    public void updateSATScore(String name, int satScore) throws IllegalArgumentException{
        ResultModel ResultModel = resultRepository.findByName(name);
        if (ResultModel == null) {
            throw new IllegalArgumentException("Result not found for name: " + name);
        }

        ResultModel.setSatScore(satScore);
        calculatePassStatus(ResultModel);
         resultRepository.save(ResultModel);
    }

    public void deleteResultModelByName(String name) throws IllegalArgumentException {
        ResultModel ResultModel = resultRepository.findByName(name);
        if (ResultModel == null) {
            throw new IllegalArgumentException("Result not found for name: " + name);
        }

        resultRepository.delete(ResultModel);
    }

    private void calculatePassStatus(ResultModel ResultModel) {
        double passThreshold = (int) (0.3 * totalMarks);
        if(ResultModel.getSatScore() > passThreshold){
            ResultModel.setPassed(true);
        }
        else{
            ResultModel.setPassed(false);
        }

    }
}

