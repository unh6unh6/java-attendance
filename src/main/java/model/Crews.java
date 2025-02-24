package model;

import dto.DismissalCrewDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

    public List<DismissalCrewDto> findDismissalCrewDtos(final LocalDate todayDate) {
        List<DismissalCrewDto> dtos = new ArrayList<>();
        for (Entry<String, Crew> entry : crews.entrySet()) {
            addDismissalCrewDto(todayDate, entry, dtos);
        }
        return dtos;
    }

    public void sortDismissalCrewDtos(final List<DismissalCrewDto> dtos) {
        Comparator<DismissalCrewDto> c = Comparator
                .comparing(DismissalCrewDto::subjectType, SubjectType::compare)
                .thenComparing((dto1, dto2) ->
                        Integer.compare(
                                SubjectType.calculateTotalLateCount(dto2.lateCount(), dto2.absentCount()),
                                SubjectType.calculateTotalLateCount(dto1.lateCount(), dto1.absentCount())))
                .thenComparing(DismissalCrewDto::nickname);
        dtos.sort(c);
    }

    private static void addDismissalCrewDto(LocalDate todayDate, Entry<String, Crew> entry,
                                            List<DismissalCrewDto> dtos) {
        List<LocalDateTime> attendanceHistory = entry.getValue().getAttendanceHistory(todayDate);
        Map<AttendanceType, Integer> result = AttendanceType.countAttendanceType(attendanceHistory);
        SubjectType subjectType = SubjectType.from(result);
        if (subjectType.equals(SubjectType.해당없음)) {
            return;
        }
        dtos.add(new DismissalCrewDto(entry.getKey(), result.get(AttendanceType.결석),
                result.get(AttendanceType.지각), subjectType));
    }
}
