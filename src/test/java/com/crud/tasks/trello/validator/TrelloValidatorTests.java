package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTests {

    @InjectMocks
    TrelloValidator trelloValidator;

    @Test
    public void validateCards() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos", "id");

        //When
        trelloValidator.validateCard(trelloCard);
        //Then

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
