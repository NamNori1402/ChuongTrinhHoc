package com.thanglong.chonlichthilai.taikhoan;

// Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
// Annotation
@Service
 
// Class
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserRepository userRepository;
    // Save operation
    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
    // Read operation
    @Override public List<User> fetchUserList(){
        return (List<User>) userRepository.findAll();
    }
    // Read operation
    @Override public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    // Read operation
    @Override public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName).orElse(null);
    }
    // Update operation
    @Override
    public User updateUser(User user, Long Id) {
        User userDB= userRepository.findById(Id).get(); 
        if (Objects.nonNull(user.getMobile()) && !"".equalsIgnoreCase(user.getMobile())) {
            userDB.setMobile(user.getMobile());
        }
        if (Objects.nonNull(user.getUserCode()) && !"".equalsIgnoreCase(user.getUserCode())) {
            userDB.setUserCode(user.getUserCode());
        }
        userDB.setAdmin(user.getAdmin());
        userDB.setSecretary(user.getSecretary());
        userDB.setLecturer(user.getLecturer());
        userDB.setAcademic(user.getAcademic());
        System.out.println(user.getActive());
        userDB.setActive(user.getActive());
        if (Objects.nonNull(user.getUnitId()) && !"".equalsIgnoreCase(user.getUnitId())) {
            userDB.setUnitId(user.getUnitId());
        }
        if (Objects.nonNull(user.getAcademic()) && !"".equalsIgnoreCase(user.getAcademic())) {
            userDB.setAcademic(user.getAcademic());
        }
        if (Objects.nonNull(user.getTitle()) && !"".equalsIgnoreCase(user.getTitle())) {
            userDB.setTitle(user.getTitle());
        }
        if (Objects.nonNull(user.getNote()) && !"".equalsIgnoreCase(user.getNote())) {
            userDB.setNote(user.getNote());
        }
        return userRepository.save(userDB);
    }
    // Delete operation
    @Override
    public void deleteUserById(Long userName){
        userRepository.deleteById(userName);
    }
}