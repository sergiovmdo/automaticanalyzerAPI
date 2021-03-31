package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.analysis.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<Analysis, String> {
}
