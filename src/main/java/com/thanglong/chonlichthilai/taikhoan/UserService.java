package com.thanglong.chonlichthilai.taikhoan;

//Importing required classes
import java.util.List;

//Interface
public interface UserService {

 // Save operation
	User saveUser(User user);

 // Read operation
	List<User> fetchUserList();

 // Update operation
	User updateUser(User user, Long id);
	
	User findUserById(Long id);
	
	User findUserByUserName(String userName);
	
 // Delete operation
	void deleteUserById(Long id);
}