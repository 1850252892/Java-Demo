package service;

import entity.User;

public interface UserService {
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    User selectUser(int id);
}
