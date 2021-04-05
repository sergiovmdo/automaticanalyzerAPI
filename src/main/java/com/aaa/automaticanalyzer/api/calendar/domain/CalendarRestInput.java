package com.aaa.automaticanalyzer.api.calendar.domain;

import com.aaa.automaticanalyzer.model.Disease;
import lombok.Data;

@Data
public class CalendarRestInput {
    private String disease;
    private String date;
    private String time;
    private String location;
}
