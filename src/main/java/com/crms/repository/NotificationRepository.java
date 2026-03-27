package com.crms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String>{

	List<Notification> findByUserId(String userId);
}
