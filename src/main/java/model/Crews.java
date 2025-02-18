package model;

import java.util.Map;

public class Crews {

    private final Map<String, Crew> crews;

    public Crews(final Map<String, Crew> crews) {
        this.crews = crews;
    }

    public Crew findCrewByNickname(final String nickname) {
        if (!crews.containsKey(nickname)) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        return crews.get(nickname);
    }
}
