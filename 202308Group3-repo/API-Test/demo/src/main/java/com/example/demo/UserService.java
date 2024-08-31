package com.example.demo;

public interface UserService  {

    public UserDetails createUser(UserDetails user);
        // You can add additional business logic or validation here before saving the user

//    public User findUser(String username, String email) {
//        return userRepository.findByUsernameOrEmail(username,email);
//    }

//    public void deleteUser(User user) {
//        userRepository.delete(user);
//    }

    // Add more methods for user-related operations as needed

}