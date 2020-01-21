package com.crud.tasks.mapper;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;


    @Test
    public void shouldReturnBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("some_id", "some_name", new ArrayList<>());
        trelloBoardsDto.add(trelloBoardDto);
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardsDto);
        //When
        String id = trelloBoard.get(0).getId();
        String name = trelloBoard.get(0).getName();
        List<TrelloList> trelloBoardList = trelloBoard.get(0).getLists();
        //Then
        assertEquals("some_id", id);
        assertEquals("some_name", name);
        assertEquals(0, trelloBoardList.size());
    }

    @Test
    public void shouldReturnBoardsDto(){
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("some_id", "some_name", new ArrayList<>());
        trelloBoards.add(trelloBoard);
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.maptoBoardsDto(trelloBoards);
        //When
        String id = trelloBoardDto.get(0).getId();
        String name = trelloBoardDto.get(0).getName();
        List<TrelloListDto> arrayList = trelloBoardDto.get(0).getLists();
        //Then
        assertEquals("some_id", id);
        assertEquals("some_name", name);
        assertEquals(0, arrayList.size());
    }
    @Test
    public void shouldReturnTrelloList(){
        //Given
        TrelloListDto someTrelloListDto = new TrelloListDto("some_id", "some_name", false);
        List<TrelloListDto> someTrelloDtoList = new ArrayList<>();
        someTrelloDtoList.add(someTrelloListDto);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloMapper.mapToList(someTrelloDtoList).get(0));

        //When
        String id = trelloLists.get(0).getId();
        String name = trelloLists.get(0).getName();
        Boolean isClosedList = trelloLists.get(0).isClosed();
        //Then
        assertEquals("some_id", id);
        assertEquals("some_name", name);
        assertNotEquals(true, isClosedList);
    }
    @Test
    public void shouldReturnTrelloListDto(){
        //Given
        TrelloList someTrelloList = new TrelloList("some_id", "some_name", false);
        List<TrelloList> someTrelloArrayList = new ArrayList<>();
        someTrelloArrayList.add(someTrelloList);
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(trelloMapper.mapToListDto(someTrelloArrayList).get(0));
        //When
        String id = trelloLists.get(0).getId();
        String name = trelloLists.get(0).getName();
        Boolean isClosedList = trelloLists.get(0).isClosed();
        //Then
        assertEquals("some_id", id);
        assertEquals("some_name", name);
        assertNotEquals(true, isClosedList);
    }
    @Test
    public void shouldReturnTrelloCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("some_card", "some_description", "some_pos",
                "some_list_id");
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //When
        String trelloCardDtoName = trelloCardDto.getName();
        String trelloCardDtoDes = trelloCardDto.getDescription();
        String trelloCardDtoPos = trelloCardDto.getPos();
        String trelloCardDtoListId = trelloCardDto.getListId();
        //Then
        assertEquals("some_card", trelloCardDtoName);
        assertEquals("some_description", trelloCardDtoDes);
        assertEquals("some_pos", trelloCardDtoPos);
        assertEquals("some_list_id", trelloCardDtoListId);
    }
    @Test
    public void shouldReturnTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("some_card", "some_description", "some_pos",
                "some_list_id");
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //When
        String trelloCardName = trelloCard.getName();
        String trelloCardDes = trelloCard.getDescription();
        String trelloCardPos = trelloCard.getPos();
        String trelloCardListId = trelloCard.getListId();
        //Then
        assertEquals("some_card", trelloCardName);
        assertEquals("some_description", trelloCardDes);
        assertEquals("some_pos", trelloCardPos);
        assertEquals("some_list_id", trelloCardListId);
    }
}
