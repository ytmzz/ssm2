package com.ytmzz.service;

import com.ytmzz.pojo.Administrator;
import com.ytmzz.pojo.UserRole;

public interface UserInfoService {

    public boolean updateAdministratorInfo(UserRole userRole, Administrator administrator);

    public Administrator getAdministratorInfo(Integer userId);
}
