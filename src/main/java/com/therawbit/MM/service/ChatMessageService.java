package com.therawbit.MM.service;

import com.therawbit.MM.entity.ChatMessage;
import com.therawbit.MM.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                true
        ).orElseThrow();
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }
    public List<ChatMessage> findChatMessages(String senderId,String receiveId){
        var chatId = chatRoomService.getChatRoomId(senderId,receiveId,false);
        return chatId.map(repository::findByChatId).orElse(
                new ArrayList<>()
        );
    }
}
