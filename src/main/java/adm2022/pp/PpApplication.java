package adm2022.pp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.*;

import adm2022.pp.repos.*;
import adm2022.pp.service.*;
import adm2022.pp.model.*;


@SpringBootApplication
public class PpApplication implements CommandLineRunner {


    // @Autowired
    // private final StoryRepository storyRepository;

    @Autowired
    private final StoryService storyService;

    public static void main(String[] args) {
        SpringApplication.run(PpApplication.class, args);
    }

    public void run(String... args) {
        System.out.println("Hi from CommandLineRunnger!");
        this.storyService.create(buildStoryDTO("Demo Story",new java.util.Date(), "Demo Description", 3));
    }

    private StoryDTO buildStoryDTO(String su, 
    java.util.Date cd, String d, int p) {
        StoryDTO s = new StoryDTO();
        s.setSummary(su);
        s.setDateCreation(cd);
        s.setDescription(d);
        s.setPoints(Integer.valueOf(p));
        return s;
    }
}
