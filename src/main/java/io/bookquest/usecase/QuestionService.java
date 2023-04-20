package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import io.bookquest.entrypoint.v1.dto.ReadingEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class QuestionService {

    @Autowired
    private DatabaseRepository databaseRepository;

    public List<BookQuizEntrypoint> getQuestions(String idBook, boolean isSearchQuestion) {
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

            if (isSearchQuestion)
                return new BookQuizEntrypoint(question.question(), questionAnswers, null);
            return new BookQuizEntrypoint(question.question(), questionAnswers, correctAnswer.get());
        }).toList();
    }

    public List<BookQuizEntrypoint> validateAnswers(String idBook, String username, List<String> answers) {
        var questions = getQuestions(idBook, false);
        if (questions.size() != answers.size())
            throw new RuntimeException();

        var rightAnwers = new AtomicInteger();
        questions.forEach(quiz -> answers.forEach(answer -> {
            if (answer.equalsIgnoreCase(quiz.getCorrectAnswer())) {
                quiz.setRightOption(true);
                rightAnwers.set(rightAnwers.incrementAndGet());
            }
        }));

        var book = databaseRepository.getBook(idBook);
        book.ifPresent(bookPresent -> {
            isReadingOfBookValid(username, bookPresent);
            var user = databaseRepository.getUser(username);
            int xpByQuestion = bookPresent.getXp() / questions.size();
            int xpGained = xpByQuestion * rightAnwers.get();
            var userUpdated = UserMapper.updateXp(user.levelXp() + xpGained);
            var reading = new ReadingEntrypoint(true);
            databaseRepository.saveReading(username, bookPresent.getIsbn13(), reading, "Completed");
            databaseRepository.saveCreate(username, userUpdated);
        });

        return questions;
    }

    private void isReadingOfBookValid(String username, BookDataTransfer bookPresent) {
        databaseRepository.getReading(username, bookPresent.getIsbn13())
                .stream().findFirst()
                .ifPresentOrElse(reading -> {
                    if (!reading.status().equals("WaitingValidation")) {
                        throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
                    }
                }, () -> {
                    throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
                });
    }
}
