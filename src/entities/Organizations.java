package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Organizations implements User {
    private final String name;
    private String password;
    private String bio;
    private ArrayList<Requests> requestList = new ArrayList<Requests>();

    private LocalDateTime date;

    public Organizations(String name, String password, String bio, LocalDateTime date) {
        this.name = name;
        this.password = password; // org is automatically created with password: "1234"
        this.bio = bio;
        this.date = date;
    }

    public void addRequest(Requests request) {
        requestList.add(request);
    }
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getBio() {
        return bio;
    }

    @Override
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return null;
    }

    public void acceptRequest(Requests request, String message) {
        request.setStatus("Accepted");
        request.getPet().setAdoptable(false);
        request.getPet().setOwner(request.getUser().getName());
        request.setOrgMessage(message);
    }
    public void rejectRequest(Requests request, String message) {
        request.setStatus("Denied");
        request.setOrgMessage(message);
    }
}
