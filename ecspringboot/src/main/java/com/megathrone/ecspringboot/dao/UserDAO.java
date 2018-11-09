package com.megathrone.ecspringboot.dao;

import com.megathrone.ecspringboot.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
  User findByName(String name);
}
