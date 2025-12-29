package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Repository.Repository;
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
