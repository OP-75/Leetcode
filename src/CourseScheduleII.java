import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CourseScheduleII {
    // this is similar to Course Schedule I
    private void addToHM(int[] prerequisite, HashMap<Integer, List<Integer>> hm) {

        if (hm.containsKey(prerequisite[0])) {
            hm.get(prerequisite[0]).add(prerequisite[1]);
        } else {
            hm.put(prerequisite[0], new ArrayList<>());
            hm.get(prerequisite[0]).add(prerequisite[1]);
        }

    }

    private int[] topoSort(int numCourses, int[][] prerequisites) {

        // we are using Kahn's Algoritm here:
        // https://youtu.be/iTBaI90lpDQ?si=-JBOmgjV7G-dh3bd

        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        // first build adjency matrix (remember this graph will be DIRECTED)
        for (int[] course_pre_pair : prerequisites) {
            addToHM(course_pre_pair, hm);
            int course = course_pre_pair[0];
            int pre = course_pre_pair[1];
            inDegree[pre]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int course = 0; course < inDegree.length; course++) {
            if (inDegree[course] == 0) {
                q.add(course);
            }
        }

        ArrayList<Integer> topoSortedArr = new ArrayList<>();
        while (!q.isEmpty()) {
            int ele = q.remove();
            topoSortedArr.add(ele);

            if (hm.containsKey(ele)) {
                List<Integer> connectedList = hm.get(ele);

                for (Integer prereq : connectedList) {
                    inDegree[prereq]--;
                    if (inDegree[prereq] == 0) {
                        // ie all the nodes/courses pointing to it have been visited so its next in line
                        // to be added to q (ie visited)
                        q.add(prereq);
                    }
                }
            }
        }

        if (topoSortedArr.size() < numCourses) {
            return new int[0]; // if sorted arr has less len then that means there are cycles in the graph
        } else {
            int[] ans = new int[numCourses];
            Collections.reverse(topoSortedArr); //IMP!!! - We have to reverse caz we want to obtain the course order ie which course to do first ie the course that has 0 connections futher/0 prerequesites
            for (int i = 0; i < topoSortedArr.size(); i++) {
                ans[i] = topoSortedArr.get(i);
            }
            return ans;
            // if sorted arr = numCourses ie all elements of graph are in soreted arr that
            // means we have DAG/no cycles in graphs
        }

    }

    private int[] dfsSolution(int numCourses, int[][] prerequisites) {

        // this is eaiser method: video/intuition:
        // https://youtu.be/9twcmtQj4DU?si=f8R45hBJoxmxa2Xk

        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        // first build adjency matrix (remember this graph will be DIRECTED)
        for (int[] course_pre_pair : prerequisites) {
            addToHM(course_pre_pair, hm);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] pathVisited = new boolean[numCourses];

        ArrayList<Integer> topoSortArr = new ArrayList<>();

        for (int course=0; course<numCourses; course++) {
            if (dfsDetectCycle(course, hm, visited, pathVisited, topoSortArr)) {
                // if there is a cycle in graph then we can complete course so return null
                return new int[0];
            }
        }

        int[] ans = new int[numCourses];
        for (int i = 0; i < topoSortArr.size(); i++) {
            ans[i] = topoSortArr.get(i);
        }
        return ans;
    }

    private boolean dfsDetectCycle(Integer course, HashMap<Integer, List<Integer>> hm, boolean[] visited,
            boolean[] pathVisited, ArrayList<Integer> toposortArr) {

        if (pathVisited[course]) {
            return true;
        }
        if (visited[course]) {
            return false;
        }

        visited[course] = true;
        pathVisited[course] = true;

        List<Integer> prereqList = hm.get(course);
        if (prereqList == null) {
            pathVisited[course] = false; // IMP!!!: there might be a case where prereq HAS NOT been insereted HM (caz it
                                         // doesnt point to any nodes) so when that is the case be sure to unmark it as
                                         // path visited
            toposortArr.add(course);
            return false;
        }
        for (Integer prereq : prereqList) {
            if (dfsDetectCycle(prereq, hm, visited, pathVisited, toposortArr)) {
                // if we detect cycle anywhere return true (collapse function & return true)
                return true;
            }
        }

        pathVisited[course] = false;
        toposortArr.add(course);

        return false;

    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // return topoSort(numCourses, prerequisites);
        return dfsSolution(numCourses, prerequisites);
    }

}
