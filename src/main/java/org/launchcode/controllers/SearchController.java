package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results


    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType,
                          @RequestParam String searchTerm) {

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("activeButton",searchType);// Bonus mission: storing the value of search field
        List<HashMap<String, String>> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchSize", jobs.size());
            return "search";
        }
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchSize", jobs.size());
        return "search";
    }


}
