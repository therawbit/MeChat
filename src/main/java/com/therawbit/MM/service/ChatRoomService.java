package com.therawbit.MM.service;

import com.therawbit.MM.entity.ChatRoom;
import com.therawbit.MM.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomId(String senderId,String receiverId,boolean createNewRoomIfNotExist){
        return chatRoomRepository.findBySenderIdAndReceiverId(senderId,receiverId)
                .map(ChatRoom::getChatId)
                .or(
                ()->{
                    if(createNewRoomIfNotExist){
                        var chatId = createChat(senderId,receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                }
        );
    }
    private String createChat(String senderId,String receiverId){
        var chatId = String.format("%s_%s",senderId,receiverId);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .receiverId(receiverId)
                .build();
        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(receiverId)
                .receiverId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;
    }
}
