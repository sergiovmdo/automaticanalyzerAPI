package com.aaa.automaticanalyzer.api.analysis.rest;

import com.aaa.automaticanalyzer.api.analysis.business.AnalysisService;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.user.business.UserService;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analysis")
@AllArgsConstructor
@AuthAwareRestController
public class AnalysisController {
    private final AnalysisService analysisService;

    @PostMapping
    public ResponseEntity<Void> addBloodAnalysis(@RequestBody final AnalysisRestInput analysis, final User user){
        return ResponseEntity.ok(null);
    }
}
