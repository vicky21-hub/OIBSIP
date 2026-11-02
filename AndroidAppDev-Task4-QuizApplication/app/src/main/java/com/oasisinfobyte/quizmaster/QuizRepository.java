package com.oasisinfobyte.quizmaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Local question bank. Add or edit questions here without changing quiz logic. */
public final class QuizRepository {
    private QuizRepository() {}

    public static List<QuizQuestion> getQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();

        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Mercury"}, 1,
                "Mars appears reddish because iron minerals in its soil have oxidised."));
        questions.add(new QuizQuestion("What is the capital of Australia?",
                new String[]{"Sydney", "Melbourne", "Canberra", "Perth"}, 2,
                "Canberra is the capital city of Australia."));
        questions.add(new QuizQuestion("Which gas do plants mainly absorb during photosynthesis?",
                new String[]{"Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen"}, 2,
                "Plants absorb carbon dioxide and use light energy to make sugars."));
        questions.add(new QuizQuestion("Who is known as the Father of the Indian Constitution?",
                new String[]{"Mahatma Gandhi", "Dr. B. R. Ambedkar", "Jawaharlal Nehru", "Sardar Patel"}, 1,
                "Dr. B. R. Ambedkar chaired the Constitution's Drafting Committee."));
        questions.add(new QuizQuestion("What is the largest ocean on Earth?",
                new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3,
                "The Pacific Ocean is the largest and deepest ocean."));
        questions.add(new QuizQuestion("Which data structure follows FIFO order?",
                new String[]{"Stack", "Queue", "Tree", "Graph"}, 1,
                "A queue follows First In, First Out (FIFO)."));
        questions.add(new QuizQuestion("How many sides does a hexagon have?",
                new String[]{"Five", "Six", "Seven", "Eight"}, 1,
                "A hexagon is a polygon with six sides."));
        questions.add(new QuizQuestion("Which is the closest star to Earth?",
                new String[]{"Sirius", "The Sun", "Proxima Centauri", "Polaris"}, 1,
                "The Sun is Earth's nearest star."));
        questions.add(new QuizQuestion("Which language is primarily used to develop Android apps in this project?",
                new String[]{"Java", "PHP", "Ruby", "C"}, 0,
                "This internship task specifies Java and XML for Android development."));
        questions.add(new QuizQuestion("What does CPU stand for?",
                new String[]{"Central Program Utility", "Computer Processing Unit", "Central Processing Unit", "Core Power Unit"}, 2,
                "CPU stands for Central Processing Unit."));
        questions.add(new QuizQuestion("Which instrument is used to measure temperature?",
                new String[]{"Barometer", "Thermometer", "Hygrometer", "Ammeter"}, 1,
                "A thermometer measures temperature."));
        questions.add(new QuizQuestion("Which number is a prime number?",
                new String[]{"21", "27", "29", "33"}, 2,
                "29 has exactly two positive factors: 1 and 29."));
        questions.add(new QuizQuestion("Which organisation developed the Android operating system initially?",
                new String[]{"Nokia", "Android Inc.", "IBM", "Mozilla"}, 1,
                "Android Inc. developed Android before Google acquired the company in 2005."));
        questions.add(new QuizQuestion("What is the chemical symbol for gold?",
                new String[]{"Ag", "Gd", "Au", "Go"}, 2,
                "Gold's chemical symbol is Au, from the Latin word aurum."));
        questions.add(new QuizQuestion("Which one is a renewable energy source?",
                new String[]{"Coal", "Petroleum", "Natural gas", "Solar energy"}, 3,
                "Solar energy is naturally replenished by sunlight."));

        Collections.shuffle(questions);
        return questions;
    }
}
