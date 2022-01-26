package adm2022.pp;

import adm2022.pp.model.MeetingDTO;
import adm2022.pp.model.StoryDTO;
import adm2022.pp.service.MeetingService;
import adm2022.pp.service.StoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class PpApplication implements CommandLineRunner {
    private final StoryService storyService;
    private final MeetingService meetingService;
    private final MeetingDTO noMeeting;

    public PpApplication(StoryService storyService, MeetingService meetingService) {
        this.storyService = storyService;
        this.meetingService = meetingService;
        this.noMeeting = buildMeetingDTO("no meeting yet");
    }

    public static void main(String[] args) {
        SpringApplication.run(PpApplication.class, args);
    }

    public void run(String... args) {
        System.out.println("Hi from CommandLineRunnger!");
        Long result = this.meetingService.create(noMeeting);
        noMeeting.setId(result);
        this.storyService.create(buildStoryDTO("Demo Story", new java.util.Date(), "Demo Description", 3));
        this.storyService.create(buildStoryDTO("Crazy Story", new java.util.Date(), "Do all the things and make billions", 13));
        this.meetingService.create(buildMeetingDTO("Kickoff meeting"));
    }

    private MeetingDTO buildMeetingDTO(String name) {
        MeetingDTO m = new MeetingDTO();
        m.setName(name);
        m.setDateCreation(new Date());
        return m;
    }

    private StoryDTO buildStoryDTO(String su,
                                   java.util.Date cd, String d, int p) {
        StoryDTO s = new StoryDTO();
        s.setSummary(su);
        s.setDateCreation(cd);
        s.setDescription(d);
        s.setPoints(Integer.valueOf(p));
        s.setMeetingStorys(noMeeting.getId());
        return s;
    }
}
