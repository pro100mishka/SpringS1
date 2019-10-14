package com.geekbrains.spring.market.repositories.user.role;


import com.geekbrains.spring.market.entity.user.role.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String name);
}
