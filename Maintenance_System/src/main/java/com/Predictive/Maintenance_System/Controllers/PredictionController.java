package com.Predictive.Maintenance_System.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Predictive.Maintenance_System.Models.Prediction;
import com.Predictive.Maintenance_System.Service.PredictionService;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @PostMapping
    public ResponseEntity<Prediction> createPrediction(@RequestBody Prediction prediction) {
        return ResponseEntity.ok(predictionService.savePrediction(prediction));
    }

    @GetMapping
    public List<Prediction> getAllPredictions() {
        return predictionService.getAllPredictions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prediction> getPredictionById(@PathVariable Long id) {
        return ResponseEntity.ok(predictionService.getPredictionById(id));
    }
}
