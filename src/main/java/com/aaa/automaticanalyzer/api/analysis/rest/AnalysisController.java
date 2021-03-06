package com.aaa.automaticanalyzer.api.analysis.rest;

import com.aaa.automaticanalyzer.api.analysis.business.AnalysisService;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestInput;
import com.aaa.automaticanalyzer.api.analysis.domain.AnalysisRestOutput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/analysis")
@AllArgsConstructor
@AuthAwareRestController
public class AnalysisController {
    private final AnalysisService analysisService;

    @PostMapping
    public void addBloodAnalysis(@RequestBody final AnalysisRestInput analysis, final User user){
        analysisService.addBloodAnalysis(analysis, user);
    }

    @GetMapping
    public List<AnalysisRestOutput> getAnalysis(final User user){
        return analysisService.getAnalysis(user);
    }
}
