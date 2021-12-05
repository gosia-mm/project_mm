public class GroupOfStudents {
    public String groupName;
    public int NumberOfStudents;
    public float Average;

    public GroupOfStudents(String name, int number, float avg) {
        groupName = name;
        NumberOfStudents = number;
        Average = avg;
    }
    public void addingNewStudent(int numberToAdd, float avgToAdd) {
        NumberOfStudents += numberToAdd;
        Average += avgToAdd;
    }
}
