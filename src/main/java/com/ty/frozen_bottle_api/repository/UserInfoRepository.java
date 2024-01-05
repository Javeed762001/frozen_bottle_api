package com.ty.frozen_bottle_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.util.Role;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

	@Query("SELECT u FROM  UserInfo u WHERE u.role=?1")
	List<UserInfo> checkManager(Role role);
}
