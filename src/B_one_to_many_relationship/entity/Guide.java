package B_one_to_many_relationship.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Guide {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "staff_id", nullable = false)
	private String staffId;
	private String name;
	private Integer salary;

	@OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
	private Set<Student> students = new HashSet<Student>();

	public Guide() {
	}

	public Guide(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}

	public Set<Student> getStudents() {
		return students;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void addStudent(Student student) {
		students.add(student);
		student.setGuide(this);
	}

}