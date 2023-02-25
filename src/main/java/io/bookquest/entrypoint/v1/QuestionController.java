package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.BookQuizDataTransfer;
import io.bookquest.usecase.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{idBook}/questions")
    public List<BookQuizDataTransfer> getQuestions(@PathVariable("idBook") Long idBook) {
        return questionService.getQuestions(idBook);
    }
}
