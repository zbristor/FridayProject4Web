import java.util.ArrayList;
import java.util.Scanner;

public class Person {
	private String fname;
	private String lname;
	private String email;
	private ArrayList<String> eduArray = new ArrayList<String>();
	private ArrayList<String> workArray = new ArrayList<String>();
	private ArrayList<String> skillArray = new ArrayList<String>();
public Person(String fname, String lname, ArrayList<String> eduArray, ArrayList<String> workArray, ArrayList<String> skillArray)
{
	this.fname=fname;
	this.lname=lname;
	this.eduArray=eduArray;
	this.workArray=eduArray;
	this.skillArray=eduArray;
}

public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public void addEdu(String e){
	eduArray.add(e);
}
public void addWork(String w)
{
	workArray.add(w);
}
public void addSkill(String s)
{
	skillArray.add(s);
}
}
