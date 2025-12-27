package org.example.demooooooo.Service;

import org.springframework.stereotype.Service;

@Service
public class loginserivce
{
 private final Repository loginrepository;
 public loginserivce( Repository loginrepository)
 {
     this.loginrepository=loginrepository;
 }
 public Detail authenticate(String email, String password)
 {
     Detail user=loginrepository.findByEmail(email).orElse(null);
     if(user==null)
     {
         return  null;
     }
     if(!user.getPassword().equals(password))
     {
         return  null;
     }
     return user;
 }

}
