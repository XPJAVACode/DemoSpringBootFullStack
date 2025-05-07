package com.BacthXP.Simple.Repository;

import org.springframework.data.repository.CrudRepository;

import com.BacthXP.Simple.Entity.AuthorityEntity;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long>{
	AuthorityEntity findByName(String name);
}
