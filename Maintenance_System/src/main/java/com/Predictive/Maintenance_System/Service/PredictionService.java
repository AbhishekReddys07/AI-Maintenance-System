package com.Predictive.Maintenance_System.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Predictive.Maintenance_System.Models.Prediction;
import com.Predictive.Maintenance_System.Repository.PredictionRepository;

import java.util.List;

@Service
public class PredictionService {

    @Autowired
    private PredictionRepository predictionRepository;

    public Prediction savePrediction(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll();
    }

    public Prediction getPredictionById(Long id) {
        return predictionRepository.findById(id).orElse(null);
    }

   
}
