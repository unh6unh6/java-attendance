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
        Comparator<DismissalCrewDto> dismissalCrewDtoComparator = Comparator
                .comparing(DismissalCrewDto::subjectType, SubjectType::compare)
                .thenComparing((firstDto, secondDto) ->
                        Integer.compare(
                                SubjectType.calculateTotalLateCount(secondDto.lateCount(), secondDto.absentCount()),
                                SubjectType.calculateTotalLateCount(firstDto.lateCount(), firstDto.absentCount())))
                .thenComparing(DismissalCrewDto::nickname);
        dtos.sort(dismissalCrewDtoComparator);
    }

    private static void addDismissalCrewDto(final LocalDate todayDate, final Entry<String, Crew> entry,
                                            final List<DismissalCrewDto> dtos) {
        List<LocalDateTime> attendanceHistory = entry.getValue().getAttendanceHistory(todayDate);
        Map<AttendanceType, Integer> result = AttendanceType.countAttendanceType(attendanceHistory);
        SubjectType subjectType = SubjectType.from(result);
        if (subjectType.equals(SubjectType.NONE)) {
            return;
        }
        dtos.add(new DismissalCrewDto(entry.getKey(), result.get(AttendanceType.ABSENT),
                result.get(AttendanceType.LATE), subjectType));
    }
}
