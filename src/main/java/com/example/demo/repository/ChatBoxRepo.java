package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ChatBox;

public interface ChatBoxRepo extends JpaRepository<ChatBox, Integer>{

}
