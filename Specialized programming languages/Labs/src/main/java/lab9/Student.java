package lab9;

public class Student implements Comparable<Student> {
    private String surname;
    private String group;
    private String course;
    private int mark;

    public Student(String surname, String group, String course, int mark) {
        this.surname = surname;
        this.group = group;
        this.course = course;
        this.mark = mark;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int compareTo(Student o) {
        return surname.compareTo(o.course);
    }
}
