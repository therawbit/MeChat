package com.therawbit.MM.repository;

import com.therawbit.MM.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom,String> {
    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId,String receiverId);
}
