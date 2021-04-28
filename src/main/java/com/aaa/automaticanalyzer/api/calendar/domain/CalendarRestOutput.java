package com.aaa.automaticanalyzer.api.calendar.domain;

import com.aaa.automaticanalyzer.model.Disease;
import lombok.Data;

@Data
public class CalendarRestOutput {
    private String disease;
    private Long date;
    private String location;
}
