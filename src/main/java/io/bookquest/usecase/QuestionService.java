package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.BookQuizDataTransfer;
import io.bookquest.entrypoint.v1.mapper.QuestionMapper;
import io.bookquest.persistence.entity.BookQuestions;
import io.bookquest.persistence.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<BookQuizDataTransfer> getQuestions(Long idBook) {
        List<BookQuestions> bookQuestions = questionRepository.findByBookId(idBook);
        return QuestionMapper.toDtoList(bookQuestions);
    }
}
