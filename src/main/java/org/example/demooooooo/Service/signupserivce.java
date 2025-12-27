package org.example.demooooooo.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

@Service
public class signupserivce
{

private final Repository signuprepository;

    public signupserivce(Repository signuprepository) {
        this.signuprepository = signuprepository;
    }
    public void signup(Detail detail)
    {
        signuprepository.save(detail);
    }

}
