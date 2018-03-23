package com.svlugovoy.springrestbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@ResponseBody
@RequestMapping("/api")
public class DateTimeController {

    @RequestMapping("/time")
    @ResponseBody
    public String currentTime() {
        return "Current Time: " + LocalTime.now().toString();
    }

    @RequestMapping("/date")
    @ResponseBody
    public String currentDate() {
        return "Current Date: " + LocalDate.now().toString();
    }

}
