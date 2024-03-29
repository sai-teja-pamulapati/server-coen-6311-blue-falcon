package com.bluefalcon.project.controllers;

import com.bluefalcon.project.model.News;
import com.bluefalcon.project.model.User;
import com.bluefalcon.project.request.FriendRequest;
import com.bluefalcon.project.request.SavedNewsRequest;
import com.bluefalcon.project.response.BaseResponse;
import com.bluefalcon.project.response.UserSocialResponse;
import com.bluefalcon.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserService userService;
    
  @PostMapping(value = "/add-fav-topic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addFavouriteTopics(@RequestBody User user) {
        return ResponseEntity.ok(userService.addFavouriteTopic(user));
    }

   
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserDetails(@RequestParam("emailId") String emailId) {
        return ResponseEntity.ok(userService.getUser(emailId));
    }

    @GetMapping(value="/users/near-me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsersNearMe(@RequestParam("location") String location){
      return ResponseEntity.ok(userService.getUsersNearMe(location));
    }

    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping(value = "/users/friends", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUserFriends(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(userService.getUserFriends(userId));
    }

    @GetMapping(value = "/users/get-news-wishlist")
    public ResponseEntity<List<News>> getUserWishlist(@RequestParam("emailId") String emailId){
        return ResponseEntity.ok(userService.getWishlist(emailId));
    }

    @PostMapping(value = "/users/add-news-to-wishlist")
    public ResponseEntity<List<News>> addNewsToWishlist(@RequestParam("newsId") String newsId, @RequestParam("emailId") String emailId){
      return ResponseEntity.ok(userService.addNewsToWishlist(emailId, newsId));
    }

    @PutMapping(value = "/users/news-list")
    public ResponseEntity<BaseResponse> addNewsToWishlist(@RequestBody SavedNewsRequest savedNewsRequest){
        return ResponseEntity.ok(userService.updateSavedNewsList(savedNewsRequest));
    }

    @PostMapping(value = "/users/remove-news-to-wishlist")
    public ResponseEntity<List<News>> removeNewsToWishlist(@RequestParam("newsId") String newsId, @RequestParam("emailId") String emailId){
        return ResponseEntity.ok(userService.removeNewsFromWishlist(emailId, newsId));
    }

    @GetMapping(value = "/users/social", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSocialResponse> getUserSocial(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(userService.getUserSocial(userId));
    }

    @GetMapping(value = "/users/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> userSearch(@RequestParam("query") String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }

    @PostMapping(value = "/users/friends", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> sendFriendRequest(@RequestBody FriendRequest friendRequest) {
        return ResponseEntity.ok(userService.sendingFriendRequest(friendRequest));
    }

    @PatchMapping(value = "/users/friends", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> actionFriendRequest(@RequestBody FriendRequest friendRequest) {
        return ResponseEntity.ok(userService.actionFriendRequest(friendRequest));
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PatchMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User payload) {
        return ResponseEntity.ok(userService.updateUser(payload));
    }


}
