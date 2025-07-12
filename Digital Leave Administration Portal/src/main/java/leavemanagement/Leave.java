package leavemanagement;

public class Leave
{
	private int empId;
	private String empName;
	private int age;
	private String dateOfJoining;
	private int totalNoOfLeaves;
	private int availedLeaves;
	private int remainingLeaves;

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public int getTotalNoOfLeaves() {
		return totalNoOfLeaves;
	}
	public void setTotalNoOfLeaves(int totalNoOfLeaves) {
		this.totalNoOfLeaves = totalNoOfLeaves;
	}
	public int getAvailedLeaves() {
		return availedLeaves;
	}
	public void setAvailedLeaves(int availedLeaves) {
		this.availedLeaves = availedLeaves;
	}
	public int getRemainingLeaves() {
		return remainingLeaves;
	}
	public void setRemainingLeaves(int remainingLeaves) {
		this.remainingLeaves = remainingLeaves;
	}
}
