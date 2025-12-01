package com.diro.ift2255.service;

import com.diro.ift2255.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComparisonService {

    private final CourseService courseService;

    public ComparisonService(CourseService courseService) {
        this.courseService = courseService;
    }

    public ComparisonResult compareCourses(List<String> courseIds) {
        List<Course> foundCourses = new ArrayList<>();
        int totalWorkload = 0;

        for (String id : courseIds) {
            Optional<Course> courseOpt = courseService.getCourseById(id);
            if (courseOpt.isPresent()) {
                Course c = courseOpt.get();
                foundCourses.add(c);

                int credits = c.getCredits() > 0 ? c.getCredits() : 3; // valeur par défaut
                totalWorkload += credits * 3;
            }
        }

        return new ComparisonResult(foundCourses, totalWorkload);
    }

    public static class ComparisonResult {
        private List<Course> courses;
        private int totalWorkload;

        public ComparisonResult(List<Course> courses, int totalWorkload) {
            this.courses = courses;
            this.totalWorkload = totalWorkload;
        }

        public List<Course> getCourses() {
            return courses;
        }

        public int getTotalWorkload() {
            return totalWorkload;
        }
    }
}

