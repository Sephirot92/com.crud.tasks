package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldReturnTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoard = new TrelloBoardDto("id", "name", new ArrayList<>());
        List<TrelloBoardDto> someTrelloList = new ArrayList<>();
        someTrelloList.add(trelloBoard);
        //When
        when(trelloService.fetchTrelloBoards()).thenReturn(someTrelloList);
        int numberOfBoards = trelloService.fetchTrelloBoards().size();

        //Then
        Assert.assertEquals(1, numberOfBoards);
    }
}
