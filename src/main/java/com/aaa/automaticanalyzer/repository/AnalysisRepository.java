package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.analysis.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface AnalysisRepository extends JpaRepository<Analysis, String> {
    @Query(value = "SELECT * from Analysis WHERE user_dni = :uid" , nativeQuery = true)
    Stream<Analysis> getAnalysis(final String uid);
}
