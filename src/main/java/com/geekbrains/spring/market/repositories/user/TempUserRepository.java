package com.geekbrains.spring.market.repositories.user;

import com.geekbrains.spring.market.entity.user.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser,Long> {
}
