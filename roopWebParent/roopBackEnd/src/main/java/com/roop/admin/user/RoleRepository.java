package com.roop.admin.user;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import com.roop.common.entity.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	 

}
