package com.crms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String>{

}
