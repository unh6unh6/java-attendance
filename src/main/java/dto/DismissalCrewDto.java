package dto;

import model.SubjectType;

public record DismissalCrewDto(String nickname, int absentCount, int lateCount, SubjectType subjectType) {
}
