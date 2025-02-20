package dto;

import model.SubjectType;

public record DismissalCrewDto(String nickname, int absentCount, int lateCount, SubjectType subjectType) implements
        Comparable<DismissalCrewDto> {

    @Override
    public int compareTo(final DismissalCrewDto o) {
        if (this.subjectType != o.subjectType) {
            return SubjectType.compare(this.subjectType, o.subjectType);
        }
        return compareLateCountAndNickname(o);
    }

    private int compareLateCountAndNickname(DismissalCrewDto other) {
        int thisTotalLateCount = SubjectType.calculateTotalLateCount(this.lateCount, this.absentCount);
        int targetTotalLateCount = SubjectType.calculateTotalLateCount(other.lateCount, other.absentCount);
        if (thisTotalLateCount != targetTotalLateCount) {
            return Integer.compare(targetTotalLateCount, thisTotalLateCount);
        }
        return this.nickname.compareTo(other.nickname);
    }
}
