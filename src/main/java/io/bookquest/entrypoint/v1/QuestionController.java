package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.AnswerEntrypoint;
import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import io.bookquest.usecase.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/books/{idBook}/questions")
    public List<BookQuizEntrypoint> getQuestions(@PathVariable("idBook") String idBook) {
        return questionService.getQuestions(idBook, true);
    }

    @PatchMapping("/users/{username}/books/{idBook}/questions")
    public List<BookQuizEntrypoint> answerQuestions(@PathVariable("username") String username,
                                                @PathVariable("idBook") String idBook,
                                                @RequestBody AnswerEntrypoint answers) {

        return questionService.validateAnswers(idBook, username, answers.getAnswers());
    }
}
