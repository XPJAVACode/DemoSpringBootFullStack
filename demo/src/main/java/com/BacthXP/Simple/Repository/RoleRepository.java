package com.BacthXP.Simple.Repository;

import org.springframework.data.repository.CrudRepository;

import com.BacthXP.Simple.Entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long>{

	RoleEntity findByName(String name);

}
