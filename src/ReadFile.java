import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;



public class ReadFile {
    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\katalog\\grades.txt");
            Path path = Paths.get("C:\\katalog\\grades.txt");
            Scanner myReader = new Scanner(myObj);
            long numberOfLines = 0;
            try {
                numberOfLines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String data;
            String[] cleanData;
            Student[] student = new Student[(int)(numberOfLines)];

            int i = 0;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                cleanData = data.split(";");
                student[i] = new Student();
                student[i].name = cleanData[2];
                student[i].NumberOfStudents = 1;
                student[i].FirstGrade = Float.parseFloat(cleanData[3]);
                student[i].SecondGrade = Float.parseFloat(cleanData[4]);
                student[i].ThirdGrade = Float.parseFloat(cleanData[5]);
                student[i].FourthGrade = Float.parseFloat(cleanData[6]);
                student[i].FifthGrade = Float.parseFloat(cleanData[7]);
                student[i].SixthGrade = Float.parseFloat(cleanData[8]);
                student[i].Average = (student[i].FirstGrade + student[i].SecondGrade + student[i].ThirdGrade + student[i].FourthGrade + student[i].FifthGrade + student[i].SixthGrade) / 6;
                i++;
            }
            myReader.close();

            ArrayList<GroupOfStudents> groups = new ArrayList<GroupOfStudents>();
            for (int j=0; j<student.length; j++) {
                if(!containsElement(groups, student[j].name)) {
                    groups.add(new GroupOfStudents(student[j].name, 1, student[j].Average));
                }
                else {
                    int index = groups.indexOf(student[j].name);
                    groups.get(index).addingNewStudent(1, student[j].Average);
                }
            }

            for (GroupOfStudents grp : groups) {
                grp.Average /= grp.NumberOfStudents;
            }

            for (int m=0; m<groups.size()-1; m++) {
                for (int n=0; n<groups.size()-1-i; n++) {
                    if (groups.get(n).Average > groups.get(n+1).Average) {
                        GroupOfStudents temp = groups.get(n);
                        groups.set(n, groups.get(n+1));
                        groups.set(n+1, temp);
                    }
                }
            }

            for (GroupOfStudents groupOfStudents : groups) {
                System.out.println(groupOfStudents.Average);
            }

            } catch (FileNotFoundException e) {
            System.out.println("An error occurred with reading file. Probably the file doesn't exist.");
            e.printStackTrace();
        }
    }

    public static boolean containsElement(ArrayList<GroupOfStudents> groupOfStuds, String name) {
        boolean response = false;

        int x = 0;
        while (x < groupOfStuds.size()-1) {
            if (groupOfStuds.get(x).groupName.equals(name)) {
                response = true;
                break;
            }
            x++;
        }
        return response;
    }
}