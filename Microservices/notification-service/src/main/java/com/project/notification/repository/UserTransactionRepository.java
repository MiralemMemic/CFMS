package com.project.notification.repository;

import com.project.notification.model.NotificationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<NotificationTransaction,Integer>{
}
