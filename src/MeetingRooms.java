import java.util.*;

public class MeetingRooms {

}

// Definition of Interval:
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}


//no need for this since the given arr is already sorted according to start time
// class IntervalComparator implements Comparator<Interval>{

//     @Override
//     public int compare(Interval o1, Interval o2) {
//         return Integer.compare(o1.start, o2.start);
//     }
    
// }

class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
    

        for (int i = 1; i < intervals.size(); i++) {
            
            Interval currInterval = intervals.get(i);
            Interval prevInterval = intervals.get(i-1);

            if (currInterval.start<prevInterval.end && currInterval.start >= prevInterval.start) {
                return false;
            }

        }

        return true;
    }
}

