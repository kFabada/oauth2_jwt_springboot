package com.oauthjwt.fabada.repository;

import com.oauthjwt.fabada.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Long> {
}
