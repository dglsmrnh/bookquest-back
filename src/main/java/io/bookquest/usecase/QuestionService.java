package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class QuestionService {


    @Autowired
    private DatabaseRepository databaseRepository;

    public List<BookQuizEntrypoint> getQuestions(String idBook) {
        var questions = databaseRepository.getBookQuestions(idBook);
        var answers = databaseRepository.getAnswerQuestions(questions);

        return questions.stream().map(question -> {
            AtomicReference<String> correctAnswer = new AtomicReference<>();
            List<String> questionAnswers = answers.stream()
                    .filter(answer -> answer.question().equalsIgnoreCase(question.id()))
                    .map(answer -> {
                        if (answer.isCorrect())
                            correctAnswer.set(answer.description());
                        return answer.description();
                    }).toList();
            return new BookQuizEntrypoint(question.question(), questionAnswers, correctAnswer.get());
        }).toList();
    }
}
