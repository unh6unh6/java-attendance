package dto;

import model.SubjectType;

public record DismissalCrewDto(String nickname, int absentCount, int lateCount, SubjectType subjectType) implements
        Comparable<DismissalCrewDto> {


    @Override
    public int compareTo(final DismissalCrewDto o) {
        // TODO : 순서 장담 못함 !!
        if (this.subjectType != o.subjectType) {
            return SubjectType.compare(this.subjectType, o.subjectType);
        }
        int thisTotalLateCount = SubjectType.calculateTotalLateCount(this.lateCount, this.absentCount);
        int targetTotalLateCount = SubjectType.calculateTotalLateCount(o.lateCount, o.absentCount);
        if (thisTotalLateCount != targetTotalLateCount) {
            return Integer.compare(targetTotalLateCount, thisTotalLateCount);
        }
        return this.nickname.compareTo(o.nickname);
    }
}
