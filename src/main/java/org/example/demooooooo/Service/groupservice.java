package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Entity.groupdetail;
import org.example.demooooooo.Repository.Repository;
import org.example.demooooooo.Repository.grouprepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class groupservice {
    private  final Repository repository;
    private final grouprepository grouprepository;

    public groupservice(Repository repository, grouprepository grouprepository) {
        this.repository = repository;
        this.grouprepository = grouprepository;
    }

    public  void creategroup(String groupName, List<String> members)
    {
    List<Detail> detail=repository.findAllById(members);

    groupdetail detail1=new groupdetail();
    detail1.setGroup_name(groupName);
    detail1.setMembers(detail);

        grouprepository.save(detail1);
    }


}
