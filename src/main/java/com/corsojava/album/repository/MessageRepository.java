package com.corsojava.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.album.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
