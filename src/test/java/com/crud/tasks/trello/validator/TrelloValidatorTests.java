package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTests {

    @InjectMocks
    TrelloValidator trelloValidator;

    @Test
    public void validateCards() {
        //Given
        Logger trelloValidatorLogger = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos", "id");

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        trelloValidatorLogger.addAppender(listAppender);

        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        List<ILoggingEvent> logList = listAppender.list;
        Assert.assertEquals("Application is running perfect.", logList.get(0).getMessage());
        listAppender.stop();
    }

    @Test
    public void validateTrelloBoard() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("id", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        //When
        trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
        Assert.assertNotEquals(0, trelloBoardList.size());
    }
}
